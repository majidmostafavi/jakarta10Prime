package com.majidmostafavi.jakartaee10prime12.basicInfo.dao;

import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.DTO.PersonDTO;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Person;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Person_;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Supplier;
import com.majidmostafavi.jakartaee10prime12.basicInfo.enums.GenderEnum;
import com.majidmostafavi.jakartaee10prime12.core.dao.AbstractDAO;
import com.majidmostafavi.jakartaee10prime12.core.utils.PersianUTF;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class PersonDao extends AbstractDAO<Person> {


    @RequestScoped

        @PersistenceContext(unitName = "coeliac")
        protected EntityManager entityManager;

        @Override
        protected EntityManager entityManager() {
            return entityManager;
        }
    public List<Person> findAllPerson() {
        Query query =entityManager.createNamedQuery("findAllPerson");
        return  query.getResultList();
    }


    public Person findPersonByNationalCode(String nationalCode){
        try {
            Query query= entityManager.createNamedQuery("findPersonByNationalCode");
            query.setParameter("nationalCode",nationalCode);
            return (Person)query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }catch (NullPointerException e){
            return null;
        }
    }

        //todo : farnaz
    public Person findPersonById(Long id) {
        try {
            Query query= entityManager.createNamedQuery("findPersonById");
            query.setParameter("id",id);
            return (Person)query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }catch (NullPointerException e){
            return null;
        }
    }


    public List<PersonDTO> search(String firstName,
                                  String lastName,
                                  String fatherName,
                                  String nationalCode,
                                  String personCode,
                                  GenderEnum gender,
                                  String mobile,
                                  Boolean active,
                                  int from, int page) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<PersonDTO> criteriaQuery = criteriaBuilder.createQuery(PersonDTO.class);

        //create parametric where clause
        Root<Person> personRoot = criteriaQuery.from(Person.class);

        criteriaQuery.select(
                criteriaBuilder.construct(PersonDTO.class,
                        personRoot.get(Person_.id),
                        personRoot.get(Person_.firstName),
                        personRoot.get(Person_.lastName),
                        personRoot.get(Person_.personCode),
                        personRoot.get(Person_.nationalCode),
                        personRoot.get(Person_.active)));

        Predicate predicate = criteriaBuilder.conjunction();
        if (active!=null){
            predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(personRoot.get(Person_.active),active));
        }
        if (nationalCode != null && !nationalCode.isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(personRoot.get(Person_.nationalCode), nationalCode + "%"));
        }
        if (personCode != null && !personCode.isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(personRoot.get(Person_.personCode), personCode + "%"));
        }
        if (lastName != null && !lastName.isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.or(
                    criteriaBuilder.like(personRoot.get(Person_.lastName),"%" + PersianUTF.farsiRevision(lastName) + "%"),
                    criteriaBuilder.like(personRoot.get(Person_.lastName),"%" + PersianUTF.arabicRevision(lastName)+ "%")
            ));
        }
        if (firstName != null && !firstName.isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.or(
                    criteriaBuilder.like(personRoot.get(Person_.firstName),"%" + PersianUTF.farsiRevision(firstName) + "%"),
                    criteriaBuilder.like(personRoot.get(Person_.firstName),"%" + PersianUTF.arabicRevision(firstName) + "%")
            ));
        }
        if (fatherName != null && !fatherName.isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.or(
                    criteriaBuilder.like(personRoot.get(Person_.fatherName),"%" + PersianUTF.farsiRevision(fatherName) + "%"),
                    criteriaBuilder.like(personRoot.get(Person_.fatherName),"%" + PersianUTF.arabicRevision(fatherName) + "%")
            ));
        }
        if (gender != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(personRoot.get(Person_.gender), gender));
        }
        if (mobile != null && !mobile.isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(personRoot.get(Person_.PHONE), mobile + "%"));
        }



        //append where clause
        criteriaQuery.where(predicate);
        criteriaQuery.orderBy(criteriaBuilder.asc(personRoot.get(Person_.lastName)));
        TypedQuery<PersonDTO> queryResult = entityManager().createQuery(criteriaQuery);

        //append sizing
        queryResult.setFirstResult(from);
        if (page > 0){
            queryResult.setMaxResults(page);
        }

        return queryResult.getResultList();
    }
}

