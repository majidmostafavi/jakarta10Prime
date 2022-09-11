package com.majidmostafavi.jakartaee10prime12.core.exception;

/**
 * Created by majid on 9/3/17.
 */
public class ForeignKeyException extends RuntimeException {
    public ForeignKeyException(String message) {
        super(message);
    }
}
