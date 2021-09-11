package com.company;

public class AgeFinder {

    public static void main(String[] args) {
        int dateOfBirth = 19930523;
        int currentDate = 20140524;

        int age = (currentDate - dateOfBirth) / 10000;

        System.out.println("You are " + age + " years old");
    }


}
