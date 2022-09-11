package com.majidmostafavi.jakartaee10prime12.basicInfo.entity;

import com.majidmostafavi.jakartaee10prime12.core.entity.AbstractEntity;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractPerson extends AbstractEntity {


    private Long id;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private City city;
    private String adress;
    private String phone;
    private String fatherName;
    private Date birthdate;
    private Long gender;
    private Long categoryId;
    private String personCode;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    @Column(name ="NAME")
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    @Column(name ="LASTNAME")
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    @Column(name ="NATIONAL_CODE")
    public String getNationalCode() {return nationalCode;}
    public void setNationalCode(String nationalCode) {this.nationalCode = nationalCode;}

    @ManyToOne
    @JoinColumn(name ="CITY")
    public City getCity() {return city;}
    public void setCity(City city) {this.city = city;}

    @Column(name ="ADRESS")
    public String getAdress() {return adress;}
    public void setAdress(String adress) {this.adress = adress;}

    @Column(name ="PHONE")
    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}

    @Column(name ="FATHER_NAME")
    public String getFatherName() {return fatherName;}
    public void setFatherName(String fatherName) {this.fatherName = fatherName;}

    @Column(name ="BIRTH_DATE")
    public Date getBirthdate() {return birthdate;}
    public void setBirthdate(Date birthdate) {this.birthdate = birthdate;}

    @Column(name ="GENDER")
    public Long getGender() {return gender;}
    public void setGender(Long gender) {this.gender = gender;}

//    @ManyToOne
//    @JoinColumn(name ="CATEGORY_ID", foreignKey = @ForeignKey(name = "FK_CATEGORY_ID"))
    @Column(name = "CATEGORY_ID")
    public Long getCategoryId() {return categoryId;}
    public void setCategoryId(Long categoryId) {this.categoryId = categoryId;}

    @Column(name ="PERSON_CODE")
    public String getPersonCode() {
        return personCode;
    }
    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPerson that = (AbstractPerson) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(nationalCode, that.nationalCode) && Objects.equals(city, that.city) && Objects.equals(adress, that.adress) && Objects.equals(phone, that.phone) && Objects.equals(fatherName, that.fatherName) && Objects.equals(birthdate, that.birthdate) && Objects.equals(gender, that.gender) && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, nationalCode, city, adress, phone, fatherName, birthdate, gender, categoryId);
    }
}
