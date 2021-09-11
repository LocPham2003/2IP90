/**
 * Circle.java
 * @author Loc Pham
 * @ID 1682512
 */

package com.company;

import java.util.Scanner;

class Circle {
    /**
    * This method check if the point input by the user lies inside the circle
     */
    public void run() {
        double circle1X = 0; // The x coordinate of circle 1
        double circle1Y = 0; // The y coordinate of circle 1
        double circle1Radius = 0; // The radius of circle 1
        double circle2X = 0; // The x coordinate of circle 2
        double circle2Y = 0; // The y coordinate of circle 2
        double circle2Radius = 0; // The radius of circle 2
        double foreignPointX = 0; // The x coordinate of the foreign point
        double foreignPointY = 0; // The y coordinate of the foreign point
        boolean isInCircle1; // Check if foreign point is in circle 1
        boolean isInCircle2; // Check if foreign point is in circle 2
        boolean isValidInput = true; // Input is true by default, unless user enter a negative number
        Scanner pointsInput; // The input of all points' coordinate

        pointsInput = new Scanner(System.in); // Get the input of the user

        try {
            circle1X = pointsInput.nextDouble();
            circle1Y = pointsInput.nextDouble();
            circle1Radius = pointsInput.nextDouble();
            circle2X = pointsInput.nextDouble();
            circle2Y = pointsInput.nextDouble();
            circle2Radius = pointsInput.nextDouble();
            foreignPointX = pointsInput.nextDouble();
            foreignPointY = pointsInput.nextDouble();
        } catch (Exception e){
            isValidInput = false;
        }

        if (circle1Radius < 0 || circle2Radius < 0){
            isValidInput = false;
        }

        isInCircle1 = Math.sqrt(Math.pow((foreignPointX - circle1X), 2) + Math.pow((foreignPointY - circle1Y), 2)) <= circle1Radius;
        isInCircle2 = Math.sqrt(Math.pow((foreignPointX - circle2X), 2) + Math.pow((foreignPointY - circle2Y), 2)) <= circle2Radius;

        if (isValidInput){
            if (isInCircle1 && !isInCircle2){
                System.out.println("The point lies in the first circle");
            } else if (!isInCircle1 && isInCircle2){
                System.out.println("The point lies in the second circle");
            } else if (!isInCircle1 && !isInCircle2){
                System.out.println("The point does not lie in either circle");
            } else if (isInCircle1 && isInCircle2){
                System.out.println("The point lies in both circles");
            }
        } else {
            System.out.println("input error");
        }
    }

    public static void main(String[] args) {
        (new Circle()).run();
    }
}
