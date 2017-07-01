package itemcatalog.factory;

import itemcatalog.ItemsLedgerFiling;
import itemcatalog.impl.ItemsLedgerImpl;

public class ItemsLedger {


	public static ItemsLedgerFiling createNewItemLog() {
		return new ItemsLedgerImpl();
	}

}
