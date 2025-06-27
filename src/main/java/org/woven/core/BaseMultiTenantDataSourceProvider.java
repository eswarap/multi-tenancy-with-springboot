package org.woven.core;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseMultiTenantDataSourceProvider {
    public abstract Connection getConnection(Object tenantIdentifier) throws SQLException;
    public abstract void releaseConnection(Object tenantIdentifier, Connection connection) throws SQLException;
    protected abstract DataSource selectDataSource();
    protected abstract DataSource selectDataSource(Object tenantIdentifier);

    protected abstract Connection getAnyConnection() throws SQLException;

    protected abstract void releaseAnyConnection(Connection connection) throws SQLException;
}
