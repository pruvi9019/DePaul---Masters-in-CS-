package orders;


public class OrderLoaderFactory {

    private OrderLoaderFactory()
    {

    }
    //Only one way to load
    //Needed in case other file formats are specified
    public static OrderLoader createNewOrders()
    {
        return new OrderXmlLoaderImpl();
    }

}
