
package com.mt.rnd.util;

import com.mt.rnd.master.model.Tenant;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;

public final class DataSourceUtil {

    private static final Logger LOG = LogManager.getLogger(DataSourceUtil.class);

    public static DataSource createAndConfigureDataSource(
            Tenant tenant) {
        HikariDataSource ds = new HikariDataSource();
        ds.setUsername(tenant.getDbUserName());
        ds.setPassword(tenant.getDbPassword());
        String url = "jdbc:mysql://" + tenant.getDbServerURL() + ":" + tenant.getDbServerPort() + "/" +
                tenant.getDbName() + "?useSSL=" + tenant.isSslEnabled();

        ds.setJdbcUrl(url);
        ds.setDriverClassName("com.mysql.jdbc.Driver");

        // HikariCP settings - could come from the master_tenant table but
        // hardcoded here for brevity
        // Maximum waiting time for a connection from the pool
        ds.setConnectionTimeout(tenant.getPoolConfiguration().getConnectionTimeout());

        // Minimum number of idle connections in the pool
        ds.setMinimumIdle(tenant.getPoolConfiguration().getMinimumIdle());

        // Maximum number of actual connection in the pool
        ds.setMaximumPoolSize(tenant.getPoolConfiguration().getMaxPoolSize());

        // Maximum time that a connection is allowed to sit idle in the pool
        ds.setIdleTimeout(tenant.getPoolConfiguration().getIdleTimeout());
        ds.setLeakDetectionThreshold(tenant.getPoolConfiguration().getLeadDetectionThreshold());

        // Setting up a pool name for each tenant datasource
        ds.setPoolName(tenant.getPoolConfiguration().getPoolName());
        LOG.info("Configured datasource:" + tenant.getName()
                + ". Connection pool name:" + tenant.getPoolConfiguration().getPoolName());
        return ds;
    }
}
