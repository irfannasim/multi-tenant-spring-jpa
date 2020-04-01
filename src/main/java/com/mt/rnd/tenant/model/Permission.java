package com.mt.rnd.tenant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PERMISSION")
public class Permission extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IS_ACTIVE", columnDefinition = "boolean default true", nullable = false)
    private Boolean active;

    @JsonIgnore
    @OneToMany(mappedBy = "permission")
    private List<RolePermission> rolePermissions;

    @JsonIgnore
    @OneToMany(mappedBy = "permission")
    private List<UserPermission> userPermissionList;

    public Permission() {
    }

    public Permission(String name, String description, String url, Boolean active) {
        this.name = name;
        this.description = description;
        this.active = active;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    public List<UserPermission> getUserPermissionList() {
        return userPermissionList;
    }

    public void setUserPermissionList(List<UserPermission> userPermissionList) {
        this.userPermissionList = userPermissionList;
    }
}
