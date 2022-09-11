package com.majidmostafavi.jakartaee10prime12.basicInfo.managedBean;

import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Year;
import com.majidmostafavi.jakartaee10prime12.basicInfo.service.YearService;
import com.majidmostafavi.jakartaee10prime12.core.exception.DuplicateDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.RelationDataException;
import com.majidmostafavi.jakartaee10prime12.core.exception.SaveRecordException;
import com.majidmostafavi.jakartaee10prime12.core.utils.MessageProvider;
import com.majidmostafavi.jakartaee10prime12.core.utils.PersianCalendarUtil;
import com.majidmostafavi.jakartaee10prime12.core.utils.Utils;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.jms.TransactionRolledBackException;

import java.util.Date;
import java.util.List;

@Named
public class YearManagedBean {
    @Inject
    YearService yearService;
    @Inject
    MessageProvider messageProvider;

    private List<Year> yearList;
    private Year year;
    private Year selectYear;
    private String startDate;
    private String endDate;

    @PostConstruct
    public void init(){
        year = new Year();
        selectYear = new Year();
        yearList = yearService.findAllSortByYear();
    }

    public String gotoCreate(){
        year = new Year();
        startDate = new String();
        endDate = new String();
        return "createYear";
    }

    public String createYear(){
        if (validation(year)) {
            try {
                year.setStartDate(PersianCalendarUtil.toGregorian(startDate));
                year.setEndDate(PersianCalendarUtil.toGregorian(endDate));
                year.setActive(true);
                year.setId(Long.valueOf(year.getCode()));
                yearService.create(year);
                Utils.createMessage(messageProvider.getValue("message.general.notification.save"), null, FacesMessage.SEVERITY_INFO, true);
                init();
                return "Year";
            } catch (ClassCastException e) {
                Utils.createMessage(messageProvider.getValue("message.general.notification.save.error"), null, FacesMessage.SEVERITY_INFO, false);
                return "";
            } catch (DuplicateDataException e) {
                Utils.createMessage(messageProvider.getValue("message.general.notification.editDelete.error"), null, FacesMessage.SEVERITY_INFO, true);
                return "";
            } catch (SaveRecordException e) {
                Utils.createMessage(messageProvider.getValue("message.general.notification.save.error"), null, FacesMessage.SEVERITY_INFO, false);
                return "";
            }
        }else {

            return "";
        }

    }

    public boolean validation (Year year){
        Date start = PersianCalendarUtil.toGregorian(startDate);
        Date end = PersianCalendarUtil.toGregorian(endDate);
        Long startYear = yearService.countYearByDate(start);
        Long endYear = yearService.countYearByDate(end);
//        FacesContext context = FacesContext.getCurrentInstance();
        if (year.getName() == null){
            Utils.createMessage(messageProvider.getValue("message.general.notification.null.error"), null, FacesMessage.SEVERITY_INFO, false);
            return false;
        }
        if (year.getCode() == null){
            Utils.createMessage(messageProvider.getValue("message.general.notification.null.code.error"), null, FacesMessage.SEVERITY_INFO, false);
            return false;
        }
        if (year.getCode().length()!=4){
            Utils.createMessage(messageProvider.getValue("message.general.report.code.len.year.error"), null, FacesMessage.SEVERITY_INFO, false);
            return false;
        }
        if (start.equals(end) || start.after(end)) {
            Utils.createMessage(messageProvider.getValue("message.general.masterValidation.year.start.after.end"),null,FacesMessage.SEVERITY_ERROR,false);
            return false;
        }
        if (year.getId() == null){
            if (!yearService.canSave(year)){
                Utils.createMessage(messageProvider.getValue("message.general.notification.existObject.nameOrCode.error"), null, FacesMessage.SEVERITY_INFO, false);
                return false;
            }

            if (startYear != 0) {
                Utils.createMessage(messageProvider.getValue("message.common.fiscal.year.validation.year.contain"), new Object[]{startDate}, FacesMessage.SEVERITY_ERROR, false);
                return false;
            }

            if (endYear != 0) {
                Utils.createMessage(messageProvider.getValue("message.common.fiscal.year.validation.year.contain"), new Object[]{endDate}, FacesMessage.SEVERITY_ERROR, false);
                return false;
            }
        }
        if (year.getId() != null){
            if (startYear != 0) {
                Utils.createMessage(messageProvider.getValue("message.common.fiscal.year.validation.year.contain"), new Object[]{startDate}, FacesMessage.SEVERITY_ERROR, false);
                return false;
            }
            if (endYear != 0) {
                Utils.createMessage(messageProvider.getValue("message.common.fiscal.year.validation.year.contain"), new Object[]{endDate}, FacesMessage.SEVERITY_ERROR, false);
                return false;
            }
        }
        return true;
    }

    public void updateYear(){
        init();
    }

    public String gotoEdit(Year year){
        startDate = PersianCalendarUtil.toSolar(selectYear.getStartDate());
        endDate = PersianCalendarUtil.toSolar(selectYear.getEndDate());
        selectYear = year;
        return "editYear";
    }

    public String editYear(){
        startDate = PersianCalendarUtil.toSolar(selectYear.getStartDate());
        endDate = PersianCalendarUtil.toSolar(selectYear.getEndDate());
        if (validation(selectYear)) {
            try {
                yearService.edit(selectYear);
                Utils.createMessage(messageProvider.getValue("message.general.notification.edit"), null, FacesMessage.SEVERITY_INFO, true);
                init();
                return "yearEdit";
            } catch (DuplicateDataException e) {
                Utils.createMessage(messageProvider.getValue("message.general.notification.editDelete.error"),null, FacesMessage.SEVERITY_INFO,true);
            } catch (RelationDataException e) {
                Utils.createMessage(messageProvider.getValue("message.general.notification.editDelete.error"),null, FacesMessage.SEVERITY_INFO,true);
            } catch (SaveRecordException e){
                Utils.createMessage(messageProvider.getValue("message.general.notification.save"), null, FacesMessage.SEVERITY_INFO, false);
            }
        }else {
            return "";
        }
        return "";
    }

    public String cancel(){
        year = new Year();
        return "year";
    }

    public void removeYear(){

    }


//    getter&setter


    public List<Year> getYearList() {
        return yearList;
    }

    public void setYearList(List<Year> yearList) {
        this.yearList = yearList;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Year getSelectYear() {
        return selectYear;
    }

    public void setSelectYear(Year selectYear) {
        this.selectYear = selectYear;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
