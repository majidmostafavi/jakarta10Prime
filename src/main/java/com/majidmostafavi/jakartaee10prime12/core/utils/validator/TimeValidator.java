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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
@FacesValidator(value = "timeValidator")
public class TimeValidator implements Validator {

    @Inject
    MessageProvider messageProvider;

    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) throws ValidatorException {
        if (object instanceof String) {
            String inputText = (String) object;
            if (!inputText.trim().isEmpty()) {
                Pattern pattern = Pattern.compile("([0][1-9]|[1][0-9]|[2][0-3]):[0-5][0-9]");
                Matcher matcher = pattern.matcher(inputText);
                if (!matcher.matches()) {
                    FacesMessage message = new FacesMessage();
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    message.setSummary(messageProvider.getValue("error.time.invalid"));
                    throw new ValidatorException(message);
                }
            }
        }
    }
}