package com.majidmostafavi.jakartaee10prime12.basicInfo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name ="PERSON")
@NamedQueries({
        @NamedQuery(name = "findAllPerson", query = "select u from Person u"),
        @NamedQuery(name = "findPersonByNationalCode", query = "select person from Person person where person.nationalCode=:nationalCode"),
        @NamedQuery(name = "countPersonByPersonId", query = "select count(person) from Person person where person.id not in :id "),
        @NamedQuery(name = "findPersonById", query = "select person from Person person where person.id=:id"),


})
public class Person extends AbstractPerson {
    public Person() {

    }
}
