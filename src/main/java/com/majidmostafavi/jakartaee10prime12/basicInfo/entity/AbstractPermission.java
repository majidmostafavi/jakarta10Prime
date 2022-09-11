package com.majidmostafavi.jakartaee10prime12.basicInfo.entity;

import com.majidmostafavi.jakartaee10prime12.core.entity.AbstractEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
public abstract class AbstractPermission extends AbstractEntity implements Comparable<AbstractPermission> {


    private Long id;
    private String key;
    private String title;
    private Set<Role> roles = new HashSet<Role>(0);

    // Constructors
    /** default constructor */
    public AbstractPermission() {
    }

    public AbstractPermission(String key, String title) {
        this.key = key;
        this.title=title;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "KEY",nullable = false,unique = true)
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    @Column(name = "NAME",nullable = false,unique = false)
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    @ManyToMany(mappedBy = "permissions",cascade = {})
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractPermission)) return false;

        AbstractPermission that = (AbstractPermission) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return key != null ? key.equals(that.key) : that.key == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        return result;
    }

    public int compareTo(AbstractPermission o) {
        return title.compareTo(o.title);
    }

    @Override
    public String toString() {
        return title;
    }

    public abstract boolean implies(Permission permission);
}


