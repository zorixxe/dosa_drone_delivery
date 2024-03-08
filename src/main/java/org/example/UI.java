package org.example;

import java.util.Scanner;
import java.util.ArrayList;

public class UI {
    // Prints the graph data in a formatted table.
    public static void printData(ArrayList<Node> graphData) {
        // Header for the table
        System.out.println(" NOD   |  NAMN                       |  GRANNAR");
        System.out.println("--------------------------------------------------");
        // Utilizes a utility method to list nodes and their links for printing
        System.out.println(Utils.listNodesAndLinks(graphData));
    }

    // Prints the optimal route found by the pathfinding algorithm.
    public static void printRoute(ArrayList<Node> path, Route route) {
        if (path.isEmpty()) {
            // Case when no path is found between start and goal
            System.out.println("No path found.");
        } else {
            // When a path is found, print each step and the total distance
            System.out.printf("The direct route from %s%nto%n%s%nWould be: %.3f km%n",
                    route.start.getName(),
                    route.goal.getName(),
                    route.start.calculateH(route.goal));
            System.out.println("The best route is: ");
            double totalDistance = 0.0; // Keeps track of the total distance traveled
            Node previousNode = null; // Keeps track of the previous node to calculate distances

            for (Node node : path) {
                if (previousNode == null) {
                    // First node (start node), just print its name
                    System.out.printf("%s",
                            node.getName());
                } else {
                    // For subsequent nodes, calculate and print the distance from the previous node
                    double distance = node.distanceToPreviousNode(previousNode);
                    totalDistance += distance; // Add to the total distance
                    // Print the direction to the next node and the distance
                    System.out.printf("%n          |%n%s (%.3f km)",
                            node.getName(),
                            distance);
                }
                previousNode = node; // Update previousNode for the next iteration
            }

            // Calculate and print the rounded total distance of the entire route
            double roundedTotalDistance = Math.round(totalDistance * 1000.0) / 1000.0;
            System.out.printf("%nTotal distance: %.3f km%n",
                    roundedTotalDistance);
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static String promptPlace(String message) {
        System.out.printf("%s (enter a valid place): ", message);
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase(); // Normalize input for comparison
            // List of predefined valid places
            String[] validPlaces = { "sorn", "bole", "arca", "tolo", "diak", "vall", "mejl", "berg", "oodi", "hels",
                    "hank", "rich", "bush", "gumt", "lill" };
            for (String validPlace : validPlaces) {
                if (input.equals(validPlace)) {
                    return input; // Return valid place name
                }
            }
            System.out.println("Please input a valid place."); // Prompt again if input is invalid
        }
    }

    // Asks user a question and expects one of the valid commands before proceeding.
    public static String promptCommand(String prompt, String... validCommands) {
        System.out.println(prompt); // Display prompt
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase(); // Normalize input for comparison
            for (String validCommand : validCommands) {
                if (input.equals(validCommand)) {
                    return input; // Return valid command
                }
            }
            System.out.println("Invalid command. Please try again."); // Prompt again if input is invalid
        }
    }

    // Closes the scanner to prevent resource leaks.
    public static void closeScanner() {
        scanner.close();
    }

}
