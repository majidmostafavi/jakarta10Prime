package com.majidmostafavi.jakartaee10prime12.basicInfo.dao;

import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.City;
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
public class CityDao extends AbstractDAO<City> {

    @PersistenceContext(unitName = "coeliac")
    protected EntityManager entityManager;



    @Override
    public EntityManager entityManager() {
        return entityManager;
    }


    public long countByNameOrCode(City city){
        try {
            Query query = entityManager().createNamedQuery("countCityByNameOrCode");
            query.setParameter("name",city.getName());
            query.setParameter("code",city.getCode());
            return (long) query.getSingleResult();
        }catch (NoResultException e){
            return 0l;
        }catch (NullPointerException e){
            return 0l;
        }
    }

    public long countByNameOrCodeForEdit(City city){
        try {
            Query query = entityManager().createNamedQuery("countCityByNameOrCodeForEdit");
            query.setParameter("name",city.getName());
            query.setParameter("code",city.getCode());
            query.setParameter("name",city.getId());
            return (long) query.getSingleResult();
        }catch (NoResultException e){
            return 0l;
        }catch (NullPointerException e){
            return 0l;
        }
    }

    public long countProvince(Province province){
        try {
            Query query = entityManager().createNamedQuery("countProvinceInCity");
            query.setParameter("province",province);
            return (long) query.getSingleResult();
        }catch (NoResultException e){
            return 0l;
        }catch (NullPointerException e){
            return 0l;
        }
    }

    public List<City> findAll() {
        try {
            Query query = entityManager.createQuery("findAllCities");
            return query.getResultList();
        }catch (Exception e){
            return new ArrayList<>(0);
        }
    }
}
