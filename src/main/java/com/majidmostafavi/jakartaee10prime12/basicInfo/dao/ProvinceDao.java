package com.majidmostafavi.jakartaee10prime12.basicInfo.dao;


import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.City;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Country;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Province;
import com.majidmostafavi.jakartaee10prime12.core.dao.AbstractDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class ProvinceDao extends AbstractDAO<Province> {

    @PersistenceContext(unitName = "coeliac")
    protected EntityManager entityManager;




    public EntityManager entityManager() {
        return entityManager;
    }

    public Long countCountyByNameOrCode (Province province){
        try {
            Query query = entityManager().createNamedQuery("countProvinceByNameOrCode");
            query.setParameter("name", province.getName());
            query.setParameter("code", province.getCode());
            return (Long) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }catch (NullPointerException e){
            return null;
        }
    }

    public Long countCountyByNameOrCodeForEdit (Province province){
        try {
            Query query = entityManager().createNamedQuery("countProvinceByNameOrCodeForEdit");
            query.setParameter("name", province.getName());
            query.setParameter("code", province.getCode());
            query.setParameter("id", province.getId());
            return (Long) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }catch (NullPointerException e){
            return null;
        }
    }

    public Long countCity (City city){
        try {
            Query query = entityManager().createNamedQuery("countCity");
            query.setParameter("city", city);
            return (Long) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }catch (NullPointerException e){
            return null;
        }
    }

    public Long countCountriesInProvinces(Country country){
        try {
            Query query = entityManager().createNamedQuery("countCountriesInProvinces");
            query.setParameter("country", country);
            return (Long) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }catch (NullPointerException e){
            return null;
        }
    }

    public Long findProvinceInCity(Province province){
        try {
            Query query = entityManager().createNamedQuery("findProvinceInCity");
            query.setParameter("province", province);
            return (Long) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }catch (NullPointerException e){
            return null;
        }
    }

    public List<Province> findAll() {
        try {
            Query query = entityManager.createQuery("findAllProvinces");
            return query.getResultList();
        }catch (Exception e){
            return new ArrayList<>(0);
        }
    }
}

