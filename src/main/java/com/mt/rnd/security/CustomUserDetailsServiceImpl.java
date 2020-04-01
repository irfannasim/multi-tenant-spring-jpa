package com.mt.rnd.security;

import com.mt.rnd.tenant.model.Permission;
import com.mt.rnd.tenant.model.User;
import com.mt.rnd.tenant.repository.user.PermissionRepository;
import com.mt.rnd.tenant.service.user.UserService;
import com.mt.rnd.tenant.wrapper.CustomUserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service("userDetailsService")
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsernameAndTenantId(String username,
                                                     String tenantId) throws UsernameNotFoundException {
        if (StringUtils.isAnyBlank(username, tenantId)) {
            throw new UsernameNotFoundException(
                    "Username and domain must be provided");
        }
        // Look for the user based on the username and tenant by accessing the
        // UserRepository via the UserService
        User user = userService.findByUsernameAndTenantId(username, tenantId);

        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format(
                            "Username not found for domain, "
                                    + "username=%s, tenant=%s",
                            username, tenantId));
        }

        CustomUserDetails customUserDetails = new CustomUserDetails(
                user.getUsername(), user.getPassword(), getAuthorities(user),
                tenantId);

        return customUserDetails;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {

        List<String> perm = getPrivileges(user);
        return getGrantedAuthorities(perm);
    }

    private List<String> getPrivileges(User user) {
        List<String> privileges = new ArrayList<>();
        List<Permission> rolePermissions = permissionRepository.findUserPermissionByUserId(user.getId());

        for (Permission per : rolePermissions) {
            privileges.add(per.getName());
        }
        return privileges.stream().collect(Collectors.toList());
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}