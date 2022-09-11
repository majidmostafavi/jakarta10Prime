package com.majidmostafavi.jakartaee10prime12.core.enumration;

public enum OrderType {
    asc,
    desc;

    public static OrderType getValue(String key) {
        if (key != null) {
            if (key.equals(asc.name())) {
                return asc;
            } else if (key.equals(desc.name())) {
                return desc;
            }
        }
        return null;
    }
}
