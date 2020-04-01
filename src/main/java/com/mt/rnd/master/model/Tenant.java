package com.mt.rnd.master.model;

import javax.persistence.*;

@Entity
@Table(name = "TENANTS")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DB_NAME")
    private String dbName;

    @Column(name = "DB_USERNAME")
    private String dbUserName;

    @Column(name = "DB_PASSWORD")
    private String dbPassword;

    @Column(name = "DB_SERVER_URL")
    private String dbServerURL;

    @Column(name = "DB_SERVER_PORT")
    private String dbServerPort;

    @Column(name = "IS_SSL_ENABLED", columnDefinition = "boolean default false", nullable = false)
    private boolean sslEnabled;

    @OneToOne
    @JoinColumn(name = "POOL_CONFIGURATION_ID")
    private TenantHikariPoolConfiguration poolConfiguration;

    public Tenant() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbServerURL() {
        return dbServerURL;
    }

    public void setDbServerURL(String dbServerURL) {
        this.dbServerURL = dbServerURL;
    }

    public String getDbServerPort() {
        return dbServerPort;
    }

    public void setDbServerPort(String dbServerPort) {
        this.dbServerPort = dbServerPort;
    }

    public boolean isSslEnabled() {
        return sslEnabled;
    }

    public void setSslEnabled(boolean sslEnabled) {
        this.sslEnabled = sslEnabled;
    }

    public TenantHikariPoolConfiguration getPoolConfiguration() {
        return poolConfiguration;
    }

    public void setPoolConfiguration(TenantHikariPoolConfiguration poolConfiguration) {
        this.poolConfiguration = poolConfiguration;
    }
}