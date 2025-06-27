package org.woven.core;

public interface TenantIdentifierProvider {
    String  resolveCurrentTenantIdentifier();
    boolean  validateExistingCurrentSessions();
}
