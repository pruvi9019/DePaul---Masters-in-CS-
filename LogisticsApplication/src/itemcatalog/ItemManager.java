package itemcatalog;

import java.util.ArrayList;

import exceptions.DataException;
import itemcatalog.factory.ItemsLedger;
import itemcatalog.factory.ItemsLoaded;

public final class ItemManager {

	private ArrayList<Item> items;
	private volatile static ItemManager itemManager;

	public static ItemManager getInstance() throws Exception {
		synchronized (ItemManager.class) {
			if (itemManager == null) {
				itemManager = new ItemManager();
			}
		}

		return itemManager;
	}

	private ItemManager() throws Exception {
		ItemsLoader itemLoader = ItemsLoaded.createNewItem();
		items = itemLoader.getItems();
	}

	public void logItems() {
		ItemsLedgerFiling logger = ItemsLedger.createNewItemLog();

		logger.itemLog(items);
	}

	public boolean itemExists(Item item)
			throws NullPointerException
		{
			if(item == null)
			{
				throw new NullPointerException();
			}
			
			return items.contains(item);
		}
		//Gets the items price
		public int getItemPrice(String itemNeededName)
				throws NullPointerException, DataException
		{
			//Do not want to check null items
			if(itemNeededName == null)
			{
				throw new NullPointerException();
			}

			Item itemNeeded = getItem(itemNeededName);

			//If item doesn't exist, do not want to return 0
			if(!items.contains(itemNeeded))
			{
				throw new DataException();
			}
			
			int price = 0;
			
			price = items.get(items.indexOf(itemNeeded)).getPrice();
			
			return price;
		}
		
		public Item getItem(String itemNeeded) 
				throws NullPointerException, DataException
		{
			//Do not want to check null items
			if(itemNeeded == null)
			{
				throw new NullPointerException();
			}
			
			Item foundItem = null;
			
			for(Item item : items)
			{
				if (item.getName().equals(itemNeeded))
				{
					foundItem = item;
				}
			}
			
			return foundItem;
			
		}
}	