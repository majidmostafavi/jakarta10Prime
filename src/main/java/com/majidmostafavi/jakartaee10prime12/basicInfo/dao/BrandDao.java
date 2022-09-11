package com.majidmostafavi.jakartaee10prime12.basicInfo.dao;

import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Brand;
import com.majidmostafavi.jakartaee10prime12.core.dao.AbstractDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

public class BrandDao extends AbstractDAO<Brand> {

    @PersistenceContext(unitName = "coeliac")
    protected EntityManager entityManager;
    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }

    public List<Brand> findBrandAllActiveAndSort() {
        try {
            Query query = entityManager().createNamedQuery("findBrandAllActiveAndSort");
            query.setParameter("active",true);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<>();
        }catch (NullPointerException e){
            return new ArrayList<>();
        }
    }


}
