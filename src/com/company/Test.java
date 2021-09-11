package com.company;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        float x, y, a, b, d;

        Scanner scanner = new Scanner(System.in);

        x = scanner.nextFloat();
        y = scanner.nextFloat();
        a = scanner.nextFloat();
        b = scanner.nextFloat();
        d = scanner.nextFloat();

        System.out.println(x + " " + y + " " + a + " " + b + " " + d);
    }
}
