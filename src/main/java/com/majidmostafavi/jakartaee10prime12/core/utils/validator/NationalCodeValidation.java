package com.majidmostafavi.jakartaee10prime12.core.utils.validator;

/**
 * Created by HosseinKhoshkar on 8/24/2016.
 */

public abstract class NationalCodeValidation {

    public static boolean validation(String personCode) {

        int [] personCodeList =new int[personCode.length()];
        for (int i=0; i<personCode.length() ;i++ ) personCodeList[i] = Integer.parseInt(String.valueOf(personCode.charAt(i)));
        switch (personCode)
        {
            case "0000000000":
            case "1111111111":
            case "22222222222":
            case "33333333333":
            case "4444444444":
            case "5555555555":
            case "6666666666":
            case "7777777777":
            case "8888888888":
            case "9999999999":
                return false;
        }
        int num2 = personCodeList[9];
        int num3=0;
        int temp=10;
        for (int i=0;i<9;i++) num3 +=personCodeList[i]*temp--;
        int num4 = num3 - ((num3 / 11) * 11);
        if ((((num4 == 0) && (num2 == num4)) || ((num4 == 1) && (num2 == 1))) || ((num4 > 1) && (num2 == Math.abs((int)(num4 - 11)))))
        {
            return true;
        }
        else
        {
            return false;
        }


    }
}
