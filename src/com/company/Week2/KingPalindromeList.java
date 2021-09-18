package com.company.Week2;

import java.util.Scanner;

/**
 * KingsPalindrome.java
 * @author Loc Pham
 * @ID 1682512
 */

public class KingPalindromeList {

    public static long reconstructNumber(long[] digits, long length) {
        long number = 0;
        for (int i = 0; i < digits.length; i++) {
            number += digits[i] * length;
            length /= 10;

        }

        return number;
    }

    public int getLengthOfNumber(long a) {
        int length = 0;
        while (a > 0) {
            a = a/10;
            length++;
        }
        return length;
    }

    public long[] getDigitsOfNumber(long number, int length) {
        long[] charInNumber = new long[length];

        for (int o = 0; o < length; o++) {
            charInNumber[o] = number % 10;
            number /= 10;
        }

        return charInNumber;
    }

    public static long[] sortAscending(long[] arr) {
        int pos;
        long temp;
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

    public static long derivePalindrome(long[] biggestPalindrome, int lengthDifference) {
        // Create another array with same length as the element that is being compared
        long[] comparable = new long[biggestPalindrome.length - lengthDifference];

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

        return reconstructNumber(comparable, (long) Math.pow(10, comparable.length - 1));
    }

    public static long[] addValueToArray(long[] a, long element, int length) {
        length++;

        long[] b = new long[length];

        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }

        b[length - 1] = element;

        return b;

    }

    /**
     * This method will remove the value at provided index
     */
    public static long[] removeElement(long[] arr, int index) {
        long[] b = new long[arr.length - 1];

        int k = 0;
        for (int i = 0;  i < arr.length; i++) {
            if (i != index) {
                b[k] = arr[i];
                k++;
            }
        }

        return b;
    }

    /**
     * This method will perform task 1 of the assignment
     */
    public long[] task1(long[] palindromeList){
        int index = 0;

        for (long number : palindromeList) {
            int length = getLengthOfNumber(number); // The number of characters of the current number
            long[] charInNumber = getDigitsOfNumber(number, length); // The array to store digits of the current number

            long[] rightHandDigits = new long[0];
            long[] leftHandDigits = new long[0];

            // Get right-hand and left-hand side digits
            for (int i = 0; i < charInNumber.length/2; i++) {
                    leftHandDigits = addValueToArray(leftHandDigits, charInNumber[charInNumber.length / 2 + (i + 1)], leftHandDigits.length);
                    rightHandDigits = addValueToArray(rightHandDigits, charInNumber[charInNumber.length / 2 - (i + 1)], rightHandDigits.length);
            }

            long leftHandNumber = reconstructNumber(leftHandDigits, (long) Math.pow(10, leftHandDigits.length - 1));
            long rightHandNumber = reconstructNumber(rightHandDigits, (long) Math.pow(10, rightHandDigits.length - 1));

            // Compare the left and the right-hand side of the number
            if (rightHandNumber > leftHandNumber) {
                charInNumber[charInNumber.length / 2]++;

                if (charInNumber[charInNumber.length / 2] == 10) {
                    charInNumber[charInNumber.length / 2] = 0;
                    for (int i = 0; i < leftHandDigits.length - 1; i++) {
                        if (leftHandDigits[i]++ == 10) {
                            leftHandDigits[i] = 0;
                        }
                    }
                }
            }

            for (int i = leftHandDigits.length - 1; i >= 0; i--) {
                charInNumber[(leftHandDigits.length - 1) - i] = leftHandDigits[i];
                charInNumber[charInNumber.length - (i + 1)] = leftHandDigits[(leftHandDigits.length - 1) - i];
            }

            // Reconstruct the array
            long coefficient = (long) Math.pow(10, length-1);
            palindromeList[index] = reconstructNumber(charInNumber, coefficient);
            index++;
        }

        return palindromeList;
    }

