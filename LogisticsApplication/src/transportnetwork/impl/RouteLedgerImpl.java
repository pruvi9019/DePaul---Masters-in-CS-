package transportnetwork.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import facilities.FacilityLink;
import transportnetwork.RouteLedgerFiling;

public class RouteLedgerImpl implements RouteLedgerFiling {

	private static final int HOURS_IN_DAY = 8;
	private static final int MILES_PER_HOUR = 50;
	private DecimalFormat numberFormatter = new DecimalFormat("#,###");

	public void logPath(ArrayList<ArrayList<FacilityLink>> shortestPath) {

		if (shortestPath == null) {
			throw new NullPointerException();
		}

		char[] letters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j' };

		Iterator<ArrayList<FacilityLink>> logIterator = shortestPath.iterator();

		int letter = 0;

		System.out.println("Shortest Path Tests:\n");
		while (logIterator.hasNext()) {
			ArrayList<FacilityLink> FacilityLinks = logIterator.next();

			FacilityLink firstNode = FacilityLinks.get(0);
			FacilityLink lastNode = FacilityLinks.get(FacilityLinks.size() - 1);

			int miles = 0;

			System.out.println(
					letters[letter] + ") \t" + firstNode.getFacilityLink() + " to " + lastNode.getFacilityLink() + ":");

			System.out.print("\t - ");

			for (FacilityLink FacilityLink : FacilityLinks) {
				miles += FacilityLink.getMiles();
				if (!FacilityLink.equals(lastNode)) {
					System.out.print(FacilityLink.getFacilityLink() + "->");
				} else {
					System.out.print(FacilityLink.getFacilityLink() + " = " + numberFormatter.format(miles) + " mi");
				}
			}

			System.out.println();
			System.out.print("\t - ");

			double daysDriven = (double) miles / (HOURS_IN_DAY * MILES_PER_HOUR);
			String daysDrivenOutput = String.format("%.2f", daysDriven);

			System.out.println(numberFormatter.format(miles) + " mi / (" + HOURS_IN_DAY + " hours per day * "
					+ MILES_PER_HOUR + " mph) = " + daysDrivenOutput + " days");
			letter++;
		}

	}

}
