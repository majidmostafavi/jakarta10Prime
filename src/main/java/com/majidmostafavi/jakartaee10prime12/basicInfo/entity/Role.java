package com.majidmostafavi.jakartaee10prime12.basicInfo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "ROLE")
@NamedQueries({
        @NamedQuery(name = "findAllRoles", query = "select role from Role role "),
        @NamedQuery(name = "findAllActiveRoles", query = "select role from Role role where role.active=true"),
        @NamedQuery(name = "findRoleByTitle", query = "select role from Role role where role.roleTitle=:title"),
        @NamedQuery(name = "findPermissionsByRole", query = "select role.permissions from Role role where role.id=:id"),
        @NamedQuery(name = "findRoleByPermission", query = "select role from Role role where :permission in elements(role.permissions) "),
        @NamedQuery(name="permissionInRole", query = "select permission from Role role inner  join role.permissions permission where role in (:roles)"),
        @NamedQuery(name="countRoleByName", query = "select count(role) from Role role where role.roleTitle=:roleTitle"),
        @NamedQuery(name="countRoleByNameEdit", query = "select count(role) from Role role where role.roleTitle=:roleTitle and role.id not in :id")
})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role extends AbstractRole {
    public Role() {
    }
}