package com.majidmostafavi.jakartaee10prime12.core.utils;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.HashMap;
import java.util.Map;


public class SessionUtils implements HttpSessionListener {
    private static final Map<String,String> sessions = new HashMap<>();

    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("SessionCounter.sessionCreated");
        HttpSession session = event.getSession();
        sessions.put(session.getId(),JsfServletUtils.getClientIp());
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("SessionCounter.sessionDestroyed");
        HttpSession session = event.getSession();
        sessions.remove(session.getId());
    }


    public static int getActiveSessionNumber() {
        return sessions.size();
    }

    public static Map<String, String> getSessionList(){
        return sessions;
    }

}
