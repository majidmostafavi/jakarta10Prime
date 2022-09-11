package com.majidmostafavi.jakartaee10prime12.core.exception;

/**
 * Created by majid on 7/27/17.
 */

public class NullValueException extends Exception {
    private static final String s = "Null Value Exception";
    public NullValueException() {
        super(s);
    }
    public NullValueException(String s){
        super(s);
    }
}
