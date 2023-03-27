package com.learning.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ModifierInfo {
    public static void main(String[] args) throws Exception{
        Entity entity = new Entity(10, "id");
        Class<? extends Entity> entityClass = entity.getClass();

        int modifierIntValue = entityClass.getModifiers();
        System.out.println(modifierIntValue & Modifier.PUBLIC);
        System.out.println(Modifier.isPublic(modifierIntValue));
        System.out.println(Modifier.toString(modifierIntValue));

        Method method = entityClass.getMethod("getVal");
        int methodModifier = method.getModifiers();
        System.out.println(methodModifier & Modifier.PRIVATE);
        System.out.println(Modifier.isPrivate(methodModifier));
        System.out.println(Modifier.toString(methodModifier));
    }
}
