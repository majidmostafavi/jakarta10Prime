package com.majidmostafavi.jakartaee10prime12.core.utils;

/**
 * Created by majid on 9/26/16.
 */
public class PersianUTF {
    private static char YE_ARABIC = 1610;
    private static char K_ARABIC = 1603;
    private static char YE_FARSI = 1740;
    private static char K_FARSI = 1705;

    public static String arabicRevision(String text) {
        return text.replace(K_FARSI, K_ARABIC).replace(YE_FARSI, YE_ARABIC);
    }

    public static String farsiRevision(String text) {
        return text.replace(K_ARABIC, K_FARSI).replace(YE_ARABIC, YE_FARSI);
    }
}
