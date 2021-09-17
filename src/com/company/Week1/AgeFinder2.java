package com.company.Week1;

import java.util.Scanner;

public class AgeFinder2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int currentDate, birthDate;

        System.out.println("Type in your date of birth");
        birthDate = scanner.nextInt();
        System.out.println("Type in the current date");
        currentDate = scanner.nextInt();

        int age = (currentDate - birthDate) / 10000;
        System.out.println("You are " + age + " years old");
    }
}
