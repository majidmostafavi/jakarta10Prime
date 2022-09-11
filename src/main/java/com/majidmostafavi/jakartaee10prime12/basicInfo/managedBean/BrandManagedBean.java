package com.majidmostafavi.jakartaee10prime12.basicInfo.managedBean;

import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Brand;
import com.majidmostafavi.jakartaee10prime12.basicInfo.service.BrandService;
import com.majidmostafavi.jakartaee10prime12.core.exception.DuplicateDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.RelationDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.SaveRecordException;
import com.majidmostafavi.jakartaee10prime12.core.utils.JsfServletUtils;
import com.majidmostafavi.jakartaee10prime12.core.utils.MessageProvider;
import com.majidmostafavi.jakartaee10prime12.core.utils.Utils;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
public class BrandManagedBean {
    @Inject
    private BrandService brandService;
    @Inject
    private MessageProvider messageProvider;

    private List<Brand> brandList;
    private Brand brand;
    private Brand selectBrand;


    @PostConstruct
    public void init(){
        brand = new Brand();
        selectBrand = new Brand();
        brandList = brandService.findBrandAllActiveAndSort();
    }

    public void removeBrand(){
        try {
            brandService.delete(selectBrand);
            Utils.createMessage(messageProvider.getValue("message.general.notification.delete"), null, FacesMessage.SEVERITY_INFO, false);
            init();
        } catch (RelationDataException e){
            Utils.createMessage(messageProvider.getValue("message.general.notification.Delete.error"), null, FacesMessage.SEVERITY_INFO, false);
        }
    }

    public void createBrand(){

        if(validation(brand)){
            try{
                brandService.create(brand);
                Utils.createMessage(messageProvider.getValue("message.general.notification.save"),null, FacesMessage.SEVERITY_INFO,false);
                init();
                JsfServletUtils.execute("PF('createBrand').hide()");
            } catch (DuplicateDataException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.existObject.nameOrCode.error"),null,FacesMessage.SEVERITY_INFO,false);
                JsfServletUtils.execute("PF('createBrand').show()");
            }catch (SaveRecordException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.save"), null, FacesMessage.SEVERITY_INFO, false);
                JsfServletUtils.execute("PF('createBrand').show()");
            }

        }
        else {
            JsfServletUtils.execute("PF('createBrand').show()");
        }
    }

    public boolean validation(Brand brand){
        if (brand.getName() == null){
            Utils.createMessage(messageProvider.getValue("message.general.notification.null.error"),null, FacesMessage.SEVERITY_INFO,false);
            return false;
        }
        if (brand.getCode() == null){
            Utils.createMessage(messageProvider.getValue("message.general.notification.null.code.error"),null,FacesMessage.SEVERITY_INFO,false);
            return false;
        }
        return true;
    }

    public void cancel(){
        init();
    }

    public void editBrand(){

        if (validation(selectBrand)){
            try {
                brandService.edit(selectBrand);
                Utils.createMessage(messageProvider.getValue("message.general.notification.edit"), null, FacesMessage.SEVERITY_INFO, false);
                JsfServletUtils.execute("PF('editBrand').hide()");
            } catch (RelationDataException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.relation"), null, FacesMessage.SEVERITY_INFO, false);
                JsfServletUtils.execute("PF('editBrand').show()");
            } catch (DuplicateDataException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.existObject.nameOrCode.error"), null, FacesMessage.SEVERITY_INFO, false);
                JsfServletUtils.execute("PF('editBrand').show()");
            }catch (SaveRecordException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.save"), null, FacesMessage.SEVERITY_INFO, false);
                JsfServletUtils.execute("PF('editBrand').show()");
            }
        }
        else {
            JsfServletUtils.execute("PF('editBrand').show()");
        }
    }


//    getter&setter

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }


    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Brand getSelectBrand() {
        return selectBrand;
    }

    public void setSelectBrand(Brand selectBrand) {
        this.selectBrand = selectBrand;
    }
}
