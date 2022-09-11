package com.majidmostafavi.jakartaee10prime12.core.utils;

//import ir.ac.tums.common.entity.CmCurrencyType;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Created by majid on 6/25/16.
 */
public class TextUtils {

//    public static String numberToTextWithCurrencyType(Double number, CmCurrencyType currencyType){
//        if (currencyType!=null && currencyType.getId()!=null){
//            return numberToText(number) + " " + currencyType.getName();
//        }
//        return numberToText(number);
//    }

    public static String numberToText(Double number) {
        DecimalFormat df = new DecimalFormat("#.", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340); //340 = DecimalFormat.DOUBLE_FRACTION_DIGITS

        String input = df.format(number);//output: 0.00000021
        String[] parts = input.split("\\.");
        String mainNumber = numberToText(parts[0]);
        if (parts.length > 1) {
            if (parts[1].trim().length() > 0) {
                if (Long.parseLong(parts[1].trim()) != 0l) {
                    mainNumber = mainNumber + " ممیز " + numberToText(parts[1]);
                }
            }
        }
        return mainNumber;
    }

    public static String numberToText(Long number) {
        DecimalFormat df = new DecimalFormat("#.", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340); //340 = DecimalFormat.DOUBLE_FRACTION_DIGITS

        String input = df.format(number);//output: 0.00000021
        String[] parts = input.split("\\.");
        String mainNumber = numberToText(parts[0]);
        if (parts.length > 1) {
            if (parts[1].trim().length() > 0) {
                if (Long.parseLong(parts[1].trim()) != 0l) {
                    mainNumber = mainNumber + " ممیز " + numberToText(parts[1]);
                }
            }
        }
        return mainNumber;
    }

    public static String persianNumberToEng(String input) {
        StringBuilder output = new StringBuilder();
        input = input.trim();
        if (input.length() != 0) {
            char temp;
            int i = 0;
            while (i < input.length()) {
                temp = input.charAt(i);
                switch (temp) {
                    case '۱':
                        temp = '1';
                        break;
                    case '۲':
                        temp = '2';
                        break;

                    case '۳':
                        temp = '3';
                        break;

                    case '۴':
                        temp = '4';
                        break;

                    case '۵':
                        temp = '5';
                        break;

                    case '۶':
                        temp = '6';
                        break;

                    case '۷':
                        temp = '7';
                        break;

                    case '۸':
                        temp = '8';
                        break;

                    case '۹':
                        temp = '9';
                        break;
                    case '۰':
                        temp = '0';
                        break;
                    default:
                        break;
                }
                output.append(temp);
                i++;
            }
        }
        return output.toString();
    }

    public static String numberToText(String input) {
        StringBuilder output = new StringBuilder();
        if (!(input.trim().length() == 0)) {
            String temp = "";
            int i = input.length() - 1;//because index start with 0
            int j = 0;
            int part = 0;
            while (i >= 0) {
                temp = String.valueOf(input.charAt(i));
                if ((i != 0) && (j == 0) & (input.charAt(i - 1) == '1'))//( 0xf8 & 0x3f); bitwise and —>0×38
                {
                    temp = input.substring(i - 1, i + 1);
                    temp = twoDigitToText(temp);
                    output.insert(0, temp);
                    if ((i != 0) & ((i - 1) != 0))
                        output.insert(0, " و ");
                    i = i - 2;
                    j++;
                } else {
                    temp = digitToText(temp, j);
                    if (!(temp.trim().length() == 0)) {
                        output.insert(0, temp);
                        if (i != 0)
                            output.insert(0, " و ");
                    }
                    i--;
                }
                if (j == 2) {
                    part++;
                    if ((i != -1) && mustShowPartName(input, i))
                        output.insert(0, " " + partsName(part) + " ");
                    j = 0;
                } else
                    j++;
            }
        }
        return output.toString();
    }

    private static boolean mustShowPartName(String input, int index) {
        index = input.length() - index - 1;
        int ceil = (int) Math.ceil(index / 3) * 3 + 3;
        ceil = ceil < input.length() ? ceil : input.length();
        int floor = (int) Math.floor(index / 3) * 3;
        String str = input.substring(input.length() - ceil, input.length() - floor);
        if (str.equals("000") || str.equals("00") || str.equals("0")) {
            return false;
        }
        return true;
    }

    private static String partsName(int part) {
        String output = "";
        switch (part) {
            case 0:
                output = "";
                break;
            case 1:
                output = "هزار";
                break;
            case 2:
                output = "میلیون";
                break;
            case 3:
                output = "میلیارد";
                break;
            case 4:
                output = "بیلیون";
                break;
            case 5:
                output = "بیلیارد";
                break;
            case 6:
                output = "تریلیون";
                break;
            case 7:
                output = "تریلیارد";
                break;
            default:
                break;
        }
        return output;
    }

    private static String digitToText(String digitStr, int order) {
        String output = "";
        int digit = Integer.parseInt(digitStr);
        if (order == 0) {
            switch (digit) {
                case 0:
                    output = "";
                    break;
                case 1:
                    output = "یک";
                    break;
                case 2:
                    output = "دو";
                    break;
                case 3:
                    output = "سه";
                    break;
                case 4:
                    output = "چهار";
                    break;
                case 5:
                    output = "پنج";
                    break;
                case 6:
                    output = "شش";
                    break;
                case 7:
                    output = "هفت";
                    break;
                case 8:
                    output = "هشت";
                    break;
                case 9:
                    output = "نه";
                    break;
                default:
                    output = "";
                    break;
            }
        }
        if (order == 1) {
            switch (digit) {
                case 0:
                    output = "";
                    break;
                case 1:
                    output = "";
                    break;
                case 2:
                    output = "بیست";
                    break;
                case 3:
                    output = "سی";
                    break;
                case 4:
                    output = "چهل";
                    break;
                case 5:
                    output = "پنجاه";
                    break;
                case 6:
                    output = "شصت";
                    break;
                case 7:
                    output = "هفتاد";
                    break;
                case 8:
                    output = "هشتاد";
                    break;
                case 9:
                    output = "نود";
                    break;
                default:
                    output = "";
                    break;
            }
        }
        if (order == 2) {
            switch (digit) {
                case 0:
                    output = "";
                    break;
                case 1:
                    output = "صد";
                    break;
                case 2:
                    output = "دویست";
                    break;
                case 3:
                    output = "سیصد";
                    break;
                case 4:
                    output = "چهارصد";
                    break;
                case 5:
                    output = "پانصد";
                    break;
                case 6:
                    output = "ششصد";
                    break;
                case 7:
                    output = "هفتصد";
                    break;
                case 8:
                    output = "هشتصد";
                    break;
                case 9:
                    output = "نهصد";
                    break;
                default:
                    output = "";
                    break;
            }
        }
        return output;
    }

    private static String twoDigitToText(String digitsStr) {
        String output = "";
        int digits = Integer.parseInt(digitsStr);
        switch (digits) {
            case 10:
                output = "ده";
                break;
            case 11:
                output = "یازده";
                break;
            case 12:
                output = "دوازده";
                break;
            case 13:
                output = "سیزده";
                break;
            case 14:
                output = "چهارده";
                break;
            case 15:
                output = "پانزده";
                break;
            case 16:
                output = "شانزده";
                break;
            case 17:
                output = "هفده";
                break;
            case 18:
                output = "هجده";
                break;
            case 19:
                output = "نوزده";
                break;
            default:
                output = "";
                break;
        }
        return output;
    }
}
