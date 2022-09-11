package com.majidmostafavi.jakartaee10prime12.basicInfo.service;

import com.majidmostafavi.jakartaee10prime12.basicInfo.dao.SupplierDao;

import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Supplier;
import com.majidmostafavi.jakartaee10prime12.core.exception.DuplicateDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.RelationDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.SaveRecordException;
import com.majidmostafavi.jakartaee10prime12.core.service.AbstractService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;

@RequestScoped
public class SupplierService extends AbstractService {
    @Inject
    SupplierDao supplierDao;

    public List<Supplier> findAll() {
        return supplierDao.findAll();
    }

    public void create(Supplier supplier) throws DuplicateDataException, SaveRecordException {
        if (canSave(supplier))
            supplierDao.create(supplier);
    }
    public void edit(Supplier supplier) throws DuplicateDataException, RelationDataException,SaveRecordException {
        if (canEdit(supplier))
            supplierDao.edit(supplier);
    }
    public void delete (Supplier supplier) throws RelationDataException{
        if (canDelete(supplier))
            supplierDao.delete(supplier);
    }

    public boolean canSave (Supplier supplier) throws DuplicateDataException{
       return true;
    }

    public boolean canEdit(Supplier supplier) throws DuplicateDataException,RelationDataException,SaveRecordException{
        return true;
    }

    public boolean canDelete (Supplier supplier) throws RelationDataException{
        return true;
    }

    public int changeActiveSupplier(Long supplierId, boolean active) {
        return supplierDao.changeActiveSupplier(supplierId, active);
    }
    
}
