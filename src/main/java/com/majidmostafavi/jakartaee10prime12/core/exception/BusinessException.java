package com.majidmostafavi.jakartaee10prime12.core.exception;

/**
 * Created by majid on 7/27/17.
 */


public class BusinessException extends Exception {
    private static final String s = "Business Exception";
    public BusinessException() {
        super(s);
    }
    public BusinessException(String s){
        super(s);
    }
}
