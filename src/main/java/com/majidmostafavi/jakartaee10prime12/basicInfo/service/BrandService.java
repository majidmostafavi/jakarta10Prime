package com.majidmostafavi.jakartaee10prime12.basicInfo.service;

import com.majidmostafavi.jakartaee10prime12.basicInfo.dao.BrandDao;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Brand;
import com.majidmostafavi.jakartaee10prime12.core.exception.DuplicateDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.RelationDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.SaveRecordException;
import com.majidmostafavi.jakartaee10prime12.core.service.AbstractService;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

public class BrandService {

    @Inject
    private BrandDao brandDao;

    public List<Brand> findBrandAllActiveAndSort(){
        try {
            return brandDao.findBrandAllActiveAndSort();
        }catch (Exception e){
            return new ArrayList<>(0);
        }
    }

    public void delete(Brand brand) throws RelationDataException {
//        if (canDelete(locationType))
            brandDao.delete(brand);
    }

//    public boolean canDelete(Brand locationType) throws RelationDataException{
//        if(brandService.countByLocationType(locationType) == 0l){
//            return true;
//        }else {
//            throw new RelationDataException();
//        }
//    }

    public void create(Brand brand) throws DuplicateDataException, SaveRecordException {
//        if (canSave(locationType))
            brandDao.create(brand);
    }

    public void edit(Brand brand) throws DuplicateDataException,RelationDataException,SaveRecordException {
//        if (canEdit(locationType))
           brandDao.edit(brand);
    }

}
