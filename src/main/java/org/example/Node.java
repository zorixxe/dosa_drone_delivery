package org.example;

import java.util.ArrayList;

public class Node {
    String name;
    String key;
    double latitude;
    double longitude;
    double f;
    double g = Double.POSITIVE_INFINITY;
    ArrayList<Node> neighbors = new ArrayList<>();
    public Node parent;

    public Node(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    // Getter & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    // Calculates the heuristic (H) for the A* algorithm by estimating the distance
    // to the end node.
    public double calculateH(Node endNode) {
        // Returns the straight-line distance from this node to the end node as the
        // heuristic value.
        return Utils.getDistance(endNode.getLatitude(), endNode.getLongitude(), this.getLatitude(),
                this.getLongitude());
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public void setNeighbors(ArrayList<Node> neighbors) {
        this.neighbors = neighbors;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    // Calculates the distance from this node to a specified previous node.
    public double distanceToPreviousNode(Node previousNode) {
        // Return 0.0 if there's no previous node (i.e., this node is the start).
        if (previousNode == null) {
            return 0.0;
        } else {
            // Calculate and return the distance between this node and the previous node.
            return Utils.getDistance(previousNode.getLatitude(), previousNode.getLongitude(), this.getLatitude(),
                    this.getLongitude());
        }
    }

}