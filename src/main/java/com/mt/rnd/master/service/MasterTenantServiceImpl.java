
package com.mt.rnd.master.service;

import com.mt.rnd.master.model.Tenant;
import com.mt.rnd.master.repository.MasterTenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(value = "masterTransactionManager", rollbackFor = {Throwable.class})
public class MasterTenantServiceImpl implements MasterTenantService {

    @Autowired
    MasterTenantRepository masterTenantRepository;

    @Override
    public Tenant findByTenantId(String tenantId) {
        return masterTenantRepository.findByTenantId(tenantId);
    }

    @Override
    public List<Tenant> findAll() {
        return masterTenantRepository.findAll();
    }

}
