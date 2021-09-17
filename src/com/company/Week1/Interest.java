package com.company.Week1;

/**
 * @author Loc Pham
 * @date 07-09-2021
 * Interest calculation
 */

import java.util.Scanner;

public class Interest {
    public void calcInterest(){
        Scanner sc = new Scanner(System.in);

        double balance;
        double interest_rate;
        double tax_rate;

        System.out.println("Put in the initial amount of as euros");
        balance = sc.nextDouble();
        System.out.println("Give interest rate as percentage");
        interest_rate = sc.nextDouble();

        System.out.println("After a year you get a balance of: " +
                (balance + ((balance * interest_rate) / 100)));

        System.out.println("Give tax rate as percentage");
        tax_rate = sc.nextDouble();

        System.out.println("The amount of tax to be paid is: " + balance * (tax_rate / 100));

    }

    public static void main(String[] args) {
        (new Interest()).calcInterest();
    }

}
