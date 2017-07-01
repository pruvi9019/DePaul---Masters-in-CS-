package orderitemprocessing;

import java.util.ArrayList;

import exceptions.DataException;
import facilities.Facility;
import facilities.FacilityLink;
import facilities.FacilityManager;
import itemcatalog.ItemManager;
import orders.Order;
import transportnetwork.TransNetworkManager;

public class OrderProcessing {

	private volatile static OrderProcessing orderProcessingManager;

	public static OrderProcessing getInstance() throws Exception {
		synchronized (OrderProcessing.class) {
			if (orderProcessingManager == null) {
				orderProcessingManager = new OrderProcessing();
			}
		}

		return orderProcessingManager;
	}

	private OrderProcessing() {

	}

	public void loadFacilities() throws Exception {
		FacilityManager.getInstance();
	}

	public void loadItems() throws Exception {
		ItemManager.getInstance();
	}

	public void logItems() throws Exception {
		ItemManager.getInstance().logItems();
	}

	public void logFacilities() throws NullPointerException, DataException, Exception {
		FacilityManager.getInstance().logItems();
	}

	public Facility getFacility(String name) throws NullPointerException, DataException, Exception {
		return FacilityManager.getInstance().getFacility(name);
	}

	public FacilityLink toFacilityLink(Facility facility) throws Exception {
		return FacilityManager.getInstance().toFacilityLink(facility);
	}

	public FacilityLink createStartEnd(String name) throws NullPointerException, DataException, Exception {
		return toFacilityLink(getFacility(name));
	}

	public ArrayList<FacilityLink> getShortestPath(FacilityLink start, FacilityLink end)
			throws NullPointerException, DataException, Exception {
		ArrayList<FacilityLink> shortestPath = TransNetworkManager.getInstance().getShortestPath(start, end);

		return shortestPath;
	}

	public void logPath(ArrayList<ArrayList<FacilityLink>> shortestPaths) throws NullPointerException, Exception {
		TransNetworkManager.getInstance().logPath(shortestPaths);
	}

	public Integer getQuantityForItemInOrder(Order order, String item) {
		// TODO Auto-generated method stub
		return null;
	}

}
