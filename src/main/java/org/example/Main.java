package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * Projekt 1 - ruttsökning med A*
 *
 * Datastrukturer och algoritmer
 *
 * Programmeringsteam:
 *
 */
public class Main {
	public static void main(String[] args) {

		while (true) {

			ArrayList<Node> mainData = GraphData.createGraph();

			String sort = UI.promptCommand("Do you want to sort the data A-Ö(a) or Nort-South(n)", "a", "n");
			if (sort.equals("a")) {

				UI.printData(Utils.sortNodesByName(mainData));
				
			} else if (sort.equals("n")) {

				UI.printData(Utils.sortNodesByLat(mainData));
				
			}

			String startPoint = UI.promptPlace("Where are you?");
			String endPoint = UI.promptPlace("Where do you want to go?");

			Route route = new Route(Utils.getNodeByKey(mainData, startPoint), Utils.getNodeByKey(mainData, endPoint));
			ArrayList<Node> path = route.aStar();
			UI.printRoute(path, route);

			if (!UI.promptCommand("Do you want to search for another route? (yes/no)", "yes", "no").equals("yes")) {
				System.out.println("Have a lovely day!");
				UI.closeScanner();
				break;

			}
		}

	}
}
