package com.learning.annotation;

import java.lang.annotation.*;

//Below are meta-annotations
@Documented
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME) // specify lifespan of annotation
public @interface MostUsed {

    String value() default "Java";
}
