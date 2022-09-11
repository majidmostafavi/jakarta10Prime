package com.majidmostafavi.jakartaee10prime12.basicInfo.service;


import com.majidmostafavi.jakartaee10prime12.basicInfo.dao.CountryDao;
import com.majidmostafavi.jakartaee10prime12.basicInfo.dao.ProvinceDao;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Country;
import com.majidmostafavi.jakartaee10prime12.core.exception.DuplicateDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.RelationDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.SaveRecordException;
import com.majidmostafavi.jakartaee10prime12.core.service.AbstractService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;

@RequestScoped
public class CountryService extends AbstractService {


    @Inject
    CountryDao countryDao;
    @Inject
    ProvinceDao provinceDao;

    public void create(Country country) throws DuplicateDataException, SaveRecordException {
        if (canSave(country))
            countryDao.create(country);
    }
    public void edit(Country country) throws DuplicateDataException, RelationDataException,SaveRecordException {
        if (canEdit(country))
            countryDao.edit(country);
    }
    public void delete (Country country) throws RelationDataException{
        if (canDelete(country))
            countryDao.delete(country);
    }


//    public List<Country> findAll(){
//        try{
//            return countryDao.findAll();
//        }catch (Exception e){
//            logger.error("No Result" , e);
//            return new ArrayList<>(0);
//        }
//    }

    public boolean canSave (Country country) throws DuplicateDataException{
        if (countryDao.countByNameOrCode(country) == 0l){
            return true;
        }else {
            throw new DuplicateDataException();
        }
    }

    public boolean canEdit(Country country) throws DuplicateDataException,RelationDataException,SaveRecordException{
        boolean b = false;
        if (countryDao.countByNameOrCodeForEdit(country) == 0l){
            b =  true;
        }else {
            throw new DuplicateDataException();
        }
        if (provinceDao.countCountriesInProvinces(country) == 0l){
            b =  true;
        }else {
            throw new RelationDataException();
        }

        return b;
    }

    public boolean canDelete (Country country) throws RelationDataException{
        if (provinceDao.countCountriesInProvinces(country) == 0l){
            return true;
        }else {
            throw new RelationDataException();
        }
    }

    public List<Country> findAll() {
        return countryDao.findAll();
    }
}

