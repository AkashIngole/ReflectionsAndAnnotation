package com.learning.annotation;

import java.util.ArrayList;

class Parent {
    public void m1() {
        System.out.println("m1 Parent Implementation");
    }

    @Deprecated
    public void m2(int a) {
        System.out.println("m1 Parent Implementation, a = " + a);
    }
}
public class GeneralPurposeAnnotations extends Parent{

    @Override
    public void m1() {
        System.out.println("m1 Child Implementation");
    }

    public static void main(String[] args) {
        int a = 10;

        @SuppressWarnings({"rawtypes", "unused"})
        ArrayList aList = new ArrayList();

        GeneralPurposeAnnotations generalPurposeAnnotations = new GeneralPurposeAnnotations();
        generalPurposeAnnotations.m2(a);

//        Integer i = new Integer(9);

        MyFunctionalInterface impl = () -> System.out.println("Method invoked.");
        impl.method();
    }
}

@FunctionalInterface
interface MyFunctionalInterface {
    public void method();

//    Cannot add more than one abstract method when using @FunctionalInterface
}
