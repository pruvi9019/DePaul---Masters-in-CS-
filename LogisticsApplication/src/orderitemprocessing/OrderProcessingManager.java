package orderitemprocessing;

import exceptions.DataException;
import facilities.Facility;
import facilities.FacilityManager;
import facilities.FacilityLink;
import itemcatalog.ItemManager;
import transportnetwork.TransNetworkManager;
import orders.Order;
import orders.OrderManager;

import java.util.ArrayList;
import java.util.Collections;


public class OrderProcessingManager {

	private ArrayList<OrdersProcessedSummary> orderInformation;


	private static final int TRAVEL_COST_PER_DAY = 300;

	private volatile static OrderProcessingManager orderProcessingManager;

	public static OrderProcessingManager getInstance() throws Exception
	{
		synchronized(OrderProcessingManager.class)
		{
			if (orderProcessingManager == null)
			{
				orderProcessingManager = new OrderProcessingManager();
			}
		}
		
		return orderProcessingManager;
	}

	private OrderProcessingManager()
	{
		orderInformation=new ArrayList<OrdersProcessedSummary>();

	}



	public void loadFacilities() throws Exception
	{
		FacilityManager.getInstance();
	}


	public void loadItems() 
			throws Exception
	{
		ItemManager.getInstance();
	}

	public void loadOrders()
			throws Exception
	{
		OrderManager.getInstance();
	}

	public ArrayList<Order> retrieveOrders()
			throws Exception
	{
		return OrderManager.getInstance().sendOrdersToProcess();
	}

	public ArrayList<String> getItemsOnOrder(Order order)
			throws Exception
	{
		if(order==null)
		{
			throw new NullPointerException();
		}

		return OrderManager.getInstance().getItemsOnOrder(order);
	}

	public String getOrderId(Order order)
			throws Exception
	{
		if(order == null)
		{
			throw new NullPointerException();
		}


		return OrderManager.getInstance().getOrderId(order);
	}

	public String getDestination(Order order)
			throws Exception
	{
		if(order == null)
		{
			throw new NullPointerException();
		}

		return OrderManager.getInstance().getDestination(order);
	}

	public int getOrderDay(Order order)
			throws Exception
	{
		if(order==null)
		{
			throw new NullPointerException();
		}

		return OrderManager.getInstance().getOrderDay(order);
	}

	public Integer getQuantityForItemInOrder(Order order, String itemName)
			throws Exception
	{
		if(order==null||itemName==null)
		{
			throw new NullPointerException();
		}

		return OrderManager.getInstance().getQuantityOfItemOnOrder(order, itemName);
	}


	public void logItems() 
			throws Exception
	{
		ItemManager.getInstance().logItems();
	}


	public void logFacilities() 
			throws NullPointerException, DataException, Exception
	{
		FacilityManager.getInstance().logItems();
	}



	public Facility getFacility(String name)
			throws NullPointerException, DataException, Exception
	{
		return FacilityManager.getInstance().getFacility(name);
	}

	public String getFacilityName(OrdersProcessable orderProcessed)
			throws NullPointerException, DataException, Exception
	{
		return orderProcessed.getSource();
	}


	public FacilityLink toFacilityLink(Facility facility)
			throws Exception
	{
		return FacilityManager.getInstance().toFacilityLink(facility);
	}



	public FacilityLink createStartEnd(String name) 
			throws NullPointerException, DataException, Exception
	{
		return toFacilityLink(getFacility(name));
	}



	public ArrayList<FacilityLink> getShortestPath(FacilityLink start, FacilityLink end) 
			throws NullPointerException, DataException, Exception
	{
		ArrayList<FacilityLink> shortestPath = TransNetworkManager.getInstance().getShortestPath(start, end);
		
		return shortestPath;
	}

	public ArrayList<FacilityLink> getShortestPath(String facility, String destination)
			throws NullPointerException, DataException, Exception
	{
		FacilityLink start = toFacilityLink(getFacility(facility));
		FacilityLink end = toFacilityLink(getFacility(destination));

		ArrayList<FacilityLink> shortestPath = TransNetworkManager.getInstance().getShortestPath(start, end);

		return shortestPath;
	}



