package facilities;

import java.util.ArrayList;

import exceptions.ItemException;

public class FacilityManager {

private ArrayList<Facility> facilities;
	
	private volatile static FacilityManager facilityManager;
	
	public static FacilityManager getInstance() throws Exception
	{
		synchronized(FacilityManager.class)
		{
			if (facilityManager == null)
			{
				facilityManager = new FacilityManager();
			}
		}
		
		return facilityManager;
	}


	private FacilityManager() throws Exception
	{
		FacilityLoader facilityLoader = FacilityLoaderFactory.createNewFacilities();
		facilities = facilityLoader.getFacilities();
	}

	public boolean facilityExists(Facility facility)
			throws NullPointerException
	{
		if(facility == null)
		{
			throw new NullPointerException();
		}
		
		return facilities.contains(facility);
	}

	public Facility getFacility(String name)
		throws NullPointerException, ItemException
	{
		if(name == null)
		{
			throw new NullPointerException();
		}
		
		Facility foundFacility = null;
		
		for(Facility facility : facilities)
		{
			if (facility.getName().equals(name))
			{
				foundFacility = facility;
			}

		}
		
		if(foundFacility==null)
		{
			throw new ItemException();
		}
		
		
		return foundFacility;
	}
	
	
	public Schedule getFacilitySchedule(Facility facilityNeeded)
			throws NullPointerException, ItemException
	{
		if(facilityNeeded == null)
		{
			throw new NullPointerException();
		}
		
		if(!facilities.contains(facilityNeeded))
		{
			throw new ItemException();
		}
		
		Schedule schedule = null;
		
		schedule = facilities.get(facilities.indexOf(facilityNeeded)).getSchedule();
		
		return schedule;
		
	}
	
	public ArrayList<FacilityLink> getFacilityLinks(Facility facilityNeeded)
			throws NullPointerException, ItemException
	{
		if(facilityNeeded == null)
		{
			throw new NullPointerException();
		}
		
		if(!facilities.contains(facilityNeeded))
		{
			throw new ItemException();
		}
		
		ArrayList<FacilityLink> facilitylinks = null;
		
		facilitylinks = facilities.get(facilities.indexOf(facilityNeeded)).getFacilityLinks();
		
		return facilitylinks;
		
	}
	
	public Inventory getInventory(Facility facilityNeeded)
			throws NullPointerException, ItemException
	{
		if(facilityNeeded == null)
		{
			throw new NullPointerException();
		}
		
		if(!facilities.contains(facilityNeeded))
		{
			throw new ItemException();
		}
		
		Inventory inventory = null;
		
		inventory = facilities.get(facilities.indexOf(facilityNeeded)).getInventory();
		
		return inventory;
		
	}
	
	public FacilityLink toFacilityLink(Facility facility)
	{
		if(facility == null)
		{
			throw new NullPointerException();
		}

		int start = 0;
		FacilityLink startFacilityLink = new FacilityLink(facility.getName(), start);
		
		return startFacilityLink;
	}
	
	
	public void logItems() throws NullPointerException, ItemException, Exception
	{
		FacilitiesLoggable logger = FacilitiesLogFactory.createNewFacilitiesLog();
		
		logger.facilitiesLog(facilities);
	}

	public boolean hasItem(String itemName, String facilityName)
			throws Exception
	{

		if(facilityName == null||itemName == null)
		{
			throw new NullPointerException();
		}

		Facility facility = getFacility(facilityName);

		return facilities.get(facilities.indexOf(facility)).itemInStock(itemName);
	}

	public Integer expectedShipDate(Integer quantity, String facility, Integer startDay)
			throws Exception
	{
		if(facility == null || quantity == null)
		{
			throw new NullPointerException();
		}

		return getFacility(facility).expectedShipDate(quantity, startDay);
	}

	public int daysToProcess(String facility, Integer quantity, Integer startDay)
			throws Exception
	{
		if(facility == null || quantity==null)
		{
			throw new NullPointerException();
		}

		return getFacility(facility).daysToProcess(quantity, startDay);

	}

	public void updateAvailability(Integer quantity, String facility, Integer startDay)
			throws Exception
	{
		if(facility == null || quantity==null)
		{
			throw new NullPointerException();
		}

		getFacility(facility).bookDays(quantity, startDay);
	}

	public ArrayList<String> facilitiesWithItem(String item, String destination)
	{
		if(item==null)
		{
			throw new NullPointerException();
		}

		ArrayList<String> facilitiesWithItem = new ArrayList<String>();

		for(Facility facility : facilities)
		{
			if(facility.itemInStock(item) && !facility.getName().equals(destination))
			{
				facilitiesWithItem.add(facility.getName());
			}
		}

		return facilitiesWithItem;
	}

	public Integer checkAmountGiven(String facilityName, String itemName, Integer amountNeeded)
			throws Exception
	{
		if(facilityName==null||itemName==null||amountNeeded==null)
		{
			throw new NullPointerException();
		}

		Facility facility = getFacility(facilityName);

		return facility.checkAmountGiven(itemName,amountNeeded);
	}

	public void updateInventory(String facilityName, String itemName, int quantityOfItems)
			throws Exception
	{
		if(facilityName==null||itemName==null)
		{
			throw new NullPointerException();
		}

		Facility facility = getFacility(facilityName);

		facility.reduceInventory(quantityOfItems,itemName);
	}

	public void bookDays(String facilityName, Integer numberOfItems, Integer startDay)
			throws Exception
	{
		Facility facility = getFacility(facilityName);

		facility.bookDays(numberOfItems, startDay);
	}

	public int costToProcessPerDay(String facilityName)
			throws Exception
	{
		if(facilityName==null)
		{
			throw new NullPointerException();
		}

		Facility facility = getFacility(facilityName);

		return facility.getRatePerDay();
	}


}
