package itemcatalog.impl;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import itemcatalog.Item;
import itemcatalog.ItemsLoader;
import itemcatalog.factory.ItemList;

public class ItemXmlInputImpl implements ItemsLoader {

	public ArrayList<Item> getItems() throws Exception {

		ArrayList<Item> itemCollection = new ArrayList<Item>();

		DocumentBuilderFactory dbf = null;
		DocumentBuilder db = null;
		File xml = null;
		
		try {
			String fileName = "ItemCatalog.xml";

			dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();

			xml = new File(fileName);
			if (!xml.exists()) {
				System.err.println("**** XML File '" + fileName + "' cannot be found");
				System.exit(-1);
			}

			Document doc = db.parse(xml);
			doc.getDocumentElement().normalize();

			NodeList itemEntries = doc.getDocumentElement().getChildNodes();
			int itemEntriesLength = itemEntries.getLength();
		
			for (int i = 0; i < itemEntriesLength; i++) {
				Node itemEntriesRecord = itemEntries.item(i);
				if (itemEntriesRecord.getNodeType() == Node.TEXT_NODE) {
					continue;
				}

				String entryName = itemEntriesRecord.getNodeName();
				if (!entryName.equals("Item")) {
					System.err.println("Unexpected node found: " + entryName);
					return itemCollection;
				}

				Element elem = (Element) itemEntriesRecord;
				String itemName = elem.getElementsByTagName("ItemName").item(0).getTextContent();
				int itemPrice = Integer.parseInt(elem.getElementsByTagName("ItemPrice").item(0).getTextContent());
				if (itemName == null) {
					throw new NullPointerException();
				}
				itemCollection.add(ItemList.createNewItem(itemName, itemPrice));
			}

		} catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
			e.printStackTrace();
		} finally {
			if(xml != null) xml = null;
			if(db != null) db = null;
			if(dbf != null) dbf = null;
		}

		return itemCollection;
	}

}