	public ArrayList<FacilityLink> getDestinationPath(String startFacilityName, String destination)
			throws NullPointerException, DataException, Exception
	{
		if(startFacilityName==null||destination==null)
		{
			throw new NullPointerException();
		}

		FacilityLink end = createStartEnd(destination);
		FacilityLink start = createStartEnd(startFacilityName);

		ArrayList<FacilityLink> shortestPath = getShortestPath(start,end);

		return shortestPath;
	}


	public void logPath(ArrayList<ArrayList<FacilityLink>> shortestPaths) 
			throws NullPointerException, Exception
	{
		TransNetworkManager.getInstance().logPath(shortestPaths);
	}


	public ArrayList<String> facilitiesWithItem(String itemName, String destination)
			throws Exception
	{
		if(itemName==null)
		{
			throw new NullPointerException();
		}

		ArrayList<String> facilitiesWithItem = FacilityManager.getInstance().facilitiesWithItem(itemName,destination);
		return facilitiesWithItem;
	}

	//Helper method for expectedDeliveryDay, gets miles so arrivalDay can be called
	public int getMilesFromPath (ArrayList<FacilityLink> pathsFromSources)
			throws Exception
	{
		if(pathsFromSources==null)
		{
			throw new NullPointerException();
		}

		return TransNetworkManager.getInstance().lengthOfPath(pathsFromSources);

	}


	public Integer itemsInStock(String facilityName, String itemName)
			throws Exception
	{
		if(facilityName==null||itemName==null)
		{
			throw new NullPointerException();
		}

		Facility facility = getFacility(facilityName);

		return facility.checkStock(itemName);
	}

	public Integer daysToProcess(String facilityName, Integer quantity, Integer startDate)
			throws Exception
	{
		if(facilityName==null||quantity==null||startDate==null)
		{
			throw new NullPointerException();
		}

		return 	FacilityManager.getInstance().daysToProcess(facilityName,quantity,startDate);
	}

	public int travelTime(Integer miles)
			throws Exception
	{
		if(miles == null)
		{
			throw new NullPointerException();
		}

		int travelTime = TransNetworkManager.getInstance().travelTime(miles);

		return travelTime;
	}

	public int expectedShipDate(String facilityName, Integer quantity, Integer startDay)
			throws Exception
	{
		if(facilityName==null || quantity==null)
		{
			throw new NullPointerException();
		}

		int expectedShipDate = FacilityManager.getInstance().expectedShipDate(quantity, facilityName, startDay);

		return expectedShipDate;
	}


	public int expectedDeliveryDate(String facility, int quantity,
													String destination, Integer startDay)
			throws Exception
	{

		if(facility==null || destination==null || startDay==null)
		{
			throw new NullPointerException();
		}


		int miles = getMilesFromPath(getDestinationPath(facility,destination));

		int expectedShipDate = expectedShipDate(facility, quantity, startDay);
		int expectedTravelTime = travelTime(miles);

		int expectedDelivery = expectedShipDate+expectedTravelTime;

		return expectedDelivery;
	}

	public OrdersProcessable addProcessedOrder(String newOrderID, String newSource, String newItemName,
								  int newNumberOfItems, int newProcessingEndDate, int newDaysTookToProcess,
								  int newTravelTime, int newArrivalDate)
			throws Exception
	{
		OrdersProcessable newOrder = OrdersProcessedFactory.createNewOrders(newOrderID, newSource, newItemName, newNumberOfItems,
				newDaysTookToProcess, newProcessingEndDate, newTravelTime, newArrivalDate);

		return newOrder;

	}


	public ArrayList<OrdersProcessable> sortSourcesByDay (ArrayList<OrdersProcessable> ordersProcessed)
			throws Exception
	{

		Collections.sort(ordersProcessed);

		return new ArrayList<OrdersProcessable>(ordersProcessed);
	}


	public void updateToItemsTaken(OrdersProcessable ordersProcessed, String itemName, Integer quantityNeeded)
			throws Exception
	{
		if(ordersProcessed == null||itemName==null||quantityNeeded==null)
		{
			throw new NullPointerException();
		}


			ordersProcessed.updateItems(quantityNeeded);

	}

