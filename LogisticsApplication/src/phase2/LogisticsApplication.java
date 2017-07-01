package phase2;

import facilities.FacilityLink;
import orders.Order;
import orderitemprocessing.OrderProcessingManager;
import orderitemprocessing.OrdersProcessable;

import java.util.ArrayList;


public class LogisticsApplication {

	public static void main(String[] args)
			throws Exception
	{
		
		OrderProcessingManager.getInstance().loadItems();
		OrderProcessingManager.getInstance().loadFacilities();
		OrderProcessingManager.getInstance().loadOrders();

		
		OrderProcessingManager.getInstance().logFacilities();

		ArrayList<Order> orders = OrderProcessingManager.getInstance().retrieveOrders();

		for(Order order:orders)
		{
			ArrayList<Integer> totalCostItem = new ArrayList<Integer>();
			ArrayList<Integer> sources = new ArrayList<Integer>();
			ArrayList<Integer> firstDay = new ArrayList<Integer>();
			ArrayList<Integer> lastDay = new ArrayList<Integer>();
			ArrayList<String> itemsOnOrder = OrderProcessingManager.getInstance().getItemsOnOrder(order);
			String orderId = OrderProcessingManager.getInstance().getOrderId(order);
			String destination = OrderProcessingManager.getInstance().getDestination(order);
			Integer orderDay = OrderProcessingManager.getInstance().getOrderDay(order);

			for(String item : itemsOnOrder)
			{
				ArrayList<OrdersProcessable> ordersProcessed = new ArrayList<OrdersProcessable>();

				ArrayList<String> facilitiesWithItem =
						OrderProcessingManager.getInstance().facilitiesWithItem(item, destination);

				for(String facility : facilitiesWithItem)
				{
					ArrayList<FacilityLink> shortestPath =
							OrderProcessingManager.getInstance().getShortestPath(facility,destination);

					Integer miles = OrderProcessingManager.getInstance().getMilesFromPath(shortestPath);

					Integer numberOfItems =
							OrderProcessingManager.getInstance().itemsInStock(facility,item);

					Integer travelTime = OrderProcessingManager.getInstance().travelTime(miles);

					Integer shipDate =
							OrderProcessingManager.getInstance().expectedShipDate(facility,numberOfItems,orderDay);


					Integer daysToProcess =
							OrderProcessingManager.getInstance().daysToProcess(facility,numberOfItems,orderDay);

					Integer expectedDeliverDay = shipDate + travelTime;

							ordersProcessed.add(
							OrderProcessingManager.getInstance().addProcessedOrder(orderId, facility, item,
									numberOfItems, daysToProcess, shipDate,travelTime, expectedDeliverDay));

				}

				OrderProcessingManager.getInstance().sortSourcesByDay(ordersProcessed);

				for(int i=0; i<ordersProcessed.size();i++)
				{
					String facility
							= OrderProcessingManager.getInstance().getFacilityName(ordersProcessed.get(i));

					Integer quantityNeeded =
							OrderProcessingManager.getInstance().getQuantityForItemInOrder(order,item);

					Integer amountToGive =
							OrderProcessingManager.getInstance().checkAmountGiven(ordersProcessed.get(i),quantityNeeded);


					
					OrderProcessingManager.getInstance().updateToItemsTaken(ordersProcessed.get(i),item,amountToGive);

					OrderProcessingManager.getInstance().updateInventory(ordersProcessed.get(i),item);

					OrderProcessingManager.getInstance().reduceQuantityNeeded(order,item,ordersProcessed.get(i));

					OrderProcessingManager.getInstance().bookDays(ordersProcessed.get(i),orderDay);

					OrderProcessingManager.getInstance().updateDaysTaken(facility,amountToGive,orderDay,ordersProcessed.get(i));

					OrderProcessingManager.getInstance().updateProcessingEndDay(destination,amountToGive,orderDay,ordersProcessed.get(i));

					OrderProcessingManager.getInstance().updateArrivalDay(destination,orderDay,ordersProcessed.get(i));

				}

				totalCostItem.add(OrderProcessingManager.getInstance().totalCostForItemOnOrder(ordersProcessed));
				sources.add(OrderProcessingManager.getInstance().getSourcesUsed(ordersProcessed));
				firstDay.add(OrderProcessingManager.getInstance().getFirstDay(ordersProcessed));
				lastDay.add(OrderProcessingManager.getInstance().getLastDay(ordersProcessed));
			}

			OrderProcessingManager.getInstance().createSummary(firstDay,lastDay,order,totalCostItem,sources);
		}

		OrderProcessingManager.getInstance().logSummary(orders);
		OrderProcessingManager.getInstance().logFacilities();
		
	}

}
