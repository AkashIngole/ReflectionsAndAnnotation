package com.learning.reflection;

import java.lang.reflect.Method;

public class MethodInfo {
    public static void main(String[] args) throws Exception {
        Entity entity = new Entity(10, "id");
        Class<? extends Entity> class1 = entity.getClass();

        System.out.println("\nGetting public methods of the class and its super-class using getMethod()");
        Method[] methods = class1.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        System.out.println("\nGetting private methods of the class using getDeclaredMethod()");
        Method[] declaredMethods = class1.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }

        Method setValueMethod = class1.getMethod("setVal", int.class);
        setValueMethod.invoke(entity, 15);

        Method getValueMethod = class1.getDeclaredMethod("getVal", null);
        getValueMethod.setAccessible(true);
        System.out.println(getValueMethod.invoke(entity, null));
    }
}
