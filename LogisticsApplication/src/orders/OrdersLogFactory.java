package orders;


public class OrdersLogFactory
{

    private OrdersLogFactory()
    {

    }
    public static OrdersLoggable createNewOrdersLog()
    {
        return new OrdersLogToConsoleImpl();
    }


}
