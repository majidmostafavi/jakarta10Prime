package com.majidmostafavi.jakartaee10prime12.core.exception;

/**
 * Created by majid on 12/22/16.
 */

public class DuplicateDataException extends Exception {
    private static final String s = "Duplicate Data Exception";
    public DuplicateDataException() {
        super(s);
    }
    public DuplicateDataException(String s){
        super(s);
    }
}
