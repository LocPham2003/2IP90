package com.company.Week2;
import java.util.Scanner;

/**
 * KingsPalindrome.java
 * @author Loc Pham
 * @ID 1682512
 */

public class KingPalindromeList {
    /**
     * This method will assemble the number from an array of digits
     */
    public static long assemble(long[] digits, double length) {
        long number = 0; // The assembled number
        for (int i = 0; i < digits.length; i++) {
            number += digits[i] * (long) length;
            length /= 10;

        }

        return number;
    }

    /**
     * This method will return the number of digits of the provided number
     */
    public int getLength(long number) {
        int length = 0; // Length of the provided number
        while (number > 0) {
            number = number/10;
            length++;
        }
        return length;
    }

    /**
     * This method will return an array of digits of the provided number
     */
    public long[] getDigits(long number, int length) {
        long[] charInNumber = new long[length]; // Store the digits of the number

        for (int o = 0; o < length; o++) {
            charInNumber[o] = number % 10;
            number /= 10;
        }

        return charInNumber;
    }

    /**
     * This method will sort the array in ascending order
     */
    public static long[] sort(long[] arr) {
        int pos; // To store the index of the minimum element
        long temp; // A temporary value for value-swapping
        for (int i = 0; i < arr.length; i++)
        {
            pos = i;
            for (int j = i+1; j < arr.length; j++)
            {
                if (arr[j] < arr[pos])  //find the index of the minimum element
                {
                    pos = j;
                }
            }

            temp = arr[pos]; //swap the current element with the minimum element
            arr[pos] = arr[i];
            arr[i] = temp;
        }

        return arr;
    }

    /**
     * This method check if another palindrome can be derived from the provided palindrome
     */
    public static long derive(long[] maxValue, int lengthDifference) {
        // Create another array with same length as the element that is being compared
        long[] comparable = new long[maxValue.length - lengthDifference]; // Digits of the derived number

        // Check if a palindrome can be derived from the biggest palindrome
        for (int i = 0; i < lengthDifference/2; i++) {
            maxValue[i] = -1;
            maxValue[maxValue.length - (i + 1)] = -1;
        }

        int index = 0; // Index of the derived number
        int otherIndex = 0; // Index of the palindrome
        while (index < comparable.length) {
            if (maxValue[otherIndex] != -1) {
                comparable[index] = maxValue[otherIndex];
                index++;
            }
            otherIndex++;
        }

        return assemble(comparable, Math.pow(10, comparable.length - 1));
    }

    /**
     * This method will add the provided value to the array
     */
    public static long[] appendValue(long[] a, long element, int length) {
        length++;

        long[] b = new long[length];

        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }

        b[length - 1] = element;

