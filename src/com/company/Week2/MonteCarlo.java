package com.company.Week2;

import java.util.Random;

public class MonteCarlo {

    public void run(){
        double innerPoint = 0;
        double pointsThrown = 100000;
        double piApproximation = 0;
        int i = 0;
        while (piApproximation != Math.PI) {
            Random rand = new Random();

            double x = rand.nextDouble();
            double y = rand.nextDouble();

            if (Math.sqrt(Math.pow((x - 0.5), 2) + Math.pow((y - 0.5), 2)) <= 0.5){
                innerPoint++;
            }

            piApproximation = (innerPoint * 4.0) / i;
            System.out.println(piApproximation);
            i++;
       }

        System.out.println("Pi equals: " + piApproximation);
    }

    public static void main(String[] args) {
        (new MonteCarlo()).run();
    }
}