    /**
     * This method will perform task 2 of the assignment
     */
    public void task2(long[] list){
        // Get correct list
        list = task1(list);
        list = sortAscending(list);

        long[][] foundedMiddleValues = new long[10][0];

        // Break down all numbers to get the middle number that occur the most
        for (long number : list) {
            int length = getLengthOfNumber(number);
            long[] digits = getDigitsOfNumber(number, length);

            foundedMiddleValues[(int) digits[length/2]] = addValueToArray(foundedMiddleValues[(int) digits[length/2]], number, foundedMiddleValues[(int) digits[length/2]].length);
        }

        // Sort all smaller arrays by its length
        for (int i = 0; i < foundedMiddleValues.length; i++) {
            int pos;
            long[] temp;
            for (int k = 0; k < foundedMiddleValues.length; k++)
            {
                pos = k;
                for (int j = k+1; j < foundedMiddleValues.length; j++) {
                    //find the index of the minimum element
                    if (foundedMiddleValues[i].length < foundedMiddleValues[pos].length)
                    {
                        pos = j;
                    }
                }

                //swap the current element with the minimum element
                temp = foundedMiddleValues[pos];
                foundedMiddleValues[pos] = foundedMiddleValues[i];
                foundedMiddleValues[i] = temp;
            }
        }

        // Get all sets whose occurrence is more than 1
        long[] index = new long[0];
        for (int i = 0; i < foundedMiddleValues.length; i++) {
            if (foundedMiddleValues[i].length > 1) {
                index = addValueToArray(index, i, index.length);
            }
        }

        // Check if the set is magical
        for (long i : index) {
            long longestPalindrome = foundedMiddleValues[(int) i][foundedMiddleValues[(int) i].length - 1];
            int lengthOfLongestPalindrome = getLengthOfNumber(longestPalindrome);
            long[] digitsOfLongestPalindrome = getDigitsOfNumber(longestPalindrome, lengthOfLongestPalindrome);
            for (int k = 1; k < foundedMiddleValues[(int) i].length - 1; k++) {
                int lengthOfNumber = getLengthOfNumber(foundedMiddleValues[(int) i][k]);

                if (foundedMiddleValues[(int) i][k] != derivePalindrome(digitsOfLongestPalindrome, lengthOfLongestPalindrome - lengthOfNumber)) {
                    foundedMiddleValues[(int) i] = removeElement(foundedMiddleValues[(int) i], k);
                }
            }
        }

        // Get all the longest arrays
        // Convert this to a while loop
        int longestArrayLength = foundedMiddleValues[0].length;
        long[] longestIndices = new long[0];
        for (int i = 0; i < foundedMiddleValues.length; i++) {
            if (foundedMiddleValues[i].length == longestArrayLength) {
                longestIndices = addValueToArray(longestIndices, i, longestIndices.length);
            } else if (foundedMiddleValues[i].length != longestArrayLength) {
                break;
            }
        }

        // Check which set has the greatest palindrome
        int greatestIndex = 0;
        long greatestPalindrome = 0;
        for (int i = 0; i < longestIndices.length - 1; i++) {
            if (foundedMiddleValues[(int) longestIndices[i]][foundedMiddleValues[(int) longestIndices[i]].length - 1] > greatestPalindrome) {
                greatestIndex = i;
                greatestPalindrome = foundedMiddleValues[i][foundedMiddleValues[i].length - 1];
            }
        }

        // Output the largest magical set
        System.out.println(foundedMiddleValues[greatestIndex].length);

    }

    /**
     * This method will perform task 3 of the assignment
     */
    public void task3(long[] list){

    }

    public void run(){
        Scanner input = new Scanner(System.in); // Declare scanner to read user input

        int choice = input.nextInt(); // Get the user's choice of task
        int length = input.nextInt(); // Get the number of elements from the King Advisor's list
        long[] palindromeList = new long[length]; // The array to store the elements of the King Advisor's list

        for (int i = 0; i < length; i++){
            palindromeList[i] = input.nextLong();
        }

        switch (choice) {
            case 1:
                palindromeList = task1(palindromeList);
                for (int i = 0; i < palindromeList.length; i++){
                    System.out.print(palindromeList[i]);

                    if (i != palindromeList.length - 1) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
                break;
            case 2:
                task2(palindromeList);
                break;
            case 3:
                task3(palindromeList);
                break;

        }
    }

    public static void main(String[] args) {
        (new KingPalindromeList()).run();
    }
}
