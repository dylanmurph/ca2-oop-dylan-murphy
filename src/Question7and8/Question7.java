package org.example.Question7and8;

import java.util.Scanner;

/**
 * Name: Dylan Murphy
 * Class Group: SD2b
 */
public class Question7and8  // Shares Tax Calculations (Queue)
{
    private static final Scanner kb = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("""
                    Choose which question to run:
                    1. Question 7 - Stock Shares Tax Calculation
                    2. Question 8 - Multi-Company Stock Shares Tax Calculation
                    3. Quit
                    
                    Enter option 1, 2 or 3:
                    """);

            String input = kb.nextLine().trim();

            switch (input) {
                case "1":
                    question7();
                    break;
                case "2":
                    question8();
                    break;
                case "3":
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3");
            }
        }
    }

    public static void question7() {
        ShareQueue sq = new ShareQueue();
        String input;
        //buy and sell regex to validate user enters commands correctly and int variable is within range.
        String buyRegex = "^buy\\s+" +
                "([1-9]\\d{0,8})\\s+" +
                "\\d+(\\.\\d+)?$";

        String sellRegex = "^sell\\s+" +
                "([1-9]\\d{0,8})\\s+" +
                "\\d+(\\.\\d+)?$";

        String[] inputs;
        int qty;
        double price;

        while (true) { //loop will repeat until return statement when the user inputs quit
            System.out.println("""
                    \u001B[94m
                    Stock Shares Calculator
                    -----------------------
                    Enter 'buy [quantity] [price]' to buy stocks
                    Enter 'sell [quantity] [price]' to sell stocks
                    Enter 'show-shares' to display current shares owned
                    Enter 'quit' to end program
                    -----------------------
                    \u001B[0m
                    Enter Command:
                    """);

            input = kb.nextLine().toLowerCase().trim();

            if (input.equals("quit")) {
                System.out.println("\u001b[31mExiting program...\u001b[0m");
                return;
            } else if (input.equals("show-shares")) {
                sq.displayShares();
                continue; //repeat loop after displaying shares
            } else if (!input.matches(buyRegex) && !input.matches(sellRegex)) { //validating user input
                System.out.println("""
                        \u001b[31m
                        -----------------------
                        Error: Invalid command, please try again
                        For buy and sell, use format '[command] [int value in range 1-999999999] [positive number]'
                        -----------------------
                        \u001b[0m
                        """);
                continue;//repeat loop if invalid input
            }

            try {
                inputs = input.split("\\s+"); //splitting input, using regex, into an array
                qty = Integer.parseInt(inputs[1]);
                price = Double.parseDouble(inputs[2]);

                if (input.matches(buyRegex)) {
                    sq.buyShares(qty, price);
                } else if (input.matches(sellRegex)) {
                    sq.sellShares(qty, price);
                } else {
                    System.out.println("\u001b[31mInvalid command, please try again\u001b[0m");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage() + "\nPlease try again");
                //if exception is thrown, error message display. loop will restart naturally
            }
        }
    }

    public static void question8() {
        ShareQueueMap sqm = new ShareQueueMap();
        String input;
        //buy and sell regex to validate user enters commands correctly and int variable is within range.
        String buyRegex = "^buy\\s+" +
                "\\w+\\s+" +
                "([1-9]\\d{0,8})\\s+" +
                "\\d+(\\.\\d+)?$";

        String sellRegex = "^sell\\s+" +
                "\\w+\\s+" +
                "([1-9]\\d{0,8})\\s+" +
                "\\d+(\\.\\d+)?$";
        String displayRegex = "^show-shares\\s+" +
                "\\w+$";

        String[] inputs;
        int qty;
        double price;

        while (true) {  //loop will repeat until return statement when the user inputs quit
            System.out.println("""
                    \u001B[94m
                    Stock Shares Calculator
                    -----------------------
                    Enter 'buy [symbol] [quantity] [price]' to buy stocks
                    Enter 'sell [symbol] [quantity] [price]' to sell stocks
                    Enter 'show-shares' to display all current shares owned
                    Enter 'show-shares [symbol]' to display shares owned in [symbol]
                    Enter 'quit' to end program
                    -----------------------
                    \u001B[0m
                    Enter Command:
                    """);

            input = kb.nextLine().toLowerCase().trim();

            if (input.equals("quit")) {
                System.out.println("\u001b[31mExiting program...\u001b[0m");
                return;
            } else if (input.equals("show-shares")) {
                sqm.displayCompanyShares();
                continue; //repeat loop after displaying shares
            } else if (input.matches(displayRegex)){
                int spaceIndex = input.indexOf(' ');
                sqm.displayCompanyShares(input.substring(spaceIndex+1));
            }else if (!input.matches(buyRegex) && !input.matches(sellRegex)) {//validating user input
                System.out.println("""
                        \u001b[31m
                        -----------------------
                        Error: Invalid command, please try again
                        For buy and sell, use format '[command] [symbol] [int value in range 1-999999999] [positive number]'
                        -----------------------
                        \u001b[0m
                        """);
                continue;//repeat loop if invalid input
            }

            try {
                inputs = input.split("\\s+"); //splitting input, using regex, into an array
                String symbol = inputs[1];
                qty = Integer.parseInt(inputs[2]);
                price = Double.parseDouble(inputs[3]);

                if (input.matches(buyRegex)) {
                    sqm.buyCompanyShares(symbol, qty, price); //buy shares for the input symbol.
                } else if (input.matches(sellRegex)) {
                    sqm.sellCompanyShares(symbol, qty, price); //sell shares for the input symbol.
                }
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\nPlease try again.");
                //if exception is thrown, error message display. loop will restart naturally
            }
        }

    }
}
