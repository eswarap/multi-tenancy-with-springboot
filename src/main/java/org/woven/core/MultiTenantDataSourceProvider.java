package org.woven.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MultiTenantDataSourceProvider extends BaseMultiTenantDataSourceProvider {

    private static final Logger logger = LoggerFactory.getLogger(MultiTenantDataSourceProvider.class);
    private final DataSource dataSource;

    @Inject
    public MultiTenantDataSourceProvider(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Connection getConnection(Object tenantIdentifier) throws SQLException {
        // Implementation to get a connection for the specified tenant
        // This could involve looking up a data source based on the tenant identifier
        logger.atInfo().log("Getting connection for tenant: " + tenantIdentifier);
        String tenantId = tenantIdentifier != null ? tenantIdentifier.toString() : "public";
        logger.info("Acquiring connection for tenant {}", tenantId);
        Connection connection = getAnyConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("SET search_path TO %s;", tenantId));
        }
        return connection;
    }

    @Override
    public void releaseConnection(Object tenantIdentifier, Connection connection)
            throws SQLException {
        // Implementation to release the connection for the specified tenant
        logger.atInfo().log("Releasing connection for tenant: " + tenantIdentifier);
        try (Statement statement = connection.createStatement()) {
            statement.execute("SET search_path TO public;");
        }
        releaseAnyConnection(connection);
        // Logic to close or return the connection to the pool
    }

    @Override
    protected DataSource selectDataSource() {
        return dataSource;
    }

    @Override
    protected DataSource selectDataSource(final Object tenantIdentifier) {
        return dataSource;
    }

    @Override
    protected Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    protected void releaseAnyConnection(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
