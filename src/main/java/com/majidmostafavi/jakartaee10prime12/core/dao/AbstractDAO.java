package com.majidmostafavi.jakartaee10prime12.core.dao;

import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.City;
import com.majidmostafavi.jakartaee10prime12.core.entity.AbstractEntity;
import com.majidmostafavi.jakartaee10prime12.core.enumration.Status;
import com.majidmostafavi.jakartaee10prime12.core.exception.DuplicateDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.ForeignKeyException;
import com.majidmostafavi.jakartaee10prime12.core.exception.RelationDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.SaveRecordException;
import com.majidmostafavi.jakartaee10prime12.core.utils.ExceptionUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDAO<T> {

   protected abstract EntityManager entityManager();


/*    public List<T> find(int id) {
        List<T> list;
        Query query = em.createQuery("select e from " + persistentClass.getSimpleName() + " e" + " where e.id =:id").setParameter("id", id);
        list = query.getResultList();
        return list;
    }

    public List<T> findAll() {
        List<T> list;
        Query query = em.createQuery("select e from " + persistentClass.getSimpleName() + " e");
        list = query.getResultList();
        return list;
    }*/

    @Transactional
    public  <T>  T create(T entity) throws DuplicateDataException, SaveRecordException {
        try {
            entityManager().persist(entity);
            return entity;
        } catch (Exception e) {
            String uniqueException = ExceptionUtils.isUniqueException(e);
            if (uniqueException != null) {
                throw new DuplicateDataException(uniqueException);
            } else {
                e.printStackTrace();
                throw new SaveRecordException();
            }
        }
    }

    @Transactional
    public <T>  T  edit (T entity) throws DuplicateDataException, RelationDataException,SaveRecordException {
        try {
            entity=entityManager().merge(entity);
            return entity;
        }catch (Exception e){
            String uniqueException = ExceptionUtils.isUniqueException(e);
            if (uniqueException != null) {
                throw new DuplicateDataException(uniqueException);
            } else {
                throw new SaveRecordException();
            }
        }
    }

    @Transactional
    public <T> boolean delete (T entity) throws ForeignKeyException,RelationDataException {
        try {
            entityManager().remove(entityManager().merge(entity));
            return true;
        } catch (Exception e) {
            String foreignKeyException = ExceptionUtils.isForeignKeyException(e);
            if (foreignKeyException != null) {
                throw new ForeignKeyException(foreignKeyException);
            } else {
                throw new RelationDataException();
            }
        }

    }

    public boolean changeStatus(Class<?> clazz,Long id, Status status){
        try {

            String tableName = clazz.getAnnotation(Table.class).name();
            Field[] fields = clazz.getSuperclass().getDeclaredFields();
            String stateColumn ="";

            stateColumn = Arrays.stream(fields).filter(field -> field.getType().equals(Status.class)).findFirst().get().getName();
            StringBuffer s = new StringBuffer();
            s.append("update" + " ");
            s.append(tableName);
            s.append(" ");
            s.append("set" + " ");
            s.append(stateColumn);
            s.append("=");
            s.append(status.ordinal());
            s.append(" where ");
            if (status.equals(Status.active)) {
                s.append(stateColumn + " = ");
                s.append(Status.deleted.ordinal());
                s.append(" and ");
            }
            s.append("id=");
            s.append(id);

            Query q = entityManager().createNativeQuery(s.toString());
            q.executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public <T extends AbstractEntity> boolean enableObject(T t) throws Exception{
        try {
            t.setActive(true);
            t=entityManager().merge(t);
            /*if (t instanceof CmIndependent){
                Map<Long, Class<?>> cacheMap = new HashMap<Long, Class<?>>();
                cacheMap.put(((CmIndependent) t).getId(), t.getClass());
                //CacheQueryUtils.updateIndependentAccount(cacheMap,t);
            }*/
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw  new Exception("Can not Enable Object");
        }
    }
    public <T extends AbstractEntity> boolean disableObject(T t) throws Exception{
        try {
            t.setActive(false);
            entityManager().merge(t);
            /*if (t instanceof CmIndependent){
                Map<Long, Class<?>> cacheMap = new HashMap<Long, Class<?>>();
                cacheMap.put(((CmIndependent) t).getId(), t.getClass());
                //CacheQueryUtils.updateIndependentAccount(cacheMap,t);
            }*/
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw  new Exception("Can not Disable Object");
        }
    }
}
