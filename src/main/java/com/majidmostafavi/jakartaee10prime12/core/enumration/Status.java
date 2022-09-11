package com.majidmostafavi.jakartaee10prime12.core.enumration;

import java.util.Arrays;
import java.util.List;

public enum Status  {

    active,
    deactive,
    deleted;

    // Constructor
    Status() {
    }


    public static List<Status> findAllStatus() {
        return Arrays.asList(values());
    }

    public static Status findByOrdinal(int ordinal){
        if(ordinal==Status.active.ordinal()){
            return active;
        }else if (ordinal==Status.deactive.ordinal()){
            return deactive;
        }else if (ordinal==Status.deleted.ordinal()){
            return deleted;
        }
        return null;
    }
    public static Status getValue(String key) {
        if (key != null) {
            if (key.equals(active.name())) {
                return active;
            } else if (key.equals(deactive.name())) {
                return deactive;
            }else if (key.equals(deleted.name())) {
                return deleted;
            }
        }
        return null;
    }
}
