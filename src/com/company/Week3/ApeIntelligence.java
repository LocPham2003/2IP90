package com.company.Week3;

import java.util.Scanner;

/**
 * ApeIntelligence.java
 * @author Loc Pham
 * @ID 1682512
 */

public class ApeIntelligence {
    public static int getUserInput(int i, int index) {
        if (i == -1) {
            return -1;
        }
        Parts parts = new Parts();

        if (index == 0) {
            switch (i) {
                case 1:
                    parts = new Part1();
                    break;
                case 2:
                    parts = new Part2();
                    break;
                case 3:
                    parts = new Part3();
                    break;
            }

            parts.getCommand(i, index);

        }

        return getUserInput(new Scanner(System.in).nextInt(), index++);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        getUserInput(input.nextInt(), 0);

    }
}
