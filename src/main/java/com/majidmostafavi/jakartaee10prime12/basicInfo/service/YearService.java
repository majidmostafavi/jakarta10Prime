package com.majidmostafavi.jakartaee10prime12.basicInfo.service;

import com.majidmostafavi.jakartaee10prime12.basicInfo.dao.YearDao;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Year;
import com.majidmostafavi.jakartaee10prime12.core.exception.DuplicateDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.RelationDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.SaveRecordException;
import com.majidmostafavi.jakartaee10prime12.core.service.AbstractService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.jms.TransactionRolledBackException;

import java.util.Date;
import java.util.List;

@RequestScoped
public class YearService extends AbstractService {

    @Inject
    YearDao yearDao;

    public List<Year> findAllSortByYear(){
        return yearDao.findAllSortByYear();
    }

    public boolean validation(){
        return true;
    }

    public Year create(Year year) throws DuplicateDataException, SaveRecordException {
        if (validation()){
            return yearDao.create(year);
        }
        return null;
    }

    public Long countYearByDate(Date date){

        return yearDao.countByYear(date);
    }

    public boolean canSave(Year year){
        try {
            long count = yearDao.countByNameOrCode(year.getName(),year.getCode());
            if (count == 0l)
                return true;
            else
                return false;
        }catch (Exception e){
            return false;
        }
    }

    public void edit(Year year) throws DuplicateDataException, RelationDataException,SaveRecordException {

       yearDao.edit(year);

    }

    public Year findYearById(Long id){
        return yearDao.findYearById(id);
    }

}
