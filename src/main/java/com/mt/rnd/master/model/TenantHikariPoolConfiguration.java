package com.mt.rnd.master.model;

import javax.persistence.*;

@Entity
@Table(name = "TENANT_HIKARI_POOL_CONFIGURATION")
public class TenantHikariPoolConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "POOL_NAME")
    private String poolName;

    @Column(name = "MINIMUM_IDLE")
    private int minimumIdle;

    @Column(name = "IDLE_TIMEOUT")
    private long idleTimeout;

    @Column(name = "CONNECTION_TIMEOUT")
    private long connectionTimeout;

    @Column(name = "MAX_LIFE_TIME")
    private long maxLifeTime;

    @Column(name = "LEAK_DETECTION_THRESHOLD")
    private long leadDetectionThreshold;

    @Column(name = "MAX_POOL_SIZE")
    private int maxPoolSize;

    public TenantHikariPoolConfiguration() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public int getMinimumIdle() {
        return minimumIdle;
    }

    public void setMinimumIdle(int minimumIdle) {
        this.minimumIdle = minimumIdle;
    }

    public long getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(long idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public long getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public long getMaxLifeTime() {
        return maxLifeTime;
    }

    public void setMaxLifeTime(long maxLifeTime) {
        this.maxLifeTime = maxLifeTime;
    }

    public long getLeadDetectionThreshold() {
        return leadDetectionThreshold;
    }

    public void setLeadDetectionThreshold(long leadDetectionThreshold) {
        this.leadDetectionThreshold = leadDetectionThreshold;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }
}