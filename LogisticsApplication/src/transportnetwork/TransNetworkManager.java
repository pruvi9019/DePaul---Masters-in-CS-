package transportnetwork;

import java.util.ArrayList;

import exceptions.DataException;
import facilities.FacilityLink;
import transportnetwork.factory.RouteLedger;
import transportnetwork.factory.TransNetworkBranch;
import transportnetwork.impl.RouteLedgerImpl;
import transportnetwork.impl.ShortestPathImpl;

public class TransNetworkManager {

	
	private final static int HOURS_PER_DAY = 8;
	private final static int MILES_PER_HOUR = 50;
	private static final int DAILY_TRAVEL_COST = 500;
	private volatile static TransNetworkManager networkManager;
	private ShortestPathImpl network;


	public static TransNetworkManager getInstance() throws Exception {
		synchronized (TransNetworkManager.class) {
			if (networkManager == null) {
				networkManager = new TransNetworkManager();
			}
		}

		return networkManager;
	}


	private TransNetworkManager() {
		network = TransNetworkBranch.createNewNetwork();
	}


	public ArrayList<FacilityLink> getShortestPath(FacilityLink start, FacilityLink end)
			throws NullPointerException, DataException, Exception {
		if (start == null || end == null) {
			throw new NullPointerException();
		}
		ArrayList<FacilityLink> shortestPath = network.getShortestPath(start, end);
		return shortestPath;

	}


	public int lengthOfPath(ArrayList<FacilityLink> path)
			throws Exception
	{
		if(path==null)
		{
			throw new NullPointerException();
		}

		int miles = 0;

		for (FacilityLink facilitylink : path)
		{
			miles += facilitylink.getMiles();
		}

		return miles;
	}

	public void logPath(ArrayList<ArrayList<FacilityLink>> shortestPath) throws NullPointerException {
		if (shortestPath == null) {
			throw new NullPointerException();
		}

		RouteLedgerImpl logger = RouteLedger.createNewPathLog();
		logger.logPath(shortestPath);
	}
	
	public int costToTravel(int travelTime)
	{

		return travelTime * DAILY_TRAVEL_COST;

	}

	public int travelTime(int miles)
	{
		return (miles/(HOURS_PER_DAY*MILES_PER_HOUR));
	}


}
