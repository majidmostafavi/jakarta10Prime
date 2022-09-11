package com.majidmostafavi.jakartaee10prime12.basicInfo.entity;


import com.majidmostafavi.jakartaee10prime12.core.entity.AbstractEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@MappedSuperclass
public abstract class AbstractRole extends AbstractEntity implements Comparable<AbstractRole> {


    private Long id;
    private String roleTitle;
    private Set<Permission> permissions = new HashSet<Permission>();

    public AbstractRole() {
    }

    public AbstractRole(String roleTitle, Set<Permission> permissions) {
        this.roleTitle= roleTitle;
        this.permissions = permissions;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME",nullable = false,unique = true)
    public String getRoleTitle() {
        return roleTitle;
    }
    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "ROLE_PERMISSION",
            joinColumns = {@JoinColumn(name = "ROLE_ID",referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "PERMISSION_ID",referencedColumnName = "id")})
    public Set<Permission> getPermissions() {
        return permissions;
    }
    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "[name=" + roleTitle + "]";
    }


    public int compareTo(AbstractRole role) {
        if(role.getRoleTitle()!=null){
            return roleTitle.compareTo(role.roleTitle);
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractRole that = (AbstractRole) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(roleTitle, that.roleTitle) &&
                Objects.equals(permissions, that.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleTitle, permissions);
    }
}