package com.majidmostafavi.jakartaee10prime12.core.annotation;

import com.majidmostafavi.jakartaee10prime12.core.enumration.SubSystem;
import com.majidmostafavi.jakartaee10prime12.core.enumration.UsecaseType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by majid on 5/20/17.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UsecaseSupport {
    UsecaseType usecaseType();
    SubSystem subSystem();
}
