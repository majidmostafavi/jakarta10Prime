package com.majidmostafavi.jakartaee10prime12.basicInfo.dao;

import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Person;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Supplier;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Supplier_;
import com.majidmostafavi.jakartaee10prime12.core.dao.AbstractDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class SupplierDao  extends AbstractDAO<Supplier> {
    @PersistenceContext(unitName = "coeliac")
    protected EntityManager entityManager;

    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }
    public List<Supplier> findAll() {
        try {
            Query query = entityManager.createQuery("findAllSupplier");
            return query.getResultList();
        }catch (Exception e){
            return new ArrayList<>(0);
        }
    }
    public List<Supplier> findAllActive() {
        try {
            Query query = entityManager.createQuery("findAllActiveSupplier");
            return query.getResultList();
        }catch (Exception e){
            return new ArrayList<>(0);
        }
    }

  public List<Supplier> findSupplier(Person person,String name,String economicCode,String mobile,Boolean active){
        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        CriteriaQuery<Supplier> criteriaQuery = criteriaBuilder.createQuery(Supplier.class);

        Root<Supplier> supplierRoot = criteriaQuery.from(Supplier.class);
        Join<Supplier,Person> personJoin = supplierRoot.join(Supplier_.marketer);
        Predicate predicate = criteriaBuilder.conjunction();

        if (person != null && person.getId() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(supplierRoot.get(Supplier_.marketer), person));
        }
        if(name !=null){
            predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(supplierRoot.get(Supplier_.name),name));
        }
        if(economicCode!=null){
            predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(supplierRoot.get(Supplier_.economicCode),economicCode));
        }
        if(mobile!=null){
            predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(supplierRoot.get(Supplier_.mobile),mobile));
        }
        if (active!=null){
            if (active){
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.isTrue(supplierRoot.get(Supplier_.active)));
            }else {
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.isFalse(supplierRoot.get(Supplier_.active)));
            }
        }

        criteriaQuery.where(predicate);
        Query queryResult = entityManager.createQuery(criteriaQuery);
        return queryResult.getResultList();
    }
    public int changeActiveSupplier(Long supplierId, boolean active) {
        Query query = entityManager.createNamedQuery("updateActiveSupplier");
        query.setParameter("id", supplierId);
        query.setParameter("active", active);
        return query.executeUpdate();
    }

}
