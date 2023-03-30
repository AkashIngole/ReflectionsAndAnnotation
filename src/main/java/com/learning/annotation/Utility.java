package com.learning.annotation;

@MostUsed
public class Utility {
    void doStuff() {
        System.out.println("Doing something!");
    }

    @MostUsed("Python")
    void doStuff(String str) {
        System.out.println("Operating on str = " + str);
    }

    void doStuff(int i) {
        System.out.println("Operating on i = " + i);
    }
}


// By default, @Inherited annotation will be inherited by SubUtility class
class SubUtility extends Utility{

}