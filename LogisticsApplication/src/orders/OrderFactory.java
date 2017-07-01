package orders;

import exceptions.DataException;
import java.util.ArrayList;



public class OrderFactory {

    private OrderFactory()
    {

    }

    public static Order createNewOrder(String orderItemID, int orderTime, String destination,
                                       ArrayList<String> items, ArrayList<Integer> quantity, ArrayList<Integer> quantityOriginal)
                    throws DataException
    {

        return new OrderImpl(orderItemID, orderTime, destination, items, quantity, quantityOriginal);
    }

}
