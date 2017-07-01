package orderitemprocessing;


public class OrdersProcessedFactory {

    private OrdersProcessedFactory()
    {

    }
    public static OrdersProcessable createNewOrders(String orderID,String newSource, String newItemName,
                                                    int newNumberOfItems, int newDaysTookToProcess, int newProcessingEndDate,
                                                    int newTravelTime, int newArrivalDate)
            throws Exception
    {
        if(orderID==null||newSource==null||newItemName==null)
        {
            throw new NullPointerException();
        }


        return new OrderProcessedImpl(orderID, newSource, newItemName, newNumberOfItems, newDaysTookToProcess, newProcessingEndDate, newTravelTime, newArrivalDate);
    }

}
