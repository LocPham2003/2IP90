
/**
 * GuessingGame -- first part of assignment 2 in 2ip90
 *
 * @author Huynh Lien Huong
 * @id 1718371
 * @author TODO
 * @id TODO
 * @date 20/09/2021
 */

package com.company.Week2;

import java.util.*; // for Scanner and Random

class GuessingGame {
    void run() {
        Scanner input = new Scanner(System.in);
        String inputOfUser;
        int high = 1000;
        int low = 0;

        System.out.println("Think of a secret number not " +
                "smaller than - and not greater than 100. Type go when you have one");

        inputOfUser = input.nextLine();
        if (inputOfUser.equals("go")) {
            for (int i = 0; i < 11; i++) {
                System.out.println((high + low) / 2);
                inputOfUser = input.nextLine();
                if (inputOfUser.equals("higher")) {
                    low = (high + low) / 2;
                } else if (inputOfUser.equals("lower")) {
                    high = (high + low) / 2;
                } else if (inputOfUser.equals("Good guess! You won.")) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        (new GuessingGame()).run();
    }
}
