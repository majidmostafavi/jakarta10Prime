package com.majidmostafavi.jakartaee10prime12.basicInfo.dao;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Year;
import com.majidmostafavi.jakartaee10prime12.core.dao.AbstractDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.Date;
import java.util.List;


public class YearDao extends AbstractDAO<Year> {
    @PersistenceContext(unitName = "coeliac")
    EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<Year> findAllSortByYear(){
        Query query = entityManager.createNamedQuery("findAllSortByYear");
        query.setHint("org.hibernate.cacheable", true);
        return query.getResultList();
    }

    public long countByYear(Date date){
        try{
            Query query = getEntityManager().createNamedQuery("countYearByDate");
            query.setParameter("intendedDate", date);
            return (long) query.getSingleResult();
        }catch (NoResultException e){
            return 0l;
        }catch (NullPointerException e){
            return 0l;
        }
    }

    public long countByNameOrCode(String name, String code){
        try{
            Query query = getEntityManager().createNamedQuery("countYearByNameOrCode");
            query.setParameter("name",name);
            query.setParameter("code",code);
            return (long) query.getSingleResult();
        }catch (NoResultException e){
            return 0l;
        }catch (NullPointerException e){
            return 0l;
        }
    }

    public Year findYearById(Long id) {
        try {
            Query query = getEntityManager().createNamedQuery("findYearById");
            query.setParameter("id", id);
            return (Year) query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }
}
