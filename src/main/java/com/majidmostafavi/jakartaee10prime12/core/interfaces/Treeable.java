package com.majidmostafavi.jakartaee10prime12.core.interfaces;



/**
 * Created by majid on 11/6/16.
 */
public interface Treeable<T> {

    Long getId();
    default Long getParentId() {
        return null;
    }
    default boolean isLeaf() { return true; }

}
