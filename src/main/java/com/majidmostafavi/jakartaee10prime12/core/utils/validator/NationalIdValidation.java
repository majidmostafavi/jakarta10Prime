package com.majidmostafavi.jakartaee10prime12.core.utils.validator;

/**
 * Created by nahal on 3/4/17.
 */

public abstract class NationalIdValidation {

    public static boolean validation(String companyId){

        int [] companyIdList =new int[companyId.length()];

        for (int i=0; i<companyId.length() ;i++ ) {
            companyIdList[i] = Integer.parseInt(String.valueOf(companyId.charAt(i)));
        }

        int controlNumber = companyIdList[10];
        int plusNumber = companyIdList[9]+2;

        for (int i=0; i<10; i++) {
            companyIdList[i] += plusNumber;
        }

        int sum = ((companyIdList[0] * 29) + (companyIdList[1] * 27) + (companyIdList[2] * 23) + (companyIdList[3] * 19) + (companyIdList[4] * 17) +
                   (companyIdList[5] * 29) + (companyIdList[6] * 27) + (companyIdList[7] * 23) + (companyIdList[8] * 19) + (companyIdList[9] * 17));

        int remain = sum % 11;

        if (remain == 10) {
            remain = 0;
        }

        if (remain == controlNumber) {
            return true;
        } else {
            return false;
        }

    }
}
