package org.example;

import java.util.*;

public class Utils {

    /**
     * Metod för att beräkna distansen mellan två geografiska koordinater
     */
    public static double getDistance( double lat1, double lon1, double lat2, double lon2) {

        // Konvertera grader till radians
        lat1 = lat1 * Math.PI / 180.0;
        lon1 = lon1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        lon2 = lon2 * Math.PI / 180.0;

        // Räkna ut distansen med haversinformeln
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1)
                * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);
        double c = 2 * Math.asin(Math.sqrt(a));

        // Jordens radie i km
        double r = 6371;
        // returnera resultatet i km
        return(c * r);

    }
    public static String listNodesAndLinks(ArrayList<Node> data) {
        StringBuilder result = new StringBuilder();
        // Väldigt basic utprintning, ville bara bekanta mig med hur det ska fungera
        for (int i = 0; i < data.size(); i++) {
            ArrayList<Node> neighborKey = data.get(i).getNeighbors();
            StringBuilder ret = new StringBuilder();
            for (int j = 0; j < neighborKey.size(); j++) {
                ret.append(String.format("[%s]",
                        neighborKey.get(j).getKey()
                ));
            }
            result.append(String.format("[%s] | [%s] | %s\n",
                    data.get(i).getKey(),
                    data.get(i).getName(),
                    ret
                    ));
        }
        return result.toString();
    }

    public static Node getNodeByKey(ArrayList<Node> data, String key) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getKey().equals(key)) {
                return data.get(i);
            }
        }
        return null;
    }

    public static ArrayList<Node> sortNodesByName(ArrayList<Node> nodes) {
        // Creates a deep copy of the original data set
        ArrayList<Node> sortedNodes = new ArrayList<>(nodes);

        for (int i = 0; i < nodes.size(); i++) {
            Node currentVal = nodes.get(i);
            int j = i - 1;
            while (j >= 0 && sortedNodes.get(j).getName().compareTo(currentVal.getName()) > 0) {
                sortedNodes.set(j + 1, sortedNodes.get(j));
                j--;
            }
            sortedNodes.set(j + 1, currentVal);
        }
        return sortedNodes;
    }

    public static ArrayList<Node> sortNodesByLat(ArrayList<Node> nodes) {
        // Creates a deep copy of the original data set
        ArrayList<Node> sortedNodes = new ArrayList<>(nodes);

        for (int i = 0; i < nodes.size(); i++) {
            Node currentVal = nodes.get(i);
            int j = i - 1;
            while (j >= 0 && sortedNodes.get(j).getLatitude() < currentVal.getLatitude()) {

                sortedNodes.set(j + 1, sortedNodes.get(j));
                j--;
            }
            sortedNodes.set(j + 1, currentVal);
        }
        return sortedNodes;
    }

}