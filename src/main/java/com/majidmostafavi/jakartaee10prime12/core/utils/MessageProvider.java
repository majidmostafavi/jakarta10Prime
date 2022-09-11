package com.majidmostafavi.jakartaee10prime12.core.utils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@ApplicationScoped
public  class MessageProvider implements Serializable {
    private static ResourceBundle bundle;

    public static ResourceBundle getBundle() {
        if (bundle == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            bundle = context.getApplication().getResourceBundle(context, "msg");

        }
        return bundle;
    }

    public static String getText(String key) {

        String result = null;
        try {
            result = getBundle().getString(key);
        } catch (MissingResourceException e) {
            result = "?" + key + "? not found";
        }
        return result;
    }
    public String getValue(String key) {
        String result = null;
        try {
            result = getBundle().getString(key);
        } catch (MissingResourceException e) {
            result = "?" + key + "? not found";
        }
        return result;
    }

    public String getValue(String key, Object[] params){
        return  MessageFormat.format(getValue(key), params);
    }

    public String formatNumber(Long value){
        return format(value);
    }

    public String formatNumber(Double value){
        return format(value);
    }

    public static String format(Long value){
        if(value!=null)
            return String.format("%d", value);
        return "";
    }

    public static String format(Double value){
        if(value!=null)
            return String.format("%.2f", value);
        return "";
    }

    public String convertToText(Double value){
        String format="###.#####";
        return convertToText(value, format);
    }

    public String convertToText(Double value, String format){
        NumberFormat formatter = new DecimalFormat(format);
        String temp=formatter.format(value);
        return temp;
    }
}

