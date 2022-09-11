package com.majidmostafavi.jakartaee10prime12.converter;

import com.majidmostafavi.jakartaee10prime12.core.utils.MessageProvider;
import com.majidmostafavi.jakartaee10prime12.core.utils.PersianCalendarUtil;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;

import java.util.Date;

/**
 * Created by majid on 6/23/16.
 */
@FacesConverter(value = "dateConverter")
public class DateConverter implements Converter {
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s == null || s.trim().length()==0 || s.equals("____/__/__")){
            return null;
        }
        try{
            return  PersianCalendarUtil.toGregorian(s);
        }catch (NumberFormatException e ){
            FacesMessage facesMessage = new FacesMessage(MessageProvider.getText("masterValidation.Date.message"));
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ConverterException(facesMessage);
        }
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if ( o !=null && o instanceof Date){
            Date date =  (Date) o;
            return PersianCalendarUtil.toSolar(date);
        } else
            return "";
    }
}
