package com.mt.rnd.tenant.service.user;

import com.mt.rnd.tenant.model.User;
import com.mt.rnd.tenant.repository.user.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(value = "tenantTransactionManager", rollbackFor = {Throwable.class})
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PermissionRepository permissionRepo;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    private UserPermissionRepository userPermissionRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    private final Logger logger = LogManager.getLogger(UserService.class);

    public User findByUsernameAndTenantId(String username, String tenantId) {
        User user = userRepository.findByUsernameAndTenant(username,
                tenantId);
        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format(
                            "Username not found for domain, "
                                    + "username=%s, tenant=%s",
                            username, tenantId));
        }
        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
