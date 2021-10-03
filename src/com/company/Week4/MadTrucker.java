package com.company.Week4;
import java.util.*;

public class MadTrucker {
    static int sumOfKilometers = 0;
    static ArrayList<Integer> fuelCansInOrder = new ArrayList<>();
    static ArrayList<Integer> mirroredFuelCans;
    static ArrayList<Integer> nonStopPointsInput = new ArrayList<>();
    static int numOfFuelCans;


    public static void createCansRefuelOrder(ArrayList<Integer> fuelCans, int target, ArrayList<Integer> subset) {
        int s = sumOfKilometers;
        for (int x : subset) {
            if (!fuelCansInOrder.contains(x)) {;
                fuelCansInOrder.add(x);
                sumOfKilometers = s;
            }
        }

        for (int i = 0; i < fuelCans.size(); i++) {
            int n = fuelCans.get(i);
            ArrayList<Integer> remaining = new ArrayList<>(fuelCans);
            remaining.remove(0);
            subset.add(n);

            if (fuelCansInOrder.size() == numOfFuelCans) {
                break;
            }

            createCansRefuelOrder(remaining, target, subset);
            subset.remove(subset.size() - 1);
        }
    }

    public static void generateSequence(ArrayList<Integer> fuelCans, int target) {
        createCansRefuelOrder(fuelCans, target, new ArrayList<>());
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> fuelCans = new ArrayList<>();
        ArrayList<String> adventure = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));

        adventure.removeIf(i -> i.equals(""));

        numOfFuelCans = Integer.parseInt(adventure.get(0));
        for (int i = 1; i < numOfFuelCans + 1; i++) {
            fuelCans.add(Integer.parseInt(adventure.get(i)));
        }

        for (int k = numOfFuelCans + 1; k < adventure.size() - 1; k++) {
            nonStopPointsInput.add(Integer.parseInt(adventure.get(k)));
        }

        mirroredFuelCans = new ArrayList<>(fuelCans);
        fuelCans.sort(Comparator.naturalOrder());
        nonStopPointsInput.sort(Comparator.naturalOrder());
        int greatestDistance = nonStopPointsInput.get(nonStopPointsInput.size() - 1);
        generateSequence(fuelCans, greatestDistance);

        for (int i = 0; i < fuelCansInOrder.size(); i++) {
            System.out.print(mirroredFuelCans.indexOf(fuelCansInOrder.get(i)));
            if (i != fuelCansInOrder.size() - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();



    }
}
