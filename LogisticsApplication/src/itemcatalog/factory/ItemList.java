package itemcatalog.factory;

import itemcatalog.Item;
import itemcatalog.impl.ItemImpl;

public class ItemList {
	private ItemList(){}


	public static Item createNewItem(String name, int price) {

		return new ItemImpl(name, price);
	}

}
