package com.majidmostafavi.jakartaee10prime12.basicInfo.service;

import com.majidmostafavi.jakartaee10prime12.basicInfo.dao.UsersDao;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Users;
import com.majidmostafavi.jakartaee10prime12.core.exception.DuplicateDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.RelationDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.SaveRecordException;
import com.majidmostafavi.jakartaee10prime12.core.service.AbstractService;
import jakarta.inject.Inject;


public class UserService extends AbstractService {

    @Inject
    UsersDao usersDao;


    public boolean create(Users users) throws DuplicateDataException, RelationDataException, SaveRecordException {
        if (canSave(users)) {
            UserService userDao;
            usersDao.create(users);
        }
        return false;
    }


    public void edit(Users users) throws DuplicateDataException, DuplicateDataException, RelationDataException, SaveRecordException {
        if (canEdit(users))
            usersDao.edit(users);
    }


    public void delete(Users users) throws RelationDataException {
        if (canDelete(users))
            usersDao.delete(users);
    }

    public boolean canSave(Users users) throws DuplicateDataException {
        return true;
    }

    public boolean canEdit(Users users) throws
            DuplicateDataException, RelationDataException, SaveRecordException {
        return true;
    }

    public boolean canDelete(Users users) throws RelationDataException {
        return true;
    }
    public Users findByUsername(String string){
        Users user = usersDao.findByUsername(string);
        if (user != null){
            return user;
        }else {
            return new Users();
        }
    }
    public Users findUserByUsernamePassword(String username,String password){
        try {
            return usersDao.findUserByUsernamePassword(username,password);
        }catch (Exception e){
            System.out.println("No Result");
            return new Users();
        }
    }



}