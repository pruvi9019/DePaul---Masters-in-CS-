package orderitemprocessing;


public class OrderSummaryLogFactory {
    private OrderSummaryLogFactory()
    {

    }

    public static OrderSummaryLoggable createNewOrdersLog()
    {
        return new OrderSummaryLogToConsoleImpl();
    }

}
