package orderitemprocessing;

import java.util.ArrayList;

public class OrdersProcessedSummary {

    private ArrayList<Integer> firstDeliveryDay;
    private ArrayList<Integer> lastDeliveryDay;
    ArrayList<String> items;
    ArrayList<Integer> quantity;
    ArrayList<Integer> costPerItem;
    ArrayList<Integer> sourcesUsed;


    public OrdersProcessedSummary(ArrayList<Integer> newFirstDeliveryDay, ArrayList<Integer> newLastDeliveryDay,
                                  ArrayList<String> newItems, ArrayList<Integer> newQuantity,
                                  ArrayList<Integer> newCostPerItem, ArrayList<Integer> newSourcesUsed)
    {
        firstDeliveryDay = newFirstDeliveryDay;
        lastDeliveryDay = newLastDeliveryDay;
        items = newItems;
        quantity = newQuantity;
        costPerItem = newCostPerItem;
        sourcesUsed = newSourcesUsed;

    }


    public ArrayList<Integer> getFirstDeliveryDay()
    {
        return new ArrayList<Integer>(firstDeliveryDay);
    }

    public ArrayList<Integer> getLastDeliveryDay()
    {
        return new ArrayList<Integer>(lastDeliveryDay);
    }

    public ArrayList<String> getItems()
    {
        return new ArrayList<String>(items);
    }

    public ArrayList<Integer> getQuantity()
    {
        return new ArrayList<Integer>(quantity);
    }

    public ArrayList<Integer> getCostPerItem()
    {
        return new ArrayList<Integer>(costPerItem);
    }

    public ArrayList<Integer> getSourcesUsed()
    {
        return  new ArrayList<Integer>(sourcesUsed);
    }


}
