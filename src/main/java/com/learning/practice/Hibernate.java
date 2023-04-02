package com.learning.practice;

import com.learning.annotation.Column;
import com.learning.annotation.PrimaryKey;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hibernate<T> {

    private Connection connection;
    private AtomicLong id = new AtomicLong(0);
    private Hibernate() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:h2:Users/HP/Documents/ReflectionsAndAnnotation/database/practice1", "sa", "");
    }
    public static <T> Hibernate<T> getConnection() throws SQLException {
        return new Hibernate<T>();
    }

    public void write(T t) throws IllegalAccessException, SQLException {
        Class<? extends Object> clsss = t.getClass();
        Field[] declaredFields = clsss.getDeclaredFields();
        Field primaryKey = null;
        List<Field> columns = new ArrayList<>();
        StringJoiner joiner = new StringJoiner(",");

        for(Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            if(declaredField.isAnnotationPresent(PrimaryKey.class)) {
                primaryKey = declaredField;
                System.out.println("The primary key is " + declaredField.getName()+ " and value = " + declaredField.get(t));
            } else if (declaredField.isAnnotationPresent(Column.class)) {
                columns.add(declaredField);
                joiner.add(declaredField.getName());
                System.out.println("The Column(s) is " + declaredField.getName()+ " and value = " + declaredField.get(t));
            }
        }
        int numberCols = joiner.length() + 1;
        String qMarks = IntStream.range(0, numberCols)
                .mapToObj(e -> "?")
                .collect(Collectors.joining(","));

        String sql = "insert into " + clsss.getSimpleName() + "( " + primaryKey.getName() +
                joiner.toString() + " ) " + "values (" + qMarks +" )";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        if(primaryKey.getType() == long.class) {
            preparedStatement.setLong(1, id.incrementAndGet());
        }

        int index = 2;
        for(Field column : columns) {
            if(column.getType() == int.class) {
                preparedStatement.setInt(index++, (int) column.get(t));
            } else if (column.getType() == String.class) {
                preparedStatement.setString(index++, (String) column.get(t));
            }
        }

        preparedStatement.executeUpdate();
    }

    public T read(Class<T> transactionHistoryClass, long l) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Field[] declaredFields = transactionHistoryClass.getDeclaredFields();
        Field primaryKey = null;

        for(Field field : declaredFields) {
            if(field.isAnnotationPresent(PrimaryKey.class)) {
                primaryKey = field;
                break;
            }
        }
        String sql = "select * from " + transactionHistoryClass.getSimpleName() +
                " where " + primaryKey.getName() + " = " + l;

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();

        T t = transactionHistoryClass.getConstructor().newInstance();
        long transactionId = rs.getInt(primaryKey.getName());
        primaryKey.setAccessible(true);
        primaryKey.set(t, transactionId);
        for(Field field : declaredFields) {
            if(field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);
                if(field.getType() == int.class) {
                    field.set(t, rs.getInt(field.getName()));
                } else if (field.getType() == String.class) {
                    field.set(t, rs.getString(field.getName()));
                }
            }
        }
        return t;
    }
}
