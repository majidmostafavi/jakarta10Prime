package com.majidmostafavi.jakartaee10prime12.core.utils;

import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Person;
import com.majidmostafavi.jakartaee10prime12.basicInfo.entity.Year;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;

/**
 * Created by majid on 9/26/16.
 */
public class SessionManager {

    public static Person getPerson() {
        Person person = (Person) ((HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(true)).
                getAttribute(Constants.CURRENT_PERSON);
        return person;
    }

    public static void setPerson(Person person) {
        ((HttpSession) JsfServletUtils.getExternalContext().getSession(true)).
                setAttribute(Constants.CURRENT_PERSON, person);

    }


    public static Year getYear() {
        return (Year) ((HttpSession) JsfServletUtils.getExternalContext().getSession(true)).getAttribute(Constants.CURRENT_FISCAL_YEAR_ATTR);
    }

    public static void setYear(Year fiscalYear) {
        ((HttpSession) JsfServletUtils.getExternalContext().getSession(true)).
                setAttribute(Constants.CURRENT_FISCAL_YEAR_ATTR, fiscalYear);
    }


    public static String getUsername() {
        return (String) ((HttpSession) JsfServletUtils.getExternalContext().getSession(true)).
                getAttribute(Constants.USERNAME_ATTR);
    }

    public static void setUsername(String username) {
        ((HttpSession) JsfServletUtils.getExternalContext().getSession(true)).
                setAttribute(Constants.USERNAME_ATTR, username);
    }

    //todo: permission
//    public static Set<CoPermission> getUserPermission(CoRoleOrganization roleOrganization){
//        return USER_PERMISSION_FULL_CACHE.get(roleOrganization);
//    }

}
