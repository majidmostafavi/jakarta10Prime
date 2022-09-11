package com.majidmostafavi.jakartaee10prime12.core.exception;

public class SPValidationException extends Exception{
    private static final String s = "validation Exception";
    public SPValidationException() {
        super(s);
    }
    public SPValidationException(String s) {
        super(s);
    }
}
