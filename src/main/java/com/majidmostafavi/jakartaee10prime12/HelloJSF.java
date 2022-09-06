package com.majidmostafavi.jakartaee10prime12;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class HelloJSF {
    private String s;

    @PostConstruct
    public void init(){
        s = "Hello World";
    }

    public String getS() {
        return s;
    }
    public void setS(String s) {
        this.s = s;
    }
}
