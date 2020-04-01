
package com.mt.rnd.tenant.config;

import com.mt.rnd.master.model.Tenant;
import com.mt.rnd.master.repository.MasterTenantRepository;
import com.mt.rnd.tenant.wrapper.CustomUserDetails;
import com.mt.rnd.util.DataSourceUtil;
import com.mt.rnd.util.TenantContextHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Configuration
public class DataSourceBasedMultiTenantConnectionProviderImpl
        extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    private final Logger logger = LogManager.getLogger(DataSourceBasedMultiTenantConnectionProviderImpl.class);
    private static final long serialVersionUID = 1L;

    @Autowired
    private MasterTenantRepository masterTenantRepo;

    private Map<String, DataSource> dataSourcesMtApp = new TreeMap<>();

    @Override
    protected DataSource selectAnyDataSource() {
        if (dataSourcesMtApp.isEmpty()) {
            List<Tenant> masterTenants = masterTenantRepo.findAll();
            logger.info("selectAnyDataSource() - Total tenants:" + masterTenants.size());
            for (Tenant masterTenant : masterTenants) {
                dataSourcesMtApp.put(masterTenant.getName(),
                        DataSourceUtil.createAndConfigureDataSource(masterTenant));
            }
        }
        return this.dataSourcesMtApp.values().iterator().next();
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        tenantIdentifier = initializeTenantIfLost(tenantIdentifier);

        if (!this.dataSourcesMtApp.containsKey(tenantIdentifier)) {
            List<Tenant> masterTenants = masterTenantRepo.findAll();
            logger.info(
                    "selectDataSource() - tenant:" + tenantIdentifier + " Total tenants:" + masterTenants.size());
            for (Tenant masterTenant : masterTenants) {
                dataSourcesMtApp.put(masterTenant.getName(),
                        DataSourceUtil.createAndConfigureDataSource(masterTenant));
            }
        }
        //check again if tenant exist in map after rescan master_db, if not, throw UsernameNotFoundException
        if (!this.dataSourcesMtApp.containsKey(tenantIdentifier)) {
            logger.warn("Trying to get tenant:" + tenantIdentifier + " which was not found in master db after rescan");
            throw new UsernameNotFoundException(
                    String.format(
                            "Tenant not found after rescan, "
                                    + " tenant=%s",
                            tenantIdentifier));
        }
        return this.dataSourcesMtApp.get(tenantIdentifier);
    }

    private String initializeTenantIfLost(String tenantIdentifier) {
        if (TenantContextHolder.getTenant() == null) {

            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();
            CustomUserDetails customUserDetails = null;
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                customUserDetails = principal instanceof CustomUserDetails ? (CustomUserDetails) principal : null;
            }
            TenantContextHolder.setTenantId(customUserDetails.getTenant());
        }

        if (tenantIdentifier != TenantContextHolder.getTenant()) {
            tenantIdentifier = TenantContextHolder.getTenant();
        }
        return tenantIdentifier;
    }
}
