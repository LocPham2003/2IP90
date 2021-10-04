package com.company.Week4;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Testing {
    public void testSequence(ArrayList<Integer> nonStopPoints, ArrayList<Integer> fuelCans, ArrayList<Integer> sequence) {
        int sumOfKilometers = 0;
        for (int i = 0; i < fuelCans.size(); i++) {
            sumOfKilometers += fuelCans.get(sequence.get(i));
            System.out.println(sumOfKilometers);
            if (nonStopPoints.contains(sumOfKilometers) && sumOfKilometers != 0) {
                System.out.println("You fucked up");
                break;
            }
            System.out.print("Kilometers travelled: " + sumOfKilometers);
            System.out.println();

        }
    }
}
