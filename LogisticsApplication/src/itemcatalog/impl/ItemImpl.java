package itemcatalog.impl;

import itemcatalog.Item;

public class ItemImpl implements Item {

	private String name;
	private int price;

	public ItemImpl() {
		name = null;
		price = 0;
	}

	public ItemImpl(String newName, int newPrice) throws NullPointerException {
		if (newName == null) {
			throw new NullPointerException();
		}
		name = newName;
		price = newPrice;
	}

	public String getName() {
		return name;
	}

	public int getPrice()
	{
		int returnedPrice = price;
		return returnedPrice;
	}

}
