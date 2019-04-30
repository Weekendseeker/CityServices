package org.marvin;

import org.h2.jdbcx.JdbcDataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class AppConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory(){

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setHibernateProperties(hibernateProp());
        sessionFactory.setPackagesToScan("org.marvin.models");

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource(){

        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:~/servicesBD");

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;

    }

    private final Properties hibernateProp(){

        Properties properties = new Properties();
        properties.setProperty(
                "hibernate.dialect", "org.hibernate.dialect.H2Dialect");

//        properties.setProperty(
//                "hibernate.hbm2ddl.auto", "create");

        return properties;
    }
}
