package orderitemprocessing;

import orders.Order;

import java.util.ArrayList;


public class OrderSummaryLogToConsoleImpl implements OrderSummaryLoggable
{
    private static final int LENGTH_OF_STRING_IN_TAB = 7;

    public void orderSummary(ArrayList<OrdersProcessedSummary> summaries, ArrayList<Order> orders)
            throws Exception
    {
        if(summaries==null||orders==null)
        {
            throw new NullPointerException();
        }

        int orderNumber = 1;

        for(int i = 0; i<orders.size();i++)
        {

            Order order = orders.get(i);
            OrdersProcessedSummary summary = summaries.get(i);

            System.out.println("--------------------------------------------------------------------------------------------------- ");
            System.out.println("Order #" + orderNumber);
            System.out.println("\tOrder Id: " + order.getOrderID());
            System.out.println("\tOrder time: Day " + order.getOrderTime());
            System.out.println("\tOrder Destination: " + order.getDestination());
            System.out.println("\tList of Order Items:");

            ArrayList<String> items = order.getItemsOnOrder();
            ArrayList<Integer> quantityOfItems = order.getOriginalQuantityOfItems();

            int printOrder=1;
            for (int j = 0; j < items.size(); j++)
            {
                System.out.println("\t\t" + printOrder + ") Item ID:\t" + items.get(j) + ",\tQuantity:" + quantityOfItems.get(j));
                printOrder++;
            }

            orderNumber++;
            
            System.out.println();

            int totalCost = OrderProcessingManager.getInstance().totalCostOfOrder(summary);
            int firstDeliveryDay = OrderProcessingManager.getInstance().summaryFirstDeliveryDay(summary);
            int lastDeliveryDay = OrderProcessingManager.getInstance().summaryLastDeliveryDay(summary);


       
            System.out.println("Processing Solution:");
            System.out.println("\tTotal Cost:\t" + totalCost);
            System.out.println("\t1st Delivery Day:" + firstDeliveryDay);
            System.out.println("\tLast Delivery Day:" + lastDeliveryDay);
            System.out.println("\tOrder Items:");

            ArrayList<String> itemsProcessed = summary.getItems();
            ArrayList<Integer> quantity = summary.getQuantity();
            ArrayList<Integer> cost = summary.getCostPerItem();
            ArrayList<Integer> sources = summary.getSourcesUsed();
            ArrayList<Integer> firstDay = summary.getFirstDeliveryDay();
            ArrayList<Integer> lastDay = summary.getLastDeliveryDay();

            System.out.println("Item ID\t\tQuantity\tCost\t# Sources Used\tFirst Day\tLast Day");
            int index = 0;
            for (String item : itemsProcessed) {
                if (item.length() < LENGTH_OF_STRING_IN_TAB) {
                    System.out.println(item + "\t" + quantity.get(index) + "\t\t" + cost.get(index) + "\t\t" + sources.get(index) + "\t\t"
                            + firstDay.get(index) + "\t\t" + lastDay.get(index));
                } else {
                    System.out.println(item + "\t\t" + quantity.get(index) + "\t\t" + cost.get(index) + "\t\t" + sources.get(index) + "\t\t\t"
                            + firstDay.get(index) + "\t\t" + lastDay.get(index));
                }
                index++;
            }
            System.out.println("---------------------------------------------------------------------------------------------- ");

        }

    }

}
