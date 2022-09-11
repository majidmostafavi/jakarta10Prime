package com.majidmostafavi.jakartaee10prime12.basicInfo.dao;

import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Country;
import com.majidmostafavi.jakartaee10prime12.core.dao.AbstractDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class CountryDao extends AbstractDAO<Country> {

    @PersistenceContext(unitName = "coeliac")
    protected EntityManager entityManager;

    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }


    public long countByNameOrCode (Country country){
        try {
            Query query = entityManager().createNamedQuery("countCountryByNameOrCode");
            query.setParameter("name", country.getName());
            query.setParameter("code", country.getCode());
            return (long) query.getSingleResult();
        }catch (NoResultException e){
            return 0l;
        }catch (NullPointerException e){
            return 0l;
        }
    }

    public long countByNameOrCodeForEdit (Country country){
        try {
            Query query = entityManager().createNamedQuery("countCountryByNameOrCodeForEdit");
            query.setParameter("name", country.getName());
            query.setParameter("code", country.getCode());
            query.setParameter("id", country.getId());
            return (long) query.getSingleResult();
        }catch (NoResultException e){
            return 0l;
        }catch (NullPointerException e){
            return 0l;
        }
    }


    public List<Country> findAll() {
        try {
            Query query = entityManager.createQuery("findAllCountries");
            return query.getResultList();
        }catch (Exception e){
            return new ArrayList<>(0);
        }
    }
}

