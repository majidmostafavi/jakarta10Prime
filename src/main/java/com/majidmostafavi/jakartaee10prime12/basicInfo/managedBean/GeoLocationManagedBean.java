package com.majidmostafavi.jakartaee10prime12.basicInfo.managedBean;


import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.City;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Country;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Province;
import com.majidmostafavi.jakartaee10prime12.basicInfo.service.CityService;
import com.majidmostafavi.jakartaee10prime12.basicInfo.service.CountryService;
import com.majidmostafavi.jakartaee10prime12.basicInfo.service.ProvinceService;
import com.majidmostafavi.jakartaee10prime12.core.utils.JsfServletUtils;
import com.majidmostafavi.jakartaee10prime12.core.utils.MessageProvider;
import com.majidmostafavi.jakartaee10prime12.core.exception.DuplicateDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.RelationDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.SaveRecordException;
import com.majidmostafavi.jakartaee10prime12.core.utils.Utils;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
public class GeoLocationManagedBean  {

    @Inject
    CountryService countryService;
    @Inject
    ProvinceService provinceService;
    @Inject
    CityService cityService;
    @Inject
    MessageProvider messageProvider;



    private Country country;
    private Country searchCountry;
    private Country selectCountry;
    private Country deleteCountry;
    private Province province;
    private Province searchProvince;
    private Province selectProvince;
    private Province deleteProvince;
    private City city;
    private City searchCity;
    private City selectCity;
    private List<Country> countryList;
    private List<Province> provinceList;
    private List<City> cityList;

    @PostConstruct
    public void init(){
        country =new Country();
        selectCountry = new Country();
        searchCountry = new Country();
        province =new Province();
        selectProvince = new Province();
        searchProvince = new Province();
        city =new City();
        selectCity = new City();
        searchCity = new City();
        countryList = countryService.findAll();
        provinceList = provinceService.findAll();
        cityList = cityService.findAll();
        deleteCountry= new Country();
        deleteProvince = new Province();
    }

    public boolean validationCountry(Country country){
        if (country.getName().isEmpty()){
            Utils.createMessage(messageProvider.getValue("message.general.name.empty"), null, FacesMessage.SEVERITY_INFO, false);
            return false;
        }if (country.getCode()==null){
            Utils.createMessage(messageProvider.getValue("message.general.code.empty"), null, FacesMessage.SEVERITY_INFO, false);
            return false;
        }
        return true;
    }
    /*Country*/

    public void createCountry() {

        if (validationCountry(country)) {
            try {
                countryService.create(country);
                Utils.createMessage(messageProvider.getValue("message.general.notification.save"), null, FacesMessage.SEVERITY_INFO, false);
                init();
                JsfServletUtils.execute("PF('createCountry').hide()");
            }catch (DuplicateDataException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.existObject.nameOrCode.error"), null, FacesMessage.SEVERITY_INFO, false);
                countryList = countryService.findAll();
            }catch (SaveRecordException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.save"), null, FacesMessage.SEVERITY_INFO, false);
            }
        }
    }
    public void editCountry(){


        if (validationCountry(selectCountry)) {
            try {
                countryService.edit(selectCountry);
                Utils.createMessage(messageProvider.getValue("message.general.notification.edit"), null, FacesMessage.SEVERITY_INFO, false);
                JsfServletUtils.execute("PF('editCountry').hide()");
            }catch (RelationDataException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.relation"), null, FacesMessage.SEVERITY_INFO, false);
            }catch (DuplicateDataException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.existObject.nameOrCode.error"), null, FacesMessage.SEVERITY_INFO, false);
            }catch (SaveRecordException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.save"), null, FacesMessage.SEVERITY_INFO, false);
            }
            countryList = countryService.findAll();
        }
    }
    public void cancelCountry(){
        init();
    }

    public void removeCountry(){
        try{
            countryService.delete(deleteCountry);
            Utils.createMessage(messageProvider.getValue("message.general.notification.save"), null, FacesMessage.SEVERITY_INFO, false);
            init();
        }catch (RelationDataException e){
            Utils.createMessage(messageProvider.getValue("message.general.notification.Delete.error"), null, FacesMessage.SEVERITY_INFO, false);
            countryList = countryService.findAll();
        }
    }

    /*Province*/
    public boolean validationCounty(Province province){
        if (province.getName().isEmpty()){
            Utils.createMessage(messageProvider.getValue("message.general.name.empty"), null, FacesMessage.SEVERITY_INFO, false);
            return false;
        }if (province.getCode()==null){
            Utils.createMessage(messageProvider.getValue("message.general.code.empty"), null, FacesMessage.SEVERITY_INFO, false);
            return false;
        }
        return true;
    }

