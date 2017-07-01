package orderitemprocessing;

import orders.Order;

import java.util.ArrayList;


public interface OrderSummaryLoggable {

    public void orderSummary(ArrayList<OrdersProcessedSummary> summary, ArrayList<Order> orders) throws Exception;
}

