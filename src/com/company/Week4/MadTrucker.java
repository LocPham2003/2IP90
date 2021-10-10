
package com.company.Week4;
import java.util.*;

/**
 * MadTrucker.java
 * @author Loc Pham
 * @ID 1682512
 */

public class MadTrucker {
    static int sumOfKilometers = 0; // Total kilometers that the trucker has travelled
    // The millage of the corresponding fuel can in order
    static ArrayList<Integer> fuelCansInOrder = new ArrayList<>();
    // A copy of the list of fuel cans
    static ArrayList<Integer> mirroredFuelCans;
    // The correct sequence to pour in fuel cans that helps the trucker to reach the final destination
    static ArrayList<Integer> sequence = new ArrayList<>();
    // The list of unstoppable points
    static ArrayList<Integer> nonStopPointsInput = new ArrayList<>();
    // Store the number of fuel cans
    static int numOfFuelCans;
    // Store when all fuelCans are used
    static boolean isFull = false;

    /**
     * This function will generate the sequence of fuel cans that the trucker should use
     */
    public static void createCansRefuelOrder(ArrayList<Integer> fuelCans, ArrayList<Integer> subset) {
        int s = sumOfKilometers; // Store the current number of kilometers that the trucker has travelled

        // Add all upcoming fuel cans in the sequence, do not add if the fuel can already exist in the sequence
        for (int x : subset) {
            if (!fuelCansInOrder.contains(x)) {
                fuelCansInOrder.add(x);
                sumOfKilometers = s;
            }
        }

        // If all fuel cans has been used, break the loop
        if (fuelCansInOrder.size() == numOfFuelCans) {
            isFull = true;
            return;
        }

        for (int i = 0; i < fuelCans.size(); i++) {
            int n = fuelCans.get(i);
            ArrayList<Integer> remaining = new ArrayList<>(fuelCans); // Get the fuel cans that has not been used
            remaining.remove(0); // Remove the fuel can that is going to be used
            subset.add(n); // Add the fuel can that is going to be used to a subset

            if (isFull) {
                break;
            }

            // Recursively call the function to get our sequence
            createCansRefuelOrder(remaining, subset);
            // Remove the fuel can that has been used
            subset.remove(subset.size() - 1);
        }
    }

    /**
     * This function will diagnose the sequence that we generate from the recursive function above
     * for any unexpected error. The recursion ends if and only if all values has been checked
     */
    public static void cleanTheSequence(ArrayList<Integer> sequence, ArrayList<Integer> nonStopPointsInput, int index) {
        if (index == sequence.size()) {
            return;
        }

        sumOfKilometers += sequence.get(index);

        // Check sequence before printing
        if (nonStopPointsInput.contains(sumOfKilometers)) {
            Collections.swap(sequence, index, index + 1);
            if (index != 0) {
                index--;
            }
        } else {
            index++;
        }

        cleanTheSequence(sequence, nonStopPointsInput, index);
    }

    /**
     * This function will start the recursive call
     */
    public static void generateSequence(ArrayList<Integer> fuelCans) {
        createCansRefuelOrder(fuelCans, new ArrayList<>());

        // After recursion:
        sumOfKilometers = 0;
        cleanTheSequence(fuelCansInOrder, nonStopPointsInput, 0);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Get the input of the user

        ArrayList<Integer> fuelCans = new ArrayList<>(); // The list that will store all fuel cans
        // The list that store the entire trip of the driver (fuel cans and unstoppable points)
        ArrayList<String> adventure = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));

        adventure.removeIf(i -> i.equals(""));
        numOfFuelCans = Integer.parseInt(adventure.get(0));

        for (int i = 1; i < numOfFuelCans + 1; i++) {
            fuelCans.add(Integer.parseInt(adventure.get(i)));
        }

        for (int k = numOfFuelCans + 1; k < adventure.size() - 1; k++) {
            nonStopPointsInput.add(Integer.parseInt(adventure.get(k)));
        }

        // Copy fuelCans to mirroredFuelCans
        mirroredFuelCans = new ArrayList<>(fuelCans);
        // Sort the unstoppable points in ascending order
        nonStopPointsInput.sort(Comparator.naturalOrder());
        // Sort the fuel cans in ascending order
        fuelCans.sort(Comparator.naturalOrder());
        // Start the recursive call
        generateSequence(fuelCans);


        // Print out the correct sequence
        for (int i = 0; i < fuelCansInOrder.size(); i++) {
            System.out.print(mirroredFuelCans.indexOf(fuelCansInOrder.get(i)));
            if (i != fuelCansInOrder.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
