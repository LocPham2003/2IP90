package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TestCases {
    static boolean setFound = false;

    public void sum_up_recursive(ArrayList<Integer> numbers, int target, ArrayList<Integer> partial) {
        int s = 0;
        for (int x : partial) {
            s += x;
        }
        if (s == target) {
            System.out.println(partial.size());
            System.out.println("sum(" + Arrays.toString(partial.toArray()) + ")=" + target);
            setFound = true;

        }

        // Base case
        if (s >= target) {
            return;
        }
        for (int i = 0; i < numbers.size(); i++) {
            if (setFound) {
                break;
            }
            int n = numbers.get(i);
            ArrayList<Integer> remaining = new ArrayList<>(numbers);
            remaining.remove(0);
            partial.add(n);
            sum_up_recursive(remaining, target, partial);
            partial.remove(partial.size() - 1);

        }

    }

    public void sum_up(ArrayList<Integer> numbers, int target) {
        sum_up_recursive(numbers, target, new ArrayList<>());
    }

    public static void main(String[] args) {


//        int firstIndex = -1;
//        firstIndex = scanner.nextInt();
//
//        for (int i = 0; i < firstIndex; i++) {
//            arrayList.add(scanner.nextInt());
//        }
//
//        for (int k = 0; k < firstIndex - 1; k++) {
//            arrayList1.add(scanner.nextInt());
//        }
    }
}