	public void updateDaysTaken(String facilityName, Integer quantity, Integer startDate, OrdersProcessable ordersProcessed)
			throws Exception
	{
		if(facilityName==null||quantity==null||startDate==null || ordersProcessed==null)
		{
			throw new NullPointerException();
		}

		ordersProcessed.setDaysTaken(daysToProcess(facilityName, quantity, startDate));
	}

	public void updateProcessingEndDay(String Destination, Integer quantity, Integer startDate, OrdersProcessable ordersProcessed)
		throws Exception
	{

		if(quantity==null||startDate==null||ordersProcessed==null)
		{
			throw new NullPointerException();
		}

		ordersProcessed.setEndDay(expectedShipDate(ordersProcessed.getSource(),ordersProcessed.getNumberOfItems(), startDate));
	}

	public void updateArrivalDay(String destination, Integer startDate, OrdersProcessable ordersProcessed)
			throws Exception
	{
		if(startDate==null||ordersProcessed==null)
		{
			throw new NullPointerException();
		}

		ordersProcessed.setArrivalDay(expectedDeliveryDate(ordersProcessed.getSource(),ordersProcessed.getNumberOfItems(),destination,startDate));
	}


	public Integer checkAmountGiven(OrdersProcessable orderProcessed, Integer amountNeeded)
			throws Exception
	{
		return FacilityManager.getInstance().checkAmountGiven(orderProcessed.getSource(),orderProcessed.getItemName(),amountNeeded);
	}

	public void updateInventory (OrdersProcessable ordersProcessed, String itemName)
			throws Exception
	{
		if(ordersProcessed == null||itemName==null)
		{
			throw new NullPointerException();
		}

		FacilityManager.getInstance().updateInventory(ordersProcessed.getSource(),itemName, ordersProcessed.getNumberOfItems());

	}


	public void reduceQuantityNeeded(Order order, String itemName, OrdersProcessable ordersProcessed)
			throws Exception
	{
		if(order==null||itemName==null||ordersProcessed==null)
		{
			throw new NullPointerException();
		}

		OrderManager.getInstance().updateQuantity(ordersProcessed.getNumberOfItems(),order,itemName);

	}

	public void bookDays (OrdersProcessable ordersProcessed, Integer startDay)
			throws Exception
	{
		if(ordersProcessed == null)
		{
			throw new NullPointerException();
		}

		FacilityManager.getInstance().bookDays(ordersProcessed.getSource(),ordersProcessed.getNumberOfItems(), startDay);

	}

	public boolean backOrdered(Order order, String itemName)
			throws Exception
	{
		if(order==null||itemName==null)
		{
			throw new NullPointerException();
		}

		return OrderManager.getInstance().itemNotSatisfied(order, itemName);
	}

	public Integer getItemCost(String itemName)
			throws Exception
	{
		if(itemName==null)
		{
			throw new NullPointerException();
		}
		return ItemManager.getInstance().getItemPrice(itemName);
	}

	public int itemCostPerFacility(OrdersProcessable processedItem)
			throws Exception
	{
		if(processedItem==null)
		{
			throw new NullPointerException();
		}

		String itemName = processedItem.getItemName();
		int itemCostPerUnit = getItemCost(itemName);
		int quantityOfItems = processedItem.getNumberOfItems();
		int costOfItemForRecord = quantityOfItems*itemCostPerUnit;

		int costToProcessPerDay = FacilityManager.getInstance().costToProcessPerDay(processedItem.getSource());
		int daysProcessed = processedItem.getDaysTookToProcess();

		int costToProcess = daysProcessed*costToProcessPerDay;

		int travelCost =0;

		if(quantityOfItems!=0)
		{
			int travelTime = processedItem.getTravelTime();
			travelCost = travelTime * TRAVEL_COST_PER_DAY;
		}

		int totalCost = costOfItemForRecord + costToProcess + travelCost;

		return totalCost;
	}

	public Integer totalCostForItemOnOrder(ArrayList<OrdersProcessable> ordersProcessed)
			throws Exception
	{
		if(ordersProcessed==null)
		{
			throw new NullPointerException();
		}


		int totalCost= 0;

		for(OrdersProcessable processedItem : ordersProcessed)
		{
			totalCost += itemCostPerFacility(processedItem);
		}

		return totalCost;
	}

