package transportnetwork;

import java.util.ArrayList;

import facilities.FacilityLink;


public interface RouteLedgerFiling {
	public void logPath(ArrayList<ArrayList<FacilityLink>> shortestPath);

}