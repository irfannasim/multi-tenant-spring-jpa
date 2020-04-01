
package com.mt.rnd.tenant.wrapper;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUserDetails
        extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;
    private String tenant;

    public CustomUserDetails(String username, String password,
                             Collection<? extends GrantedAuthority> authorities, String tenant) {
        super(username, password, authorities);
        this.tenant = tenant;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

}
