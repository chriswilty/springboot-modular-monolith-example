package com.example.momo.counting.config;

import com.example.momo.counting.jpa.entity.Count;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import static java.util.Objects.requireNonNull;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.momo.counting.jpa.repository",
        entityManagerFactoryRef = "countingEntityManagerFactory",
        transactionManagerRef = "countingTransactionManager"
)
@EntityScan(basePackageClasses = {Count.class})
public class CountingDataConfig {

    @Bean
    @ConfigurationProperties("counting.datasource")
    public DataSourceProperties countingDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("counting.datasource")
    public DataSource countingDataSource() {
        return countingDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "countingEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean countingEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(countingDataSource())
                .packages(Count.class)
                .persistenceUnit("counting")
                .build();
    }

    @Bean
    public PlatformTransactionManager countingTransactionManager(
            final @Qualifier("countingEntityManagerFactory")
                    LocalContainerEntityManagerFactoryBean countingEntityManagerFactory
    ) {
        return new JpaTransactionManager(requireNonNull(countingEntityManagerFactory.getObject()));
    }
}
