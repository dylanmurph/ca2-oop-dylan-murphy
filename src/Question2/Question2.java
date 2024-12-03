package org.example.Question2;

import java.util.Scanner;

/**
 *  Name:
 *  Class Group:
 */
public class Question2  // Car Parking - Stack
{
    public static void runSimulation()
    {
        CarParking cp = new CarParking();
        Scanner kb = new Scanner(System.in);
        int input;

        System.out.println("Enter positive numbers to add a car, negative numbers to remove a car, and 0 to stop:");

        while (true) {
            input = kb.nextInt();

            if (input > 0) {
                cp.addCar(input);
            } else if (input < 0) {
                cp.removeCar(-input);
            } else {
                System.out.println("Simulation stopped.");
                break;
            }

            cp.getStack();
        }
    }

    public static void main(String[] args) {
        runSimulation();
    }
}
