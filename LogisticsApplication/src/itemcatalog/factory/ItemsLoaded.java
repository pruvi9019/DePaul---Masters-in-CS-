package itemcatalog.factory;

import itemcatalog.ItemsLoader;
import itemcatalog.impl.ItemXmlInputImpl;

public class ItemsLoaded {


	public static ItemsLoader createNewItem() {
		return new ItemXmlInputImpl();
	}

}
