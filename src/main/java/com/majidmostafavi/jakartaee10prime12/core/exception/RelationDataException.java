package com.majidmostafavi.jakartaee10prime12.core.exception;

/**
 * Created by majid on 12/22/16.
 */

public class RelationDataException extends Exception {
    private static final String s = "exception.relation.data";
    public RelationDataException() {
        super(s);
    }
    public RelationDataException(String s) {
        super(s);
    }

}
