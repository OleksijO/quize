package com.pdp.quize.config.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Aspect
@Component
public class TransactionPerHttpRequestConfig {

    @Autowired
    private EntityManager entityManager;

    @Around("execution(* com.pdp.quize.controller.*.*(..)) " +
            "&& !execution(* com.pdp.quize.controller.*.get*(..))" +
            "&& !execution(* com.pdp.quize.controller.*.initBinder(..))")
    public Object aroundSampleCreation(ProceedingJoinPoint pjp) throws Throwable {

        // entityManager.getTransaction().begin();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>\nTransaction started for method " +
                pjp.getTarget().getClass().getSimpleName() + "#" + pjp.getSignature().toShortString());

        Object result;
        try {

            result = pjp.proceed(pjp.getArgs());
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>\nTransaction ended for method " +
                    pjp.getTarget().getClass().getSimpleName() + "#" + pjp.getSignature().toShortString());
            // entityManager.getTransaction().commit();

        } catch (Exception e) {

            // entityManager.getTransaction().rollback();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>\nTransaction rollbacked for method " +
                    pjp.getTarget().getClass().getSimpleName() + "#" + pjp.getSignature().toShortString());
            throw e;

        }

        return result;
    }
}
