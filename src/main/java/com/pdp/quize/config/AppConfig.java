package com.pdp.quize.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.pdp.quize.util.PhysicalNamingStrategyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import java.beans.PropertyVetoException;
import java.util.Properties;

// import org.apache.tomcat.jdbc.pool.DataSource;
// import org.springframework.orm.jpa.vendor.Database;

@Configuration
//@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan({
        "com.pdp.quize.controller",
        "com.pdp.quize.service",
        "com.pdp.quize.security.impl",
        "com.pdp.quize.domain"
})
@EnableJpaRepositories("com.pdp.quize.repository")
public class AppConfig {

    @Autowired
    private Environment env;


//    @Bean(destroyMethod = "close")
//    public DataSource dataSource() {
//        DataSource ds = new DataSource();
//
//        ds.setDriverClassName(env.getProperty("db.driver"));
//        ds.setUrl(env.getProperty("db.url"));
//        ds.setUsername(env.getProperty("db.user"));
//        ds.setPassword(env.getProperty("db.password"));
//        ds.setInitialSize(5);
//        ds.setMaxActive(30);
//        ds.setMaxIdle(10);
//        ds.setMaxWait(100);
//        ds.setMinEvictableIdleTimeMillis(30000);
//        ds.setTimeBetweenEvictionRunsMillis(20000);
//        ds.setRemoveAbandonedTimeout(30);
//        ds.setLogAbandoned(false);
//        ds.setRemoveAbandoned(true);
//        ds.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
//                "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer;" +
//                "org.apache.tomcat.jdbc.pool.interceptor.ResetAbandonedTimer");
//
//        return ds;
//    }

    @Bean(destroyMethod = "close")
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("hibernate.c3p0.min_size")));
        dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("hibernate.c3p0.max_size")));
//        dataSource.setAcquireIncrement(acquireIncrement);
//        dataSource.setIdleConnectionTestPeriod(idleTestPeriod);
//        dataSource.setMaxStatements(maxStatements);
        dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("hibernate.c3p0.idle_test_period")));
        dataSource.setJdbcUrl(env.getProperty("db.url"));
        dataSource.setPassword(env.getProperty("db.password"));
        dataSource.setUser(env.getProperty("db.username"));
        dataSource.setDriverClass(env.getProperty("db.driver"));
        return dataSource;
    }

    @Bean
    @Autowired
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(ComboPooledDataSource dataSource,
                                                                       JpaVendorAdapter vendorAdapter,
                                                                       Properties jpaProperties) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan("com.pdp.quize.domain");
        entityManagerFactory.setJpaProperties(jpaProperties);

        return entityManagerFactory;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public JpaVendorAdapter vendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        // vendorAdapter.setGenerateDdl(true);
        // vendorAdapter.setShowSql(true);
        // vendorAdapter.setDatabase(Database.MYSQL);

        return vendorAdapter;
    }

    @Bean
    public Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put(
                "hibernate.dialect",
                env.getProperty("hibernate.dialect"));
        properties.put(
                "hibernate.show_sql",
                env.getProperty("hibernate.show_sql"));
        properties.put(
                "hibernate.hbm2ddl.auto",
                env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put(
                "hibernate.physical_naming_strategy",
                PhysicalNamingStrategyImpl.INSTANCE);

        return properties;
    }

    /**
     * PersistenceExceptionTranslationPostProcessor is a bean post processor
     * which adds an advisor to any bean annotated with Repository so that any
     * platform-specific exceptions are caught and then rethrown as one
     * Spring's unchecked data access exceptions (i.e. a subclass of
     * DataAccessException).
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
