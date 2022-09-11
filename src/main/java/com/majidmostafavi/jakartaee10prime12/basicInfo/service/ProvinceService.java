package com.majidmostafavi.jakartaee10prime12.basicInfo.service;


import com.majidmostafavi.jakartaee10prime12.basicInfo.dao.CityDao;
import com.majidmostafavi.jakartaee10prime12.basicInfo.dao.ProvinceDao;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Province;
import com.majidmostafavi.jakartaee10prime12.core.exception.DuplicateDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.RelationDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.SaveRecordException;
import com.majidmostafavi.jakartaee10prime12.core.service.AbstractService;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;


public class ProvinceService extends AbstractService {

    @Inject
    ProvinceDao provinceDao;
    @Inject
    CityDao cityDao;

    public void create(Province province) throws DuplicateDataException, SaveRecordException {
        if (canSave(province))
            provinceDao.create(province);
    }
    public void edit(Province province) throws DuplicateDataException, RelationDataException,SaveRecordException{
        if (canEdit(province))
            provinceDao.edit(province);
    }
    public void delete (Province province) throws RelationDataException{
        if (canDelete(province))
            provinceDao.delete(province);
    }


//    public List<Province> findAll(){
//        try{
//            return provinceDao.findAll();
//        }catch (Exception e){
//            logger.error("No Result" , e);
//            return new ArrayList<Province>(0);
//        }
//    }

    public boolean canSave (Province province) throws DuplicateDataException{
        if(provinceDao.countCountyByNameOrCode(province) == 0l){
            return true;
        }else {
            throw new DuplicateDataException();
        }
    }

    public boolean canEdit(Province province) throws DuplicateDataException,RelationDataException,SaveRecordException{
        boolean b = false;
        if(provinceDao.countCountyByNameOrCodeForEdit(province) == 0l){
            b = true;
        }else {
            throw new DuplicateDataException();
        }

        if(cityDao.countProvince(province) == 0l){
            b = true;
        }else {
            throw new RelationDataException();
        }
        return b;
    }

    public boolean canDelete(Province province) throws RelationDataException{
        if(cityDao.countProvince(province) == 0l){
            return true;
        }else {
            throw new RelationDataException();
        }
    }

    public List<Province> findAll() {
        return provinceDao.findAll();
    }
}

