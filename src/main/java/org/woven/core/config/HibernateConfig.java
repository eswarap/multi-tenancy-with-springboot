package org.woven.core.config;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.woven.core.AppTenantIdentifierProvider;
import org.woven.core.TenantIdentifierProvider;

import javax.inject.Inject;

public class HibernateConfig {
    private final JpaProperties jpaProperties;

    @Inject
    public HibernateConfig(final JpaProperties jpaProperties) {
        this.jpaProperties = jpaProperties;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public TenantIdentifierProvider currentTenantIdentifierResolver() {
        return new AppTenantIdentifierProvider(); // Replace with your implementation
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(javax.sql.DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setPackagesToScan("org.woven.core"); // Adjust package as needed
        emf.setJpaPropertyMap(jpaProperties.getProperties());
        return emf;
    }

}
