package com.majidmostafavi.jakartaee10prime12.core.utils;


//import org.primefaces.PrimeFaces;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.primefaces.PrimeFaces;



import java.io.IOException;
import java.text.MessageFormat;

public class JsfServletUtils {

    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public static ExternalContext getExternalContext() {
        return getFacesContext().getExternalContext();
    }

    public static ServletContext getServletContext() {
        return (ServletContext) getExternalContext().getContext();
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) getExternalContext().getResponse();
    }

    public static String getViewId() {
        return getFacesContext().getViewRoot().getViewId();
    }

    public static String getLocalIp() {
        return getRequest().getLocalAddr();
    }

    public static int getLocalPort() {
        return getRequest().getLocalPort();
    }

    public static String getClientIp() {
        try {
            String ip = getRequest().getHeader("X-Forwarded-For");
            if (ip == null || ip.trim().length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = getRequest().getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.trim().length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = getRequest().getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.trim().length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = getRequest().getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.trim().length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = getRequest().getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.trim().length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = getRequest().getRemoteAddr();
            }
            return ip;
        }catch (Exception e){
            e.printStackTrace();
            return "No IP";
        }

    }

    public static void createMessage(String message, Object[] params, FacesMessage.Severity severity, boolean keepMessages) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (keepMessages) {
            Flash flash = getExternalContext().getFlash();
            flash.setKeepMessages(keepMessages);
        }
        message = MessageFormat.format(message, params);
        FacesMessage facesMessage = new FacesMessage(severity, "", message);
        context.addMessage(null, facesMessage);
    }

    public static void addMessage(String msg, String id, FacesMessage.Severity severity, boolean isError) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(msg);
        message.setSeverity(severity);
        context.addMessage(id, message);
       // RequestContext.getCurrentInstance().addCallbackParam("error", isError);
    }

    public static String getClientInfo() {
        return getRequest().getHeader("user-agent");
    }

    public static String getRequestParameter(String parameterName) {
        return getRequest().getParameter(parameterName);
    }

    public static void sendError(int errorCode) throws IOException {
        getResponse().sendError(errorCode);
    }


    public static void update(String s){
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.update(s);
        PrimeFaces p = PrimeFaces.current();
       p.ajax().update(s);
    }

    public static void execute(String s){
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.execute(s);
        PrimeFaces p =  PrimeFaces.current();
        p.executeScript(s);
    }

}
