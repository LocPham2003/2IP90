package com.company.Week2;

import java.util.Scanner;


/**
 * KingsPalindrome.java
 * @author Loc Pham
 * @ID 1682512
 */

public class KingPalindromeList {
    /**
     * This function reverse an array for us
     */
    public static int[] reverse(int[] a, int n)
    {
        int[] b = new int[n];
        int j = n;
        for (int i = 0; i < n; i++) {
            b[j - 1] = a[i];
            j--;
        }

        return b;
    }

    public static int reconstructNumber(int[] digits, double length) {
        int number = 0;

        for (int i : digits) {
            number += i * length;
            length /= 10;
        }

        return number;
    }

    public int getLengthOfNumber(int a) {
        int length = 0;
        while (a > 0) {
            a = a/10;
            length++;
        }
        return length;
    }

    public int[] getDigitsOfNumber(int number, int length) {
        int[] charInNumber = new int[length];

        for (int o = 0; o < length; o++) {
            charInNumber[o] = number % 10;
            number /= 10;
        }

        return charInNumber;
    }

    public static int[] sortAscending(int[] arr) {
        int pos;
        int temp;
        for (int i = 0; i < arr.length; i++)
        {
            pos = i;
            for (int j = i+1; j < arr.length; j++)
            {
                if (arr[j] < arr[pos])                  //find the index of the minimum element
                {
                    pos = j;
                }
            }

            temp = arr[pos];            //swap the current element with the minimum element
            arr[pos] = arr[i];
            arr[i] = temp;
        }

        return arr;
    }

    public static int derivePalindrome(int[] biggestPalindrome, int lengthDifference) {
        // Create another array with same length as the element that is being compared
        int[] comparable = new int[biggestPalindrome.length - lengthDifference];

        // Check if a palindrome can be derived from the biggest palindrome
        for (int i = 0; i < lengthDifference/2; i++) {
            biggestPalindrome[i] = -1;
            biggestPalindrome[biggestPalindrome.length - (i + 1)] = -1;
        }

        int index = 0;
        int otherIndex = 0;
        while (index < comparable.length) {
            if (biggestPalindrome[otherIndex] != -1) {
                comparable[index] = biggestPalindrome[otherIndex];
                index++;
            }
            otherIndex++;
        }

        return reconstructNumber(comparable, Math.pow(10, comparable.length));
    }

    /**
     * This method will perform task 1 of the assignment
     */
    public int[] task1(int[] palindromeList){
        int index = 0;

        for (int number : palindromeList) {
            int length = getLengthOfNumber(number); // The number of characters of the current number
            int[] charInNumber = getDigitsOfNumber(number, length); // The array to store digits of the current number

            charInNumber = reverse(charInNumber, length);

            // Compare digits and correct numbers to palindrome
            int o = 0;
            while (o < length/2) {
                if (charInNumber[length / 2 - (o + 1)] < charInNumber[length / 2 + (o + 1)]) {
                    charInNumber[length / 2] += 1;
                    o += length / 2;

                    if (charInNumber[length / 2] == 10) {
                        charInNumber[length / 2] = 0;
                        charInNumber[length / 2 - 1]++;
                    }
                } else {
                    o++;
                }
            }

            o = 0;
            while (o < length/2) {
                charInNumber[length - (o + 1)] = charInNumber[o];
                o++;
            }

            // Reconstruct the array
            double coefficient = Math.pow(10, length-1);

            palindromeList[index] = reconstructNumber(charInNumber, coefficient);
            index++;
        }

        return palindromeList;
    }

    public static int[] addValueToArray(int[] a, int element, int length) {
            length++;

            int[] b = new int[length];

            for (int i = 0; i < a.length; i++) {
                b[i] = a[i];
            }

            b[length - 1] = element;

            return b;

    }

    /**
     * This method will perform task 2 of the assignment
     */
    public void task2(int[] list){
        // Get correct list
        list = task1(list);
        list = sortAscending(list);

        int[][] foundedMiddleValues = new int[10][0];

        // Break down all numbers to get the middle number that occur the most
        for (int number : list) {
            int length = getLengthOfNumber(number);
            int[] digits = getDigitsOfNumber(number, length);

            foundedMiddleValues[digits[length/2]] = addValueToArray(foundedMiddleValues[digits[length/2]], number, foundedMiddleValues[digits[length/2]].length);
        }


        // Get all sets whose occurrence is more than 1
        int[] index = new int[0];
        for (int i = 0; i < foundedMiddleValues.length; i++) {
            if (foundedMiddleValues[i].length > 1) {
                index = addValueToArray(index, i, index.length);
            }
        }

        // Check if the set is magical
        for (int i : index) {
            int longestPalindrome = foundedMiddleValues[i][foundedMiddleValues[i].length - 1];
            int lengthOfLongestPalindrome = getLengthOfNumber(longestPalindrome);
            int[] digitsOfLongestPalindrome = getDigitsOfNumber(longestPalindrome, lengthOfLongestPalindrome);
            for (int k = 1; k < foundedMiddleValues[i].length - 1; k++) {
                int lengthOfNumber = getLengthOfNumber(foundedMiddleValues[i][k + 1]);

                if (foundedMiddleValues[i][k] != derivePalindrome(digitsOfLongestPalindrome, lengthOfLongestPalindrome - lengthOfNumber)) {
                    // Remove this value
                } else {
                    // Keep it
                }

            }
        }
    }

    /**
     * This method will perform task 3 of the assignment
     */
    public void task3(int[] list){

    }

    public void run(){
        Scanner input = new Scanner(System.in); // Declare scanner to read user input

        int choice = input.nextInt(); // Get the user's choice of task
        int length = input.nextInt(); // Get the number of elements from the King Advisor's list
        int[] palindromeList = new int[length]; // The array to store the elements of the King Advisor's list

        for (int i = 0; i < length; i++){
            palindromeList[i] = input.nextInt();
        }

        switch (choice) {
            case 1:
                for (int number : task1(palindromeList)){
                    System.out.print(number + " ");
                }
                System.out.println();
            case 2:
                task2(palindromeList);
            case 3:
                task3(palindromeList);
        }
    }

    public static void main(String[] args) {
        (new KingPalindromeList()).run();
    }
}
