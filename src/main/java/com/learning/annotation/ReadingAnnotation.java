package com.learning.annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReadingAnnotation {
    public static void main(String[] args) throws Exception{
        Class<?> utilityClass = Class.forName("com.learning.annotation.Utility");
        Constructor<?> utilityConstructor = utilityClass.getConstructor();
        Utility utilityInstance = (Utility) utilityConstructor.newInstance();

        Method[] utilityMethods = utilityClass.getDeclaredMethods();

        for(Method method : utilityMethods) {
            if(method.isAnnotationPresent(MostUsed.class)) {
//                method.invoke(utilityInstance, "Scala");
                MostUsed mostUsedAnnotation = method.getAnnotation(MostUsed.class);
                String value = mostUsedAnnotation.value();
                method.invoke(utilityInstance, value);
            }
        }
    }
}
