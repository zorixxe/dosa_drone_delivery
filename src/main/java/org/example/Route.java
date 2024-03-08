package org.example;

import java.util.ArrayList;
import java.util.Collections;


public class Route {

    Node start; // The starting node of the route
    Node goal; // The goal or end node of the route

    // Constructor initializes the route with start and goal nodes.
    public Route(Node start, Node goal) {
        this.start = start;
        this.goal = goal;
    }

    // Implements the A* pathfinding algorithm to find the best route from start to
    // goal.
    public ArrayList<Node> aStar() {
        ArrayList<Node> result = new ArrayList<>(); // Holds the final path from start to goal

        ArrayList<Node> openNodes = new ArrayList<>(); // Nodes to be evaluated
        openNodes.add(start); // Start by evaluating the start node

        // Initialize start node's A* scores: g (cost from start to node) and f (total
        // estimated cost)
        start.setG(0);
        start.setF(start.getG() + start.calculateH(goal));

        // Loop until there are no nodes left to evaluate
        while (!openNodes.isEmpty()) {
            // Select the node with the lowest f score to process next
            Node current = openNodes.get(0);
            for (int i = 1; i < openNodes.size(); i++) {
                if (openNodes.get(i).getF() < current.getF()) {
                    current = openNodes.get(i);
                }
            }

            // Remove current node from the open list
            openNodes.remove(current);

            // Check if current node is the goal; if so, construct the path and return it
            if (current.getName().equals(goal.getName())) {
                Node temp = current;
                result.add(temp);
                // Trace back from goal to start via parent nodes
                while (temp.getParent() != null) {
                    result.add(temp.getParent());
                    temp = temp.getParent();
                }
                Collections.reverse(result); // Reverse the list to get the correct order from start to goal
                return result;
            }

            // Process each neighbor of the current node for possible inclusion in the path
            for (Node neighbor : current.getNeighbors()) {
                double tempG = current.getG() + current.calculateH(neighbor); // Temp g score for neighbor
                // Update neighbor's parent and scores if this path to neighbor is better
                if (tempG < neighbor.getG()) {
                    neighbor.setParent(current);
                    neighbor.setG(tempG);
                    neighbor.setF(neighbor.getG() + neighbor.calculateH(goal));
                    if (!openNodes.contains(neighbor)) {
                        openNodes.add(neighbor);
                    }
                }
            }
        }

        return result; // Return the constructed path or an empty list if no path found
    }
}
