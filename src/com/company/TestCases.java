package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TestCases {

    public static boolean isThereValue(String i, Scanner scanner) {
        if (!scanner.hasNext()) {
            return true;
        }

        return isThereValue(scanner.next(), scanner);
    }

    public static void main(String[] args) {
       String hello = "H e l l o bitch";
       Scanner scanner = new Scanner(hello);

       for (int i = 0; i < hello.length(); i++) {
           System.out.println(scanner.next());
       }

    }
}
