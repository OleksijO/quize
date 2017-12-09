package com.pdp.quize.config.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Aspect
@Component
public class TransactionPerHttpRequestConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger("TRANSACTION MANAGEMENT");

    private static final String EXECUTE_ON_ALL_METHODS_IN_CONTROLLERS = "execution(* com.pdp.quize.controller.*.*(..))";
    private static final String EXCLUDE_METHODS_STARTING_WITH_GET = "!execution(* com.pdp.quize.controller.*.get*(..))";
    private static final String EXCLUDE_INIT_BILDER_METHOD = "!execution(* com.pdp.quize.controller.*.initBinder(..))";
    private static final String AND = " && ";

    @Autowired
    private EntityManagerFactory emf;

    @Around(EXECUTE_ON_ALL_METHODS_IN_CONTROLLERS +
            AND + EXCLUDE_METHODS_STARTING_WITH_GET +
            AND + EXCLUDE_INIT_BILDER_METHOD)
    public Object aroundSampleCreation(ProceedingJoinPoint pjp) throws Throwable {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction(); //returns new tx
        String txHash = Integer.toHexString(transaction.hashCode());
        LOGGER.info("Transaction is active: {}", transaction.isActive());
        transaction.begin();

        log("started", pjp, txHash);
        LOGGER.info("Transaction is active: {}", transaction.isActive());

        Object result;
        try {

            result = pjp.proceed(pjp.getArgs());
            transaction.commit();

            log("ended", pjp, txHash);
            LOGGER.info("Transaction is active: {}", transaction.isActive());

        } catch (Exception e) {

            transaction.rollback();

            log("rolled back", pjp, txHash);
            LOGGER.info("Transaction is active: {}", transaction.isActive());

            throw e;
        }
        return result;
    }

    private void log(String action, ProceedingJoinPoint pjp, String txObj) {
        LOGGER.info("Transaction [" + txObj + "] " + action + " for method " +
                pjp.getTarget().getClass().getSimpleName() + "#" + pjp.getSignature().toShortString());
    }
}
