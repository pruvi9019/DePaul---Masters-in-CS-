package itemcatalog.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;

import itemcatalog.Item;
import itemcatalog.ItemsLedgerFiling;

public class ItemsLedgerImpl implements ItemsLedgerFiling {

	private static final int ITEMS_PER_LINE = 4;
	private DecimalFormat currencyFormatter = new DecimalFormat("#,###");
	
	public ItemsLedgerImpl(){}

	public void itemsLog(ArrayList<Item> items) throws NullPointerException {

		if (items == null) {
			throw new NullPointerException();
		}

		System.out.println("Item Catalog:\n");

		for (int j = 0; j < items.size(); j += ITEMS_PER_LINE) {
			for (int i = j; i < j + ITEMS_PER_LINE; i++) {

				if (items.get(i).getName().length() < 7) {
					System.out.print(items.get(i).getName() + ":\t\t $" + currencyFormatter.format(items.get(i).getPrice()) + "\t\t");
				} else {
					System.out.print(items.get(i).getName() + ":\t $" + currencyFormatter.format(items.get(i).getPrice()) + "\t\t");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	@Override
	public void itemLog(ArrayList<Item> items) {
		// TODO Auto-generated method stub
		
	}

}