        return b;

    }

    /**
     * This method will remove the value from the array at provided index
     */
    public static long[] removeValue(long[] arr, int index) {
        long[] b = new long[arr.length - 1]; // New array tht doesn't store the provided value

        int k = 0; // The current index of array b
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
        int index = 0; // Current index of the provided palindrome list

        for (long number : palindromeList) {
            int length = getLength(number); // The number of characters of the current number
            long[] charInNumber = getDigits(number, length); // The array to store digits of the current number

            long[] rightHandDigits = new long[0]; // Store all digits in the right-hand side
            long[] leftHandDigits = new long[0]; // Store all digits in the right-hand side

            int rightLength = 0; // Current number of right-hand digits
            int leftLength = 0; // Current number of left-hand digits

            if (length % 2 != 0) {
                // Get right-hand and left-hand side digits
                for (int i = 0; i < charInNumber.length / 2; i++) {
                    int leftHandIndex = charInNumber.length / 2 + (i + 1); // Store the index of the digit to add
                    int rightHandIndex = charInNumber.length / 2 - (i + 1); // Store the index of the digit to add

                    leftHandDigits = appendValue(leftHandDigits, charInNumber[leftHandIndex], leftLength);
                    rightHandDigits = appendValue(rightHandDigits, charInNumber[rightHandIndex], rightLength);

                    rightLength = rightHandDigits.length;
                    leftLength = leftHandDigits.length;
                }

                long leftNumber = assemble(leftHandDigits, Math.pow(10, leftLength - 1)); // Construct left number
                long rightNumber = assemble(rightHandDigits, Math.pow(10, rightLength - 1)); // Construct right number

                // Compare the left and the right-hand side of the number
                if (rightNumber > leftNumber) {
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

                long coefficient = (long) Math.pow(10, length - 1); // The number of 0s that the number has
                palindromeList[index] = assemble(charInNumber, coefficient);
                index++;
            } else {
                palindromeList = removeValue(palindromeList, index);
            }
        }

        return palindromeList;
    }

    /**
     * This method will perform task 2 of the assignment
     */
    public long[] task2(long[] list){
        // Get the correct list
        list = task1(list);

        // Sort the correct list
        list = sort(list);

        long[][] commonMidDigit = new long[10][0]; // An array to store numbers who share the same middle digit

        // Break down all numbers to get the middle number that occur the most
        for (long number : list) {
            int length = getLength(number); // Store the number of digits of the current number
            long[] digits = getDigits(number, length); // Store the array of digits of the current number
            int middleValue = (int) digits[length/2]; // Get the middle value of the current number
            int currentLength = commonMidDigit[(int) digits[length/2]].length; // Get the length of the current group

            commonMidDigit[middleValue] = appendValue(commonMidDigit[middleValue], number, currentLength);
        }

        // Sort array of arrays of numbers that share the same middle digit by its length
        for (int i = 0; i < commonMidDigit.length; i++) {
            int pos; // To store the index of current smallest value
            long[] temp; // Act as a temporary value for value swapping
            for (int k = 0; k < commonMidDigit.length; k++) {
                pos = k;
                for (int j = k+1; j < commonMidDigit.length; j++) {
                    //find the index of the minimum element
                    if (commonMidDigit[i].length < commonMidDigit[pos].length) {
                        pos = j;
                    }
                }

                //swap the current element with the minimum element
                temp = commonMidDigit[pos];
                commonMidDigit[pos] = commonMidDigit[i];
                commonMidDigit[i] = temp;
            }
        }

        long[] index = new long[0]; // Store all arrays of same middle digits whose length is greater than 2

        // Get all sets whose occurrence is more than 2
        for (int i = 0; i < commonMidDigit.length; i++) {
            if (commonMidDigit[i].length > 1) {
                index = appendValue(index, i, index.length);
            }
        }

        // Check if the set is magical and remove un-magical value from set
        for (long i : index) {
            int maxValueIndex = commonMidDigit[(int) i].length - 1; // Store the index of the max value
            long maxValue = commonMidDigit[(int) i][maxValueIndex]; // Store the max value
            int maxValueLength = getLength(maxValue); // Store the number of digits of the max value
            long[] maxValueDigits = getDigits(maxValue, maxValueLength); // Store the array of digits of the max value
            for (int k = 1; k < commonMidDigit[(int) i].length - 1; k++) {
                int numberLength = getLength(commonMidDigit[(int) i][k]); // Store the length of the current number

                if (commonMidDigit[(int) i][k] != derive(maxValueDigits, maxValueLength - numberLength)) {
                    commonMidDigit[(int) i] = removeValue(commonMidDigit[(int) i], k);
                }
            }
        }

        // Convert this to a while loop
        int longestArrayLength = commonMidDigit[0].length; // Get all the longest arrays
        long[] longestIndices = new long[0];
        for (int i = 0; i < commonMidDigit.length; i++) {
            if (commonMidDigit[i].length == longestArrayLength) {
                longestIndices = appendValue(longestIndices, i, longestIndices.length);
            } else if (commonMidDigit[i].length != longestArrayLength) {
                break;
            }
        }

        // Check which set has the greatest palindrome
        int greatestIndex = 0;
        long greatestPalindrome = 0;
        for (int i = 0; i < longestIndices.length - 1; i++) {
            int finalIndex = commonMidDigit[(int) longestIndices[i]].length - 1; // Get the final index of the array
            int currentMidDigit = (int) longestIndices[i];
            long currentValue = commonMidDigit[currentMidDigit][finalIndex]; // Get the final number of the array
            if (currentValue > greatestPalindrome) {
                greatestIndex = i;
                greatestPalindrome = commonMidDigit[i][commonMidDigit[i].length - 1];
            }
        }

        return commonMidDigit[greatestIndex];
    }

    /**
     * This method will perform task 3 of the assignment
     */
    public void task3(long[] list){
        long[] longestMagicSet = task2(list);

        if (longestMagicSet.length <= 2) {
            System.out.println(longestMagicSet[longestMagicSet.length - 1]);
        } else {
            for (int i = 0; i < longestMagicSet.length; i++) {
                System.out.print(longestMagicSet[i]);
                if (i != longestMagicSet.length - 1) {
                    System.out.print(" ");
                }
            }

            System.out.println();
        }
    }

    public void run(){
        Scanner input = new Scanner(System.in); // Declare scanner to read user input

        int choice = input.nextInt(); // Get the user's choice of task
        int length = input.nextInt(); // Get the number of elements from the King Advisor's list
        long[] palindromeList = new long[length]; // The array to store the elements of the King Advisor's list

        for (int i = 0; i < length; i++){
            long value = input.nextLong(); // Get the input of user
            palindromeList[i] = value;

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
                System.out.println(task2(palindromeList).length);
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
