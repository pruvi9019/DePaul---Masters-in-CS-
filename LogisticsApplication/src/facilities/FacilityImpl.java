package facilities;

import java.util.ArrayList;
import exceptions.DataException;

public class FacilityImpl implements Facility {

	private String name;
	private int ratePerDay;
	private int itemsPerDay;
	private ArrayList<FacilityLink> facilitylinks;
	private Schedule schedule;
	private Inventory inventory;
	
	public FacilityImpl(String newName, int newRatePerDay, int newItemsPerDay,
			ArrayList<FacilityLink>newFacilityLinks, Schedule newSchedule, Inventory newInventory)
			throws DataException
	{
		if(newName == null || newFacilityLinks == null || newSchedule == null || newInventory == null)
		{
			throw new DataException();
		}

		name = newName;
		ratePerDay = newRatePerDay;
		itemsPerDay = newItemsPerDay;
		facilitylinks = newFacilityLinks;
		schedule = newSchedule;
		inventory = newInventory;	
	}



	public String getName()
	{
		String returnedName = new String(name);
		return returnedName;
	}
	
	public int getRatePerDay()
	{
		int returnRatePerDay = ratePerDay;
		return returnRatePerDay;
	}
	
	public int getItemsPerDay()
	{
		int returnItemsPerDay = itemsPerDay;
		return returnItemsPerDay;
	}

	public ArrayList<FacilityLink> getFacilityLinks()
	{
		return new ArrayList<FacilityLink>(facilitylinks);
	}
	
	public Schedule getSchedule()
	{
		return new Schedule(schedule);
	}
	
	public Inventory getInventory()
	{
		return new Inventory(inventory);
	}


	public ArrayList<String> getDepletedItems()
	{
		return inventory.getDepletedItems();
	}

	public boolean itemInStock(String itemName)
	{

		if(itemName == null)
		{
			throw new NullPointerException();
		}

		return inventory.itemInStock(itemName);

	}

	public int expectedShipDate(Integer quantity, Integer startDay)
	{
		if(quantity == null || startDay == null)
		{
			throw new NullPointerException();
		}

		return schedule.expectedShipDate(quantity, itemsPerDay, startDay);
	}

	public void bookDays(Integer quantity, Integer startDay)
	{
		if(quantity == null)
		{
			throw new NullPointerException();
		}
		schedule.bookDays(quantity, itemsPerDay, startDay);
	}

	public int daysToProcess(Integer quantity, Integer startDay)
	{
		if(quantity == null|| startDay == null)
		{
			throw new NullPointerException();
		}
		return schedule.daysToProcess(quantity, itemsPerDay, startDay);
	}

	public Integer checkStock(String itemName)
	{
		return inventory.checkStock(itemName);
	}

	public Integer checkAmountGiven(String itemName, Integer amountNeeded)
	{
		return inventory.checkAmountGiven(itemName, amountNeeded);
	}

	public void reduceInventory(Integer quantity, String itemName)
	{
		inventory.reduceInventory(quantity, itemName);
	}


}