    public void createProvince(){

        if (validationCounty(province)) {
            try {
                province.setCountry(selectCountry);
                provinceService.create(province);
                Utils.createMessage(messageProvider.getValue("message.general.notification.save"), null, FacesMessage.SEVERITY_INFO, false);
                JsfServletUtils.execute("PF('createProvince').hide()");
                init();
            }catch (DuplicateDataException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.existObject.nameOrCode.error"), null, FacesMessage.SEVERITY_INFO, false);
                provinceList = provinceService.findAll();
            }catch (SaveRecordException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.save"), null, FacesMessage.SEVERITY_INFO, false);
            }
        }

    }
    public void editProvince(){

        if (validationCounty(selectProvince)) {
            try {
                provinceService.edit(selectProvince);
                Utils.createMessage(messageProvider.getValue("message.general.notification.edit"), null, FacesMessage.SEVERITY_INFO, false);
                JsfServletUtils.execute("PF('editProvince').hide()");
            }catch (RelationDataException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.relation"), null, FacesMessage.SEVERITY_INFO, false);
            }catch (DuplicateDataException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.existObject.nameOrCode.error"), null, FacesMessage.SEVERITY_INFO, false);
            }catch (SaveRecordException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.save"), null, FacesMessage.SEVERITY_INFO, false);
            }
            provinceList = provinceService.findAll();
        }
    }
    public void cancelProvince(){init();}

    public void removeProvince(){
        if(validationCounty(deleteProvince)) {
            try {
                provinceService.delete(deleteProvince);
                Utils.createMessage(messageProvider.getValue("message.general.notification.delete"), null, FacesMessage.SEVERITY_INFO, false);
                init();
            }catch (RelationDataException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.Delete.error"), null, FacesMessage.SEVERITY_INFO, false);
                provinceList = provinceService.findAll();
            }
        }
    }

    /*City*/
    public boolean validationCity(City city){
        if (city.getName().isEmpty()){
            Utils.createMessage(messageProvider.getValue("message.general.name.empty"), null, FacesMessage.SEVERITY_INFO, false);
            return false;
        }if (city.getCode()==null){
            Utils.createMessage(messageProvider.getValue("message.general.code.empty"), null, FacesMessage.SEVERITY_INFO, false);
            return false;
        }
        return true;
    }
    public void createCity(){


        if (validationCity(city)) {
            try {
                city.setProvince(selectProvince);
                cityService.create(city);
                Utils.createMessage(messageProvider.getValue("message.general.notification.save"), null, FacesMessage.SEVERITY_INFO, false);
                JsfServletUtils.execute("PF('createCity').hide()");
                init();
            }catch (DuplicateDataException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.existObject.nameOrCode.error"),null,FacesMessage.SEVERITY_INFO,false);
                cityList = cityService.findAll();
            }catch (SaveRecordException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.save"), null, FacesMessage.SEVERITY_INFO, false);
            }
        }
    }
    public void editCity(){


        if (validationCity(selectCity)) {
            try {
                cityService.edit(selectCity);
                Utils.createMessage(messageProvider.getValue("message.general.notification.edit"), null, FacesMessage.SEVERITY_INFO, false);
                JsfServletUtils.execute("PF('editCity').hide()");
            }catch (RelationDataException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.relation"), null, FacesMessage.SEVERITY_INFO, false);
            }catch (DuplicateDataException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.existObject.nameOrCode.error"), null, FacesMessage.SEVERITY_INFO, false);
            }catch (SaveRecordException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.save"), null, FacesMessage.SEVERITY_INFO, false);
            }
            cityList = cityService.findAll();
        }
    }

    public void cancelCity(){init();}

    public void removeCity(){
        try {
            cityService.delete(selectCity);
            init();
        }catch (RelationDataException e){
            Utils.createMessage(messageProvider.getValue("message.general.notification.relation"), null, FacesMessage.SEVERITY_INFO, false);
            cityList = cityService.findAll();
        }
    }



    /* Setter And Getter Methods */


    public void setCountry(Country country) {
        this.country = country;
    }
    public Country getCountry() {
        return country;
    }

    public void setSearchCountry(Country searchCountry) {
        this.searchCountry = searchCountry;
    }
    public Country getSearchCountry() {
        return searchCountry;
    }

    public void setSelectCountry(Country selectCountry) {
        this.selectCountry = selectCountry;
    }
    public Country getSelectCountry() {
        return selectCountry;
    }

    public Country getDeleteCountry() {
        return deleteCountry;
    }
    public void setDeleteCountry(Country deleteCountry) {
        this.deleteCountry = deleteCountry;
    }

    public Province getProvince() {
        return province;
    }
    public void setProvince(Province province) {
        this.province = province;
    }

    public Province getSearchProvince() {
        return searchProvince;
    }
    public void setSearchProvince(Province searchProvince) {
        this.searchProvince = searchProvince;
    }

    public Province getSelectProvince() {
        return selectProvince;
    }
    public void setSelectProvince(Province selectProvince) {
        this.selectProvince = selectProvince;
    }

    public Province getDeleteProvince() {
        return deleteProvince;
    }
    public void setDeleteProvince(Province deleteProvince) {
        this.deleteProvince = deleteProvince;
    }

    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }

    public City getSearchCity() {
        return searchCity;
    }
    public void setSearchCity(City searchCity) {
        this.searchCity = searchCity;
    }

    public City getSelectCity() {
        return selectCity;
    }
    public void setSelectCity(City selectCity) {
        this.selectCity = selectCity;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }
    public List<Country> getCountryList() {
        return countryList;
    }

    public List<Province> getProvinceList() {
        return provinceList;
    }
    public void setProvinceList(List<Province> provinceList) {
        this.provinceList = provinceList;
    }

    public List<City> getCityList() {
        return cityList;
    }
    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }


}

