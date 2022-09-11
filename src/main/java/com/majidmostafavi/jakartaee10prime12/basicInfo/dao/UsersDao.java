package com.majidmostafavi.jakartaee10prime12.basicInfo.dao;

import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Users;
import com.majidmostafavi.jakartaee10prime12.core.dao.AbstractDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.*;

@RequestScoped
public class UsersDao extends AbstractDAO<Users> {

    @PersistenceContext(unitName = "coeliac")
    EntityManager entityManager;


    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void disableUser(String username) {
        Query query = getEntityManager().createNamedQuery("disableUser");
        query.setParameter("username", username);
        query.executeUpdate();
    }


    public Users findByUsername(String username) {
        try {
            Query query = getEntityManager().createNamedQuery("findUserByUsername");
            query.setParameter("username", username.toLowerCase());
            Users users = (Users) query.getSingleResult();
            return users;
        } catch (NullPointerException  ex) {
            ex.printStackTrace();
            return new Users();
        }catch (NoResultException ex){
            ex.printStackTrace();
            return new Users();
        }
    }

    public Users findUserByUsernamePassword(String username,String password) {
        try {
            Query query = getEntityManager().createNamedQuery("findUserByUsernamePassword");
            query.setParameter("username", username.toLowerCase());
//                query.setParameter("password",Users.hashSHA1(password));
            Users users = (Users) query.getSingleResult();
//            entityManager.detach(users);
            return users;
        } catch (NullPointerException  ex) {
            ex.printStackTrace();
            return new Users();
        }catch (NoResultException ex){
            ex.printStackTrace();
            return new Users();
        }
    }


  /*  public String findPasswordByUsername(String username) {
        try {
            Query query = getEntityManager().createNamedQuery("findPasswordByUsername");
            query.setParameter("username", username.toLowerCase());
            query.setParameter("active", true);
            query.setHint("org.hibernate.cacheable", true);
            return (String) query.getSingleResult();
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public int changePassword(Users users){
        try {
            Query query = entityManager.createNamedQuery("updateUserPassword");
            query.setParameter("newPassword",Users.hashSHA1(users.getPassword()));
            query.setParameter("id",users.getId());
            return query.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public long countUserByUsername(String username) {
        try {
            Query query = entityManager.createNamedQuery("countUserByUsername");
            query.setParameter("userName", username);
            return (long) query.getSingleResult();
        } catch (Exception e) {
            return 0l;
        }*/
    @Override
    protected EntityManager entityManager() {
        return null;
    }
}

/*
        public List<Users> search(String firstName,
                                    String lastName,
                                    String userName,
                                    String nationalCode,
                                    Role role,
                                    int from, int page){

            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Users> criteriaQuery = criteriaBuilder.createQuery(Users.class);
*/
       /*     Root<Users> usersRoot = criteriaQuery.from(Users.class);
            Join<Users, Person> personnelJoin = usersRoot.join(Users_.person,JoinType.LEFT);
            criteriaQuery.select(usersRoot).distinct(true);
            Predicate predicate = criteriaBuilder.conjunction();

            predicate = criteriaBuilder.and(predicate,criteriaBuilder.isTrue(roleJoin.get(Role_.active)));


            if(firstName !=null &&  !firstName.isEmpty()){
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.or(
                        criteriaBuilder.like(personnelJoin.get(Person_.firstName),"%" + PersianUTF.farsiRevision(firstName) + "%"),
                        criteriaBuilder.like(personnelJoin.get(Person_.firstName),"%" + PersianUTF.arabicRevision(firstName) + "%")
                ));
            }
            if(lastName != null &&  !lastName.isEmpty()){
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.or(
                        criteriaBuilder.like(personnelJoin.get(Person_.lastName),"%" + PersianUTF.farsiRevision(lastName) + "%"),
                        criteriaBuilder.like(personnelJoin.get(Person_.lastName),"%" + PersianUTF.arabicRevision(lastName) + "%")
                ));
            }
            if(userName != null && !userName.isEmpty()){
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.like(usersRoot.get(Users_.userName), "%" + userName + "%"));
            }
            if(nationalCode != null &&  !nationalCode.isEmpty()){
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(personnelJoin.get(Person_.nationalCode),  nationalCode));
            }
            if (role != null && role.getId() != null){
                predicate= criteriaBuilder.and(predicate,criteriaBuilder.equal(roleJoin.get(Role_.id), role.getId()));
            }


            criteriaQuery.where(predicate);
            TypedQuery<Users> queryResult = getEntityManager().createQuery(criteriaQuery);*/

            //append sizing
      /*      queryResult.setFirstResult(from);
            if (page > 0){
                queryResult.setMaxResults(page);
            }

            return queryResult.getResultList();*/
        /**/


     /*   public Long countSearch(String firstName,
                                String lastName,
                                String userName,
                                String nationalCode,
                                Role role)
            {

            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);*/

            //create parametric where clause
           /* Root<Users> usersRoot = criteriaQuery.from(Users.class);
            Join<Users,Person> personnelJoin = usersRoot.join(Users_.person,JoinType.LEFT);
            criteriaQuery.select(criteriaBuilder.countDistinct(usersRoot));
            Predicate predicate = criteriaBuilder.conjunction();

            predicate = criteriaBuilder.and(predicate,criteriaBuilder.isTrue(roleJoin.get(Role_.active)));




            if(firstName !=null &&  !firstName.isEmpty()){
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.or(
                        criteriaBuilder.like(personnelJoin.get(Person_.firstName),"%" + PersianUTF.farsiRevision(firstName) + "%"),
                        criteriaBuilder.like(personnelJoin.get(Person_.firstName),"%" + PersianUTF.arabicRevision(firstName) + "%")
                ));
            }
            if(lastName != null &&  !lastName.isEmpty()){
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.or(
                        criteriaBuilder.like(personnelJoin.get(Person_.lastName),"%" + PersianUTF.farsiRevision(lastName) + "%"),
                        criteriaBuilder.like(personnelJoin.get(Person_.lastName),"%" + PersianUTF.arabicRevision(lastName) + "%")
                ));
            }
            if(userName != null && !userName.isEmpty()){
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.like(usersRoot.get(Users_.userName), "%" + userName + "%"));
            }
            if(nationalCode != null &&  !nationalCode.isEmpty()){
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(personnelJoin.get(Person_.nationalCode),  nationalCode));
            }
            if (role != null && role.getId() != null){
                predicate= criteriaBuilder.and(predicate,criteriaBuilder.equal(roleJoin.get(Role_.id), role.getId()));
            }*/
            //append where clause
          /*  criteriaQuery.where(predicate);
            TypedQuery<Long> queryResult = getEntityManager().createQuery(criteriaQuery);

            return queryResult.getSingleResult();
        }
*/

