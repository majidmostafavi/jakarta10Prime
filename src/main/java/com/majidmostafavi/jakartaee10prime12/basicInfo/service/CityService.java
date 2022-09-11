package com.majidmostafavi.jakartaee10prime12.basicInfo.service;


import com.majidmostafavi.jakartaee10prime12.basicInfo.dao.CityDao;
import com.majidmostafavi.jakartaee10prime12.basicInfo.dao.ProvinceDao;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.City;
import com.majidmostafavi.jakartaee10prime12.core.exception.DuplicateDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.RelationDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.SaveRecordException;
import com.majidmostafavi.jakartaee10prime12.core.service.AbstractService;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class CityService extends AbstractService {

    @Inject
    CityDao cityDao;
    @Inject
    ProvinceDao provinceDao;

    public void create(City city) throws DuplicateDataException  ,SaveRecordException {
        if (canSave(city))
            cityDao.create(city);
    }
    public void edit(City city) throws DuplicateDataException, RelationDataException, SaveRecordException {
        if (canEdit(city))
            cityDao.edit(city);
    }
    public void delete (City city) throws RelationDataException{
        if (canDelete(city))
            cityDao.delete(city);

    }

    public boolean canSave(City city) throws DuplicateDataException{
        if (cityDao.countByNameOrCode(city) == 0l){
            return true;
        }else {
            throw new DuplicateDataException();
        }
    }

    public boolean canEdit(City city) throws DuplicateDataException,RelationDataException,SaveRecordException{
        boolean b = false;
        if (cityDao.countByNameOrCodeForEdit(city) == 0l){
            b =  true;
        }else {
            throw new DuplicateDataException();
        }

        return b;
    }

    public boolean canDelete(City city) throws RelationDataException{
        return true;
    }

    public List<City> findAll() {
        return cityDao.findAll();
    }

//    public List<City> findAll(){
//        try{
//            return cityDao.findAll();
//        }catch (Exception e){
//            logger.error("No Result" , e);
//            return new ArrayList<>(0);
//        }
//    }
}

