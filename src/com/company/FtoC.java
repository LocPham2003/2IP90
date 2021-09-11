package com.company;

import java.util.Scanner;

public class FtoC {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give a temperature in Fahrenheit");
        double f = scanner.nextDouble();

        double constant = 5.0/9.0;
        double c = constant * (f - 32);

        System.out.println(c);

        /*
        2.
        The body temperature that Gabriel Fahrenheit used in degree Celsius is 35.5 ~ 36

        3. The beginning point is -17.8 degree Celsius
        32 Fahrenheit is 0 degree Celsius
        96 is 36 degree celsius

        Because these are the measurable temperature that he has.
         */
    }
}
