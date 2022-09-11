package com.majidmostafavi.jakartaee10prime12.core.utils;

import java.util.regex.Pattern;

/**
 * Created by majid on 12/20/16.
 */
public class ExceptionUtils {

    public static String  isForeignKeyException(Throwable throwable) {
        if (Pattern.compile(Pattern.quote("foreign key constraint"), Pattern.CASE_INSENSITIVE).matcher(throwable.getMessage()).find()
                || Pattern.compile(Pattern.quote("integrity constraint"), Pattern.CASE_INSENSITIVE).matcher(throwable.getMessage()).find()) {
            int fkIndex = throwable.getMessage().toLowerCase().indexOf("fk_");
            int endIndex = throwable.getMessage().indexOf(')', fkIndex);
            if (endIndex < 0) {
                endIndex = throwable.getMessage().indexOf('"', fkIndex);
            }
            if (endIndex < 0) {
                endIndex = throwable.getMessage().indexOf('\'', fkIndex);
            }
            return throwable.getMessage().substring(fkIndex, endIndex).toUpperCase();
        } else {
            Throwable cause = throwable.getCause();
            if (cause == null) {
                return null;
            } else {
                return isForeignKeyException(cause);
            }
        }
    }
    public static String isUniqueException(Throwable throwable) {
        if (Pattern.compile(Pattern.quote("unique"), Pattern.CASE_INSENSITIVE).matcher(throwable.getMessage()).find()
                || Pattern.compile(Pattern.quote("duplicate"), Pattern.CASE_INSENSITIVE).matcher(throwable.getMessage()).find()) {
            int ukIndex = throwable.getMessage().toLowerCase().indexOf("uk_");
            int endIndex = throwable.getMessage().indexOf(')', ukIndex);
            if (endIndex < 0) {
                endIndex = throwable.getMessage().indexOf('"', ukIndex);
            }
            if (endIndex < 0) {
                endIndex = throwable.getMessage().indexOf('\'', ukIndex);
            }
            return throwable.getMessage().substring(ukIndex, endIndex).toUpperCase();
        } else {
            Throwable cause = throwable.getCause();
            if (cause == null) {
                return null;
            } else {
                return isUniqueException(cause);
            }
        }
    }
    public static String isConstraintException(Throwable throwable) {
        if (Pattern.compile(Pattern.quote("constraint"), Pattern.CASE_INSENSITIVE).matcher(throwable.getMessage()).find()
                || Pattern.compile(Pattern.quote("check constraint"), Pattern.CASE_INSENSITIVE).matcher(throwable.getMessage()).find()) {
            Throwable cause = throwable.getCause().getCause();
            int cnIndex = cause.getMessage().indexOf(":");
            int endIndex = cause.getMessage().indexOf(')', cnIndex);
            if (endIndex < 0) {
                endIndex = cause.getMessage().indexOf('"', cnIndex);
            }
            if (endIndex < 0) {
                endIndex = cause.getMessage().indexOf('\'', cnIndex);
            }
            if (endIndex < 0) {
                endIndex = cause.getMessage().length();
            }
            return cause.getMessage().substring(cnIndex, endIndex).toUpperCase();
        } else {
            Throwable cause = throwable.getCause();
            if (cause == null) {
                return null;
            } else {
                return isConstraintException(cause);
            }
        }
    }
}
