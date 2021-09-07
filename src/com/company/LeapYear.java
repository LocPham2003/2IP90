package com.company;

public class LeapYear {

    public static void main(String[] args) {
        int year = 2015;
        boolean leapYear = false;

        if (year % 4 == 0){
            leapYear = true;
        } else {
            leapYear = false;
        }

        System.out.println("The statement year " + year + " is a leap year is " + leapYear);
    }
}
