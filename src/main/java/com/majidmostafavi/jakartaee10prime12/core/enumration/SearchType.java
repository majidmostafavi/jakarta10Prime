package com.majidmostafavi.jakartaee10prime12.core.enumration;

import java.util.Arrays;
import java.util.List;

public enum SearchType {

    startWith,
    contain,
    equal;

    SearchType() {
    }

    public static List<SearchType> findAllSearchType() {
        return Arrays.asList(values());
    }

    public static SearchType getValue(String key) {
        if (key != null) {
            if (key.equals(startWith.name())) {
                return startWith;
            } else if (key.equals(contain.name())) {
                return contain;
            }else if (key.equals(equal.name())) {
                return equal;
            }
        }
        return null;
    }
}
