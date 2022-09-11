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

@FacesValidator(value = "amountValidator")
@ApplicationScoped
public class AmountValidator implements Validator {

    @Inject
    MessageProvider messageProvider;

    private static final String PATTERN = "[0-9]*";

    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) throws ValidatorException {
        if ((object instanceof String)) {
            String value = ((String) object).replaceAll(",", "").trim();
            if (!value.matches(PATTERN)) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary(messageProvider.getValue("amount.validate.exception"));
                throw new ValidatorException(message);
            }
        }
    }
}