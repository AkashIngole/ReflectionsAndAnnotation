package com.learning.reflection;

import java.lang.reflect.Constructor;

public class ConstructorInfo {
    public static void main(String[] args) throws Exception{
        Class<?> entityClass = Class.forName("com.learning.reflection.Entity");

        System.out.println("\nGetting constructors:");
        Constructor<?>[] constructors = entityClass.getConstructors();
        for(Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        System.out.println("\nGetting declared constructors: (including private)");
        Constructor<?>[] declaredConstructors = entityClass.getDeclaredConstructors();
        for(Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }

        System.out.println("\nUsing public constructor to get Entity instance");
        Constructor<?> publicConstructor = entityClass.getConstructor(int.class, String.class);
        Entity publicEntity = (Entity) publicConstructor.newInstance(10, "studentID");
        System.out.println(publicEntity);

        System.out.println("\nUsing public constructor to get Entity instance");
        Constructor<?> privateConstructor = entityClass.getDeclaredConstructor();
        privateConstructor.setAccessible(true);
        Entity privateEntity = (Entity) privateConstructor.newInstance();
        System.out.println(privateEntity);

    }
}
