package orderitemprocessing;


public interface OrdersProcessable extends Comparable<OrdersProcessable>
{

    public String getOrderID();

    public void updateItems(Integer takenItems) throws Exception;

    public int getArrivalDate();

    public String getSource();

    public Integer getNumberOfItems();

    public Integer getDaysTookToProcess();

    public String getItemName();

    public void setDaysTaken(Integer days);

    public void setEndDay(Integer day);

    public void setArrivalDay(Integer day);

    public Integer getTravelTime();
}
