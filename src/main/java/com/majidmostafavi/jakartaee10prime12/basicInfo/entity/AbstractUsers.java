package com.majidmostafavi.jakartaee10prime12.basicInfo.entity;

import com.majidmostafavi.jakartaee10prime12.core.entity.AbstractEntity;
import jakarta.persistence.*;
import java.security.MessageDigest;

@MappedSuperclass
public abstract class AbstractUsers extends AbstractEntity {


    private Long id;
    private String userName;
    private String password;
    private Person person;

    public AbstractUsers(String userName, String password) {
        this.userName = userName;
//        this.password = hashSHA1(password);
    }

    public AbstractUsers() {
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


    @Column(name = "username", length = 128, nullable = false)
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Column(name = "password", length = 128, unique = false, nullable = false, insertable = true, updatable = false)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="person_id")
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return true;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.userName != null ? this.userName.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "[name=" +
                userName + "] : " + password;
    }

//    public static String hashSHA1(String password) {
//        try {
//            MessageDigest cript = MessageDigest.getInstance("SHA-1");
//            cript.reset();
//            return Hex.encodeHexString(cript.digest(password.getBytes("UTF-8")));
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return password;
//        }
//    }


}
