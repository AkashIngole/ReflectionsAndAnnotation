package com.learning.reflection;

import java.lang.reflect.Field;

public class FieldInfo {
    public static void main(String[] args) throws Exception {
        Entity entity = new Entity(10, "id");
        Class<? extends Entity> class1 = entity.getClass();


        System.out.println("\nGetting fields (prints only public fields) of that class and its super class:");
        Field[] fields = class1.getFields();
        for (Field field: fields) {
            System.out.println(field.getName());
        }

        System.out.println("\nGetting declared fields (prints all fields irrespective of access modifiers) of that class only :");
        Field[] declaredFields = class1.getDeclaredFields();
        for(Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }

        System.out.println("\nSetting the class field values:");
        Field typeField = class1.getField("type");
        Field valField = class1.getDeclaredField("val");
        typeField.set(entity, "rollNum");
//        valField.set(entity, 10101); // cannot set value of private field, raises IllegalAccessException

//        To set value of private field, set that field to accessible
        valField.setAccessible(true);
        valField.set(entity, 10101);
        System.out.println(entity);
    }
}
