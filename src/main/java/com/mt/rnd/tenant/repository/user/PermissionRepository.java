package com.mt.rnd.tenant.repository.user;

import com.mt.rnd.tenant.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Query("SELECT DISTINCT p FROM Permission p INNER JOIN p.rolePermissions rp INNER JOIN rp.role r INNER JOIN r.userRoles ur INNER JOIN ur.user u WHERE u.id=:userId")
    List<Permission> findUserPermissionByUserId(@Param("userId") Long userId);

}
