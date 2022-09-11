package com.majidmostafavi.jakartaee10prime12.core.utils.validator;

import com.majidmostafavi.jakartaee10prime12.core.utils.Config;
import com.majidmostafavi.jakartaee10prime12.core.utils.MessageProvider;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.text.MessageFormat;

/**
 * Created by majid on 8/16/16.
 */
@Named
@FacesValidator(value = "dateValidation")
@ApplicationScoped
public  class DateValidation implements Validator,Serializable {

    @Inject
    MessageProvider messageProvider;


    @Config("system.minimum.year")
    private Integer minimumValidYear=1300;


    @Config("system.maximum.year")
    private Integer maximumValidYear=1450;

    @Override
    public  void validate(FacesContext facesContext, UIComponent uiComponent, Object object) throws ValidatorException {

        if (object instanceof String && object!=null && !object.toString().isEmpty() ) {
            String enteredDateValue = (String) object;
            String summary = messageProvider.getValue("message.general.date");
            String detail = null;

            int minYear = minYear(uiComponent) != null ? Integer.parseInt(minYear(uiComponent)) : minimumValidYear;
            int maxYear = maxYear(uiComponent) != null ? Integer.parseInt(maxYear(uiComponent)) : maximumValidYear;

            if (dateIsEmpty(enteredDateValue)) {
                detail = messageProvider.getValue("message.general.date.validation");
            } else {

                try {
                    String[] parts = enteredDateValue.split("/");
                    if (parts.length != 3) {
                        detail = messageProvider.getValue("message.general.date.validation");
                    } else {
                        String year = parts[0];
                        String month = parts[1];
                        String day = parts[2];
                        if (Integer.parseInt(year) > maxYear || Integer.parseInt(year) < minYear){
                            detail = MessageFormat.format(messageProvider.getValue("message.general.date.range.validation"),new Object[]{minYear, maxYear});
                        } else if (Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12){
                            detail = messageProvider.getValue("message.general.date.validation");
                        } else if (Integer.parseInt(month) < 7){
                            if(Integer.parseInt(day) < 1 || Integer.parseInt(day) > 31){
                                detail = messageProvider.getValue("message.general.date.validation");
                            }
                        } else {
                            if(Integer.parseInt(day) < 1 || Integer.parseInt(day) > 30){
                                detail = messageProvider.getValue("message.general.date.validation");
                            }
                        }
                    }
                } catch (NumberFormatException nfe) {
                    detail = messageProvider.getValue("message.general.date.validation");
                }
            }

            if (detail != null) {
                FacesMessage msg = new FacesMessage(summary, detail);
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }

    }
    private boolean dateIsEmpty(String dateValue) {
        if (dateValue == null) {
            return true;
        } else {
            dateValue = dateValue.replace('/', ' ');
            return dateValue.trim().isEmpty();
        }
    }
    protected String minYear(UIComponent uiComponent) {
        Object object = uiComponent.getAttributes().get("minYear");
        String attribute = null;
        if (object != null) {
            if (object instanceof String)
                attribute = (String) object;
            else
                attribute = String.valueOf(object);
            return attribute;
        } else {
            return null;
        }

    }

    protected String maxYear(UIComponent uiComponent) {
        Object object = uiComponent.getAttributes().get("maxYear");
        String attribute = null;
        if (object != null) {
            if (object instanceof String)
                attribute = (String) object;
            else
                attribute = String.valueOf(object);
            return attribute;
        } else {
            return null;
        }

    }
}
