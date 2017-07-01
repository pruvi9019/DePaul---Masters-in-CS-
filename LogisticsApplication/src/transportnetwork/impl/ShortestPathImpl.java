package transportnetwork.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import exceptions.DataException;
import facilities.Facility;
import facilities.FacilityLink;
import transportnetwork.TransNetwork;

public class ShortestPathImpl implements TransNetwork {
	private ArrayList<FacilityLink> route;
	private HashMap<Integer, ArrayList<FacilityLink>> pairs;
	private HashSet<FacilityLink> visible;
	private int key;

	public ArrayList<FacilityLink> getShortestPath(FacilityLink start, FacilityLink end)
			throws NullPointerException, DataException, Exception {
		if (start == null || end == null) {
			throw new NullPointerException();
		}

		route = new ArrayList<FacilityLink>();
		pairs = new HashMap<Integer, ArrayList<FacilityLink>>();
		visible = new HashSet<FacilityLink>();
		key = 0;

		mapPairs(start);

		visible = new HashSet<FacilityLink>();

		ArrayList<FacilityLink> pathList = new ArrayList<FacilityLink>();
		pathList.add(start);

		findPath(start, end, pathList);

		return route;
	}

	public void mapPairs(FacilityLink start) throws NullPointerException, DataException, Exception {
		visible.add(start);
		Facility facility = start.getFacility();
		ArrayList<FacilityLink> allSubsidiaries = facility.getFacilityLinks();

		for (FacilityLink FacilityLink : allSubsidiaries) {
			ArrayList<FacilityLink> pairing = new ArrayList<FacilityLink>();
			pairing.add(start);
			pairing.add(FacilityLink);
			pairs.put(key, pairing);
			key++;

			if (!existsHashSet(visible, FacilityLink)) {
				mapPairs(FacilityLink);
			}
		}

	}

	public void findPath(FacilityLink start, FacilityLink end, ArrayList<FacilityLink> pathList)
			throws NullPointerException, DataException, Exception {
		if (start.getFacility().equals(end.getFacility())) {
			int pathListSum = 0;
			for (FacilityLink FacilityLink : pathList) {
				pathListSum += FacilityLink.getMiles();
			}

			int routeSum = 0;
			for (FacilityLink FacilityLink : route) {
				routeSum += FacilityLink.getMiles();
			}

			if (pathListSum < routeSum || routeSum == 0) {
				route = pathList;
				return;
			} else {
				return;
			}

		} else {
			HashSet<ArrayList<FacilityLink>> fromHere = new HashSet<ArrayList<FacilityLink>>();

			for (int i = 0; i < pairs.size(); i++) {
				ArrayList<FacilityLink> FacilityLinks = pairs.get(i);
				FacilityLink firstNode = FacilityLinks.get(0);

				if (firstNode.getFacility().equals(start.getFacility())) {

					fromHere.add(FacilityLinks);

				}
			}

			for (ArrayList<FacilityLink> FacilityLinks : fromHere) {
				FacilityLink secondNode = FacilityLinks.get(1);

				if (!existsArrayList(pathList, secondNode)) {
					ArrayList<FacilityLink> newPath = new ArrayList<FacilityLink>();

					for (FacilityLink FacilityLink : pathList) {
						newPath.add(FacilityLink);
					}

					newPath.add(secondNode);
					findPath(secondNode, end, newPath);

				}
			}
		}
	}

	public boolean existsHashSet(HashSet<FacilityLink> FacilityLinksToCheck, FacilityLink FacilityLinkCheckedAgainst)
			throws NullPointerException, DataException, Exception {

		boolean contains = false;


		for (FacilityLink FacilityLink : FacilityLinksToCheck) {
			if (FacilityLinkCheckedAgainst.getFacility().equals(FacilityLink.getFacility())) {
				contains = true;
			}
		}

		return contains;
	}

	public boolean existsArrayList(ArrayList<FacilityLink> FacilityLinksToCheck, FacilityLink FacilityLinkCheckedAgainst)
			throws NullPointerException, DataException, Exception {

		boolean contains = false;

		for (FacilityLink FacilityLink : FacilityLinksToCheck) {
			if (FacilityLinkCheckedAgainst.getFacility().equals(FacilityLink.getFacility())) {
				contains = true;
			}
		}

		return contains;
	}

}
