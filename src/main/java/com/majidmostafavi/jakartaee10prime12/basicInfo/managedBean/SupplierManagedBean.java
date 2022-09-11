package com.majidmostafavi.jakartaee10prime12.basicInfo.managedBean;

import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Brand;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.DTO.PersonDTO;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Person;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Supplier;
import com.majidmostafavi.jakartaee10prime12.basicInfo.enums.GenderEnum;
import com.majidmostafavi.jakartaee10prime12.basicInfo.managedBean.lazyLoader.PersonLazyLoader;
import com.majidmostafavi.jakartaee10prime12.basicInfo.service.BrandService;
import com.majidmostafavi.jakartaee10prime12.basicInfo.service.PersonService;
import com.majidmostafavi.jakartaee10prime12.basicInfo.service.SupplierService;
import com.majidmostafavi.jakartaee10prime12.core.exception.DuplicateDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.RelationDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.SaveRecordException;
import com.majidmostafavi.jakartaee10prime12.core.utils.JsfServletUtils;
import com.majidmostafavi.jakartaee10prime12.core.utils.MessageProvider;
import com.majidmostafavi.jakartaee10prime12.core.utils.SessionManager;
import com.majidmostafavi.jakartaee10prime12.core.utils.Utils;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
public class SupplierManagedBean {
    @Inject
    MessageProvider messageProvider;
     @Inject
    SupplierService supplierService;
     @Inject
    PersonLazyLoader personLazyLoader;
     @Inject
    PersonService personService;
     @Inject
    BrandService brandService;

    private String name;
    private String economicCode;
    private String mobile;
    private Boolean active;

    private Supplier supplier;
    private List<Supplier> supplierList;
    private Supplier selectSupplier;
    private List<Brand> brandList;


    //person Search
    private String firstName;
    private String lastName;
    private String nationalCode;
    private String personCode;
    private PersonDTO selectPersonDTO;
    private Person selectPerson;

    @PostConstruct
    public void init(){
        supplier = new Supplier();
        setSelectPersonDTO(null);
        selectPersonDTO = null;
        brandList = brandService.findBrandAllActiveAndSort();
    cancelSearch();
    }

    public void searchSupplier(){
        supplierList = supplierService.findAll();
    }
    public void cancelSearch(){

    }
    public String gotoCreate(){
        supplier = new Supplier();
        return "createSupplier";
    }
    public String createSupplier(){
        try{
                supplierService.create(supplier);
                Utils.createMessage(messageProvider.getValue("message.general.notification.save"), new Object[]{supplier.getEconomicCode()}, FacesMessage.SEVERITY_INFO, true);
                return "personnel";

        } catch (DuplicateDataException e){
            Utils.createMessage(messageProvider.getValue("message.general.notification.existObject.personnelCodeOrNationalCode.error"), null, FacesMessage.SEVERITY_INFO, false);
            return "";
        }catch (SaveRecordException e){
            Utils.createMessage(messageProvider.getValue("message.general.notification.save.error"), null, FacesMessage.SEVERITY_INFO, false);
            return "";
        }
    }
    public String gotoEdit(Supplier supplier){
        return  "editSupplier";
    }

    public void changeActiveSupplier(Supplier supplier) {
        if (supplier.isActive()) {
            int result = supplierService.changeActiveSupplier(supplier.getId(),false);
            if (result != 0) {
                supplier.setActive(false);
                Utils.createMessage(messageProvider.getValue("message.supplier.disActive.message"), null, FacesMessage.SEVERITY_INFO, false);
            } else {
                Utils.createMessage(messageProvider.getValue("message.common.reportType.bug.operation"), null, FacesMessage.SEVERITY_INFO, false);
            }
        } else {
            int result = supplierService.changeActiveSupplier(supplier.getId(),true);
            if (result != 0) {
                supplier.setActive(true);
                Utils.createMessage(messageProvider.getValue("message.supplier.active.message"), null, FacesMessage.SEVERITY_INFO, false);
            } else {
                Utils.createMessage(messageProvider.getValue("message.common.reportType.bug.operation"), null, FacesMessage.SEVERITY_INFO, false);
            }
        }
    }

    public String cancel(){
        selectSupplier =null;
        supplier=null;
        return "supplier";
    }


    public void onSelectedPerson() {

        selectPerson = personService.findPersonById(selectPersonDTO.getId());
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, messageProvider.getValue("message.general.button.select"), selectPerson.toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        JsfServletUtils.execute("PF('personDialog').hide()");
    }

    public void cancelPersonComposite() {

        setSelectPerson(null);
        JsfServletUtils.execute("PF('personDialog').hide()");
    }

    public void searchPerson() {
        personLazyLoader.setFirstName(firstName);
        personLazyLoader.setLastName(lastName);
        personLazyLoader.setNationalCode(nationalCode);
        personLazyLoader.setPersonCode(personCode);
    }

    public void cancelSearchPerson() {
        firstName = null;
        lastName = null;
        nationalCode = null;
        personCode = null;
        searchPerson();
    }

    //getter & setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEconomicCode() {
        return economicCode;
    }
    public void setEconomicCode(String economicCode) {
        this.economicCode = economicCode;
    }

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Supplier> getSupplierList() {
        return supplierList;
    }
    public void setSupplierList(List<Supplier> supplierList) {
        this.supplierList = supplierList;
    }

    public SupplierService getSupplierService() {
        return supplierService;
    }
    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public Supplier getSelectSupplier() {
        return selectSupplier;
    }
    public void setSelectSupplier(Supplier selectSupplier) {
        this.selectSupplier = selectSupplier;
    }

    public Supplier getSupplier() {
        return supplier;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public PersonDTO getSelectPersonDTO() {
        return selectPersonDTO;
    }

    public void setSelectPersonDTO(PersonDTO selectPersonDTO) {
        this.selectPersonDTO = selectPersonDTO;
    }

    public Person getSelectPerson() {
        return selectPerson;
    }

    public void setSelectPerson(Person selectPerson) {
        this.selectPerson = selectPerson;
    }

    public PersonLazyLoader getPersonLazyLoader() {
        return personLazyLoader;
    }

    public void setPersonLazyLoader(PersonLazyLoader personLazyLoader) {
        this.personLazyLoader = personLazyLoader;
    }

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }
}
