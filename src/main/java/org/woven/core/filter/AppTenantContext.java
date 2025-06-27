package org.woven.core.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static org.woven.core.constants.TenantConstants.DEFAULT_TENANT;
import static org.woven.core.constants.TenantConstants.LOGGER_TENANT_ID;
import static org.woven.core.constants.TenantConstants.TENANT_HEADER;

public class AppTenantContext implements Filter {

    private static final ThreadLocal<String> currentTenant = new ThreadLocal<>();

    public static String getCurrentTenant() {
        String tenant = currentTenant.get();
        return Objects.requireNonNullElse(tenant, DEFAULT_TENANT);
    }

    public static void setCurrentTenant(final String tenant) {
        MDC.put(LOGGER_TENANT_ID, tenant);
        currentTenant.set(tenant);
    }

    public static void clear() {
        MDC.clear();
        currentTenant.remove();
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain)
                            throws IOException, ServletException {
        try {
            // Set the tenant context here
            // For example, you might extract tenant information from the request
            HttpServletRequest req = (HttpServletRequest) request;
            String tenantId = req.getHeader(TENANT_HEADER);
            String currentTenant = Optional.of(tenantId).orElse(DEFAULT_TENANT);
            setCurrentTenant(currentTenant);

            // Proceed with the filter chain
            chain.doFilter(request, response);
        } finally {
            // Clear the tenant context after processing the request
            clear();
        }
    }
}
