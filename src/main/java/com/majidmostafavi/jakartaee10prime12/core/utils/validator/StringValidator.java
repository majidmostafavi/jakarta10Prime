package com.majidmostafavi.jakartaee10prime12.core.utils.validator;


import com.majidmostafavi.jakartaee10prime12.core.utils.MessageProvider;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;

@FacesValidator(value = "stringValidator")
@ApplicationScoped
public class StringValidator implements Validator {

    private static final String PATTERN = "^[a-zA-Z\\s]+$";

    @Inject
    MessageProvider messageProvider;

    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) throws ValidatorException {

        if ((object instanceof String)) {
            String value = ((String) object).trim();
            if (!value.matches(PATTERN)) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary(messageProvider.getValue("english.string.code.exception"));
                throw new ValidatorException(message);
            }
        }
    }
}