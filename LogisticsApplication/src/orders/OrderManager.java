package orders;

import exceptions.ItemException;

import java.util.ArrayList;



public class OrderManager {

    private ArrayList<Order> orders;

    private volatile static OrderManager orderManager;

    public static OrderManager getInstance() throws Exception
    {
        synchronized(OrderManager.class)
        {
            if (orderManager == null)
            {
                orderManager = new OrderManager();
            }
        }

        return orderManager;
    }

    private OrderManager()
            throws Exception
    {
        OrderLoader orderLoader = OrderLoaderFactory.createNewOrders();
        orders = orderLoader.getOrders();
    }

    public ArrayList<Order> sendOrdersToProcess()
    {

        return orders;

    }

    public String getOrderId(Order order)
    {
        return order.getOrderID();
    }

    public ArrayList<String> getItemsOnOrder(Order order)
    {
        return order.getItemsOnOrder();
    }

    public String getDestination(Order order)
    {
        if(order==null)
        {
            throw new NullPointerException();
        }
        return order.getDestination();
    }

    public Integer getOrderDay(Order order)
    {
        if(order==null)
        {
            throw new NullPointerException();
        }

        return order.getOrderTime();
    }

    public Integer getQuantityOfItemOnOrder(Order order, String itemName)
    {
        if(order==null||itemName==null)
        {
            throw new NullPointerException();
        }

        return order.getOriginalQuantityForItem(itemName);
    }

    public ArrayList<Integer> getOriginalQuantity(Order order)
    {
        return order.getOriginalQuantityOfItems();
    }

    public Order getOrder(String orderId)
            throws Exception
    {
        if(orderId==null)
        {
            throw new NullPointerException();
        }
        if(!orders.contains(orderId))
        {
            throw new ItemException();
        }

        int index = orders.indexOf(orderId);
        return orders.get(index);
    }

    public void logOrders() throws Exception
    {
        OrdersLoggable logger = OrdersLogFactory.createNewOrdersLog();
        logger.ordersLog(orders);
    }

    public void updateQuantity (Integer numberOfItems, Order order, String itemName)
            throws Exception
    {

        order.updateQuantity(numberOfItems,itemName);

    }

    public boolean itemNotSatisfied(Order order, String itemName)
    {
        if(order==null||itemName==null)
        {
            throw new NullPointerException();
        }

        return order.itemNotSatisfied(itemName);
    }



}
