package org.example.Question2;

import java.util.Stack;

public class CarParking {
    private Stack<Integer> drivewayStack = new Stack<>(); // Stack for the driveway
    private Stack<Integer> streetStack = new Stack<>();   // Stack for the street

    public void addCar(int carNumber) {
        drivewayStack.push(carNumber);
        System.out.println("Added car " + carNumber + " to the driveway.");
    }

    public void removeCar(int carNumber) {
        if (drivewayStack.isEmpty()) {
            System.out.println("Driveway is empty. Cannot remove car " + carNumber);
            return;
        }

        boolean found = false;

        // Move cars from driveway to street until the target car is found
        while (!drivewayStack.isEmpty()) {
            int currentCar = drivewayStack.pop();
            if (currentCar == carNumber) {
                found = true; // Car found
                System.out.println("Retrieved car " + carNumber + " from the driveway.");
                break;
            } else {
                streetStack.push(currentCar); // Temporarily move to the street stack
            }
        }

        // Move cars back to the driveway from the street
        while (!streetStack.isEmpty()) {
            drivewayStack.push(streetStack.pop());
        }

        if (!found) {
            System.out.println("Car " + carNumber + " is not in the driveway.");
        }
    }

    public void getStack() {
        System.out.println("Current state of the driveway: " + drivewayStack);
        System.out.println("Current state of the street: " + streetStack);
    }
    
}
