
package com.mt.rnd.master.service;

import com.mt.rnd.master.model.Tenant;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MasterTenantService {

    Tenant findByTenantId(@Param("tenantId") String tenantId);

    List<Tenant> findAll();
}
