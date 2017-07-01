package orders;


import java.util.ArrayList;



public class OrdersLogToConsoleImpl implements OrdersLoggable
{
  
    public void ordersLog(ArrayList<Order> orders)
            throws Exception
    {
        int orderNumber = 1;
        for(Order order : orders)
        {
            System.out.println("---------------------------------------------------------------------------------- ");
            System.out.println("Order #" + orderNumber);
            System.out.println("\tOrder Id:\t" + order.getOrderID());
            System.out.println("\tOrder time:\tDay " + order.getOrderID());
            System.out.println("\tOrder Destination:\t" + order.getDestination());
            System.out.println("\tList of Order Items:");

            ArrayList<String> items = order.getItemsOnOrder();
            ArrayList<Integer> quantityOfItems = order.getQuantityOfItems();

            int itemNumber = 1;

            for(int i=0; i<items.size();i++)
            {
                System.out.println("\t\t"+itemNumber+") Item ID:\t"+items.get(i)+",\tQuantity:\t"+quantityOfItems.get(i));
            }

            orderNumber++;
           

    }

    }
}