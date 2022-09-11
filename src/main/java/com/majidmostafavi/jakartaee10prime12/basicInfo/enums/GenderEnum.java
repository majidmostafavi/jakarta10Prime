package com.majidmostafavi.jakartaee10prime12.basicInfo.enums;

import java.util.Arrays;
import java.util.List;

public enum GenderEnum {
    male("male","message.gender.male"),
    female("female","message.genre.female");

    private final String key;
    private final String name;

    GenderEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }
    public String getName() {
        return name;
    }

    public static  GenderEnum getValue (String key){
        if (key!=null){
            if (key.equals(male.getKey())){
                return male;
            }else if (key.equals(female.getKey())){
                return female;
            }
        }
        return null;
    }

    public static List<GenderEnum> findAll(){return Arrays.asList(values());}
}
