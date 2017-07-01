package orderitemprocessing;

import exceptions.DataException;



public class OrderProcessedImpl implements OrdersProcessable
{
    private String orderID;
    private String source;
    private String itemName;
    private int numberOfItems;
    private int daysTookToProcess;
    private int travelTime;
    private int arrivalDate;

    public OrderProcessedImpl(String newOrderID, String newSource, String newItemName,
                              int newNumberOfItems, int newDaysTookToProcess, int newProcessingEndDate,
                              int newTravelTime, int newArrivalDate)
            throws Exception
    {

        if(newSource == null || newOrderID == null)
        {
            throw new DataException();
        }

        orderID = newOrderID;
        source = newSource;
        itemName = newItemName;
        numberOfItems = newNumberOfItems;
        daysTookToProcess = newDaysTookToProcess;
        travelTime = newTravelTime;
        arrivalDate = newArrivalDate;

    }

    public String getOrderID()
    {
        return new String(orderID);
    }

    public String getSource(){return new String(source);}

    public String getItemName(){return new String(itemName);}

    public Integer getNumberOfItems(){return numberOfItems;}

    public Integer getDaysTookToProcess(){return daysTookToProcess;}

    public Integer getTravelTime(){return travelTime;}

    public int getArrivalDate()
    {
        int returnArrivalDate = arrivalDate;
        return returnArrivalDate;
    }

    public void updateItems(Integer amountNeeded)
            throws Exception
    {
        if(amountNeeded == null)
        {
            return;
        }

        numberOfItems = amountNeeded;
    }

    public void setDaysTaken(Integer days)
    {
        if(days==null)
        {
            throw new NullPointerException();
        }

        daysTookToProcess = days;
    }

    public void setEndDay(Integer day)
    {
        if(day==null)
        {
            throw new NullPointerException();
        }


    }

    public void setArrivalDay(Integer day)
    {
        if(day==null)
        {
            throw new NullPointerException();
        }

        arrivalDate = day;
    }

    public int compareTo(OrdersProcessable orderProcess)
    {
        if(arrivalDate > orderProcess.getArrivalDate())
        {
            return 1;
        }
        else if(arrivalDate < orderProcess.getArrivalDate())
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }


}
