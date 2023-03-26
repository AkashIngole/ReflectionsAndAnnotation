package com.learning.reflection;

public class GettingClassObject {
    public static void main(String[] args) throws Exception{
        // forName
        Class<?> class1 = Class.forName("java.lang.String");
        Class<?> class2 = Class.forName("java.lang.String");
        System.out.println(class1 == class2);

//        Classname.class refers to object of that class
//        primitive types can also be represented as objects

        Class<?> class3 = int.class;
        Class<?> class4 = String.class;

//        object.getClass()
        MyClass myClass = new MyClass();
        Class<? extends MyClass> class5 = myClass.getClass();
        System.out.println(class5);

//        get super class
        Class<?> superClass = class5.getSuperclass();
        System.out.println(superClass);

//        get interfaces implemented by a class
        Class<?>[] interfaces = class1.getInterfaces();

//        getName
        System.out.println(class1.getName());
    }
}
