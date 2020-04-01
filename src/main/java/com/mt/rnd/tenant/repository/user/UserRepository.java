package com.mt.rnd.tenant.repository.user;


import com.mt.rnd.tenant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndTenant(String username, String tenantId);

}
