package com.majidmostafavi.jakartaee10prime12.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface UsecaseTypeAnnotation {
    String usecaseType();
}
