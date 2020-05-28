package com.example.momo.messages.config;

import com.example.momo.messages.jpa.entity.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import static java.util.Objects.requireNonNull;

@Configuration
@PropertySource("classpath:messages.properties")
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.momo.messages.jpa.repository",
        entityManagerFactoryRef = "messagesEntityManagerFactory",
        transactionManagerRef = "messagesTransactionManager"
)
@EntityScan(basePackageClasses = {Message.class})
public class MessageDataConfig {

    @Bean
    @Primary
    @ConfigurationProperties("messages.datasource")
    public DataSourceProperties messagesDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("messages.datasource")
    public DataSource messagesDataSource() {
        return messagesDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "messagesEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean messagesEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(messagesDataSource())
                .packages(Message.class)
                .persistenceUnit("messages")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager messagesTransactionManager(
            final @Qualifier("messagesEntityManagerFactory")
            LocalContainerEntityManagerFactoryBean messagesEntityManagerFactory
    ) {
        return new JpaTransactionManager(requireNonNull(messagesEntityManagerFactory.getObject()));
    }
}
