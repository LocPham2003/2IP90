package com.company.Week4;

import java.util.ArrayList;
import java.util.Collections;

public class AnalyzeInput {
    public void checkSequence(ArrayList<Integer> fuelCans, ArrayList<Integer> sequence, ArrayList<Integer> nonStopPoints) {
        Collections.sort(nonStopPoints);
        int sumOfKilometers = 0;
        for (int i = 0; i < fuelCans.size(); i++) {
            sumOfKilometers += fuelCans.get(sequence.get(i));
            try {
                if (sumOfKilometers == nonStopPoints.get(i)) {
                    System.out.println("You fucked up");
                } else {
                    System.out.println("Current Unstoppable: " + nonStopPoints.get(i) + " | Mileage of Fuel can used " + fuelCans.get(sequence.get(i)) +  " | Kilometers travelled " + sumOfKilometers);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("You made it");
            }
        }
    }
}
