package com.majidmostafavi.jakartaee10prime12.basicInfo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "USERS")
@NamedQueries({
        @NamedQuery(name = "disableUser", query = "update Users u set u.active=false where u.userName=:username"),
        @NamedQuery(name = "findAllUsers", query = "select u from Users u"),
        @NamedQuery(name = "findUserByUsername", query = "select u from Users u where (u.userName)=:username and u.active=true"),
//            @NamedQuery(name = "findUserDTOByUsername",query = " select new ir.ac.tums.coeliac.basicInfo.dto.UserDTO(u.userName,u.password,u.active,u.person.id, p.active) from Users u inner join u.person p where (u.userName)=:username and u.active=:active"),
        @NamedQuery(name = "findPasswordByUsername", query = "select u.password from Users u where lower(u.userName)=:username and u.active=:active"),
        @NamedQuery(name = "findPersonnelByUsername", query = "select u.person from Users u where lower(u.userName)=:userName"),
        @NamedQuery(name = "countPersonnelInUser", query = "select count(user)  from Users user where user.person.id =:id"),
        @NamedQuery(name = "findUserByPersonnel", query = "select user from Users user where user.person =:personnel"),
//            @NamedQuery(name = "findCandidatesPermissions", query = "SELECT role.permissions FROM Users user " +" where user.userName=:userName "),
        @NamedQuery(name = "updateUserPassword", query = "update Users user set user.password=:newPassword where user.id=:id"),
        @NamedQuery(name = "countUserByUsername", query = "select count(user) from Users  user where user.userName=:userName"),
        @NamedQuery(name = "findUserByUsernamePassword", query = "select u from Users u where u.userName=:username and u.password=:password")
               })


@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Users extends AbstractUsers{
    public static final String DEFAULT_PERMISSIONS_NAMED_QUERY = "query.permission_named_query";

    public Users() {

    }
}
