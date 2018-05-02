package com.zen.smi.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enum of all possible roles of the EPORTAL application.
 * 
 * @author amantri
 */
public enum SMIAuthority  implements GrantedAuthority {
	// These roles are specified in the security application context and are
    // mapped to LDAP roles by the AuthoritiesMapper
	ROLE_ADMIN, ROLE_USER, ROLE_ANONYMOUS;

    public String getAuthority() {
        return name();
    }
}
