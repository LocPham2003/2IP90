package com.company.Week2;

public class Planes {
    public static int planeNumbers(int plane) {
        if (plane == 1) {
            return 1;
        } else if (plane == 0) {
            return 0;
        }

        return plane + 2 * planeNumbers(plane - 1);
    }

    public static void main(String[] args) {
        System.out.println(planeNumbers(3));
    }
}
