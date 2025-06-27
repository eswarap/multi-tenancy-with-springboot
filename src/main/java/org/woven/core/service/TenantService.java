package org.woven.core.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class TenantService {
    private final JdbcTemplate jdbcTemplate;

    @Inject
    public TenantService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTenant(String tenantName) {
        // Validate tenant name: allow only alphanumeric characters and underscores.
        if (!tenantName.matches("^[a-zA-Z0-9_]+$")) {
            throw new IllegalArgumentException("Invalid tenant name.");
        }
        String sql = "CREATE SCHEMA IF NOT EXISTS " + tenantName;
        jdbcTemplate.execute(sql);
    }

}
