package com.majidmostafavi.jakartaee10prime12.basicInfo.entity.DTO;

public class PersonDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String personCode;
    private String nationalCode;
    private boolean active;

    public PersonDTO(Long id, String firstName, String lastName, String personCode, String nationalCode, boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personCode = personCode;
        this.nationalCode = nationalCode;
        this.active = active;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonCode() {
        return personCode;
    }
    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getNationalCode() {
        return nationalCode;
    }
    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
