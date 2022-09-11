package com.majidmostafavi.jakartaee10prime12.basicInfo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "PERMISSION")
@NamedQueries({
        @NamedQuery(name = "findPermissionByKey", query = "select permission from Permission permission where permission.key=:key"),
        @NamedQuery(name = "existsPermissionByTitle", query = "select count(p) from Permission p where p.title= :title"),
        @NamedQuery(name = "existsPermissionByShortTitle", query = "select count(p) from Permission p where p.key= :shortTitle"),
        @NamedQuery(name = "existsPermissionByKey", query = "select count(p) from Permission p where p.key= :key"),
       /// @NamedQuery(name = "findPermissionsByUsername", query = "SELECT permissions FROM Permission permissions where permissions in (select roles.permissions from CoRole roles where roles in (select roleorgs.roles from CoRoleOrganization roleorgs where roleorgs in (select u.roleOrganizations from CoUsers u where u.userName=:userName) and roleorgs.organization=:organization))"),

})
public class Permission extends AbstractPermission {


    @Override
    public boolean implies(Permission permission) {
        return false;
    }
}