	public Integer getSourcesUsed(ArrayList<OrdersProcessable> ordersProcessed)
			throws Exception
	{

		if(ordersProcessed==null)
		{
			throw new NullPointerException();
		}


		int sourcesUsed=0;
		for(OrdersProcessable ordersAtFacility : ordersProcessed)
		{
			if(!ordersAtFacility.getNumberOfItems().equals(0))
			{
				sourcesUsed++;
			}
		}

		return sourcesUsed;

	}

	public Integer getFirstDay(ArrayList<OrdersProcessable> ordersProcessed)
			throws Exception
	{
		if(ordersProcessed==null)
		{
			throw new NullPointerException();
		}

		int firstDay = ordersProcessed.get(0).getArrivalDate();

		for(int i=1;i< ordersProcessed.size();i++)
		{
			if(ordersProcessed.get(i).getArrivalDate()<firstDay)
			{
				firstDay=ordersProcessed.get(i).getArrivalDate();
			}
		}

		return firstDay;

	}

	public Integer getLastDay(ArrayList<OrdersProcessable> ordersProcessed)
			throws Exception
	{
		if(ordersProcessed==null)
		{
			throw new NullPointerException();
		}

		int lastDay = ordersProcessed.get(0).getArrivalDate();

		for(int i=1;i< ordersProcessed.size();i++)
		{
			if(ordersProcessed.get(i).getArrivalDate()>lastDay)
			{
				lastDay=ordersProcessed.get(i).getArrivalDate();
			}
		}
		return lastDay;

	}

	public ArrayList<Integer> getOriginalOrderQuantity(Order order)
			throws Exception
	{
		return OrderManager.getInstance().getOriginalQuantity(order);
	}


	public Integer totalCostOfOrder(OrdersProcessedSummary summary)
			throws Exception
	{
		if(summary == null)
		{
			throw new NullPointerException();
		}

		Integer totalCost = 0;
		ArrayList<Integer> costsPerItem = summary.getCostPerItem();
		for(Integer itemCost : costsPerItem)
		{
			totalCost+=itemCost;
		}

		return totalCost;
	}

	public void createSummary(ArrayList<Integer> firstDay, ArrayList<Integer> lastDay, Order order,
												ArrayList<Integer> costPerItem, ArrayList<Integer> sourcesUsed)
			throws Exception
	{
		if(firstDay==null||order==null||lastDay==null||costPerItem==null||sourcesUsed==null)
		{
			throw new NullPointerException();
		}


		OrdersProcessedSummary summary = new OrdersProcessedSummary(firstDay, lastDay, order.getItemsOnOrder() ,getOriginalOrderQuantity(order), costPerItem, sourcesUsed);

		orderInformation.add(summary);
	}

	public int summaryFirstDeliveryDay(OrdersProcessedSummary summary)
			throws Exception
	{
		if(summary==null)
		{
			throw new NullPointerException();
		}

		ArrayList<Integer> firstDays = summary.getFirstDeliveryDay();

		int firstDay = firstDays.get(0);

		for(int i=1;i< firstDays.size();i++)
		{
			if(firstDays.get(i)<firstDay)
			{
				firstDay=firstDays.get(i);
			}
		}

		return firstDay;
	}

	public int summaryLastDeliveryDay(OrdersProcessedSummary summary)
			throws Exception
	{
		if(summary==null)
		{
			throw new NullPointerException();
		}

		ArrayList<Integer> lastDays = summary.getLastDeliveryDay();

		int lastDay = lastDays.get(0);

		for(int i=1;i< lastDays.size();i++)
		{
			if(lastDays.get(i)>lastDay)
			{
				lastDay=lastDays.get(i);
			}
		}

		return lastDay;
	}


	public void logSummary(ArrayList<Order> orders)
			throws Exception
	{
		OrderSummaryLoggable logger = OrderSummaryLogFactory.createNewOrdersLog();
		logger.orderSummary(orderInformation, orders);
	}



}
