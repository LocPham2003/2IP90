package com.company.Week3;

import java.util.Scanner;

/**
 * ApeIntelligence.java
 * @author Loc Pham
 * @ID 1682512
 */

public class ApeIntelligence {

    public static int disassembleArray(int length, String[] arr) {
        if (length == arr.length) {
            return arr.length;
        }

        System.out.println(arr[length]);

        return length + disassembleArray(length + 1, arr);
    }

    public static void part1(String command) {
        String[] arr = command.split(" ");
        disassembleArray(0, arr);
    }

    public static void part2(String command) {

    }

    public static void part3(String command) {

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String part = input.nextLine();
        String command = input.nextLine();

        switch (part) {
            case "Part 1" -> part1(command);
            case "Part 2" -> part2(command);
            case "Part 3" -> part3(command);
        }
    }
}
