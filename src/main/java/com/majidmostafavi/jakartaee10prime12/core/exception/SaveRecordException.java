package com.majidmostafavi.jakartaee10prime12.core.exception;

/**
 * Created by majid on 7/27/17.
 */

public class SaveRecordException extends Exception {
    private static final String s = "Save Record Data Exception";
    public SaveRecordException() {
        super(s);
    }
    public SaveRecordException (String s){
        super(s);
    }
}
