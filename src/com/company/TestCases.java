package com.company;

import java.util.Scanner;

public class TestCases {


    static Scanner scanner = new Scanner(System.in);
    static int index = 0;
    public static int recursion(String command) {
        if (command.equals("-1")) {
            return -1;
        }

        System.out.println(command);

        return recursion(scanner.next());
    }

    public static void main(String[] args) {
       recursion(scanner.next());
    }
}
