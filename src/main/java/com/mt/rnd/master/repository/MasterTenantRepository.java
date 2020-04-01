package com.mt.rnd.master.repository;

import com.mt.rnd.master.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterTenantRepository extends JpaRepository<Tenant, Long> {

    @Query("select p from Tenant p where p.name = :name")
    Tenant findByTenantId(@Param("name") String tenantId);

}
