package com.majidmostafavi.jakartaee10prime12.basicInfo.converter;

import com.majidmostafavi.jakartaee10prime12.basicInfo.dao.YearDao;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Year;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value = "fiscalyearConverter")
public class FiscalyearConverter implements Converter {

    @Inject
    YearDao yearDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value==null||value.isEmpty())
            return null;
        Long id = null;

        try {
            id = Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new ConverterException("Can't convert " + value + " as ID to the corresponding Unit!");
        }
        Year year = yearDao.findYearById(id);
        return year;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof Year) {
            Year year= (Year) value;
            return year.getId() != null ? String.valueOf(year.getId()) : "";
        } else
            return "";

    }
}
