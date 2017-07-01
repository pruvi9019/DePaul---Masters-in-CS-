package orders;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import exceptions.DataException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class OrderXmlLoaderImpl implements OrderLoader {

    public ArrayList<Order> getOrders() throws Exception
    {
        ArrayList<Order> orderCollection = new ArrayList<Order>();

        try {
            String fileName = "Orders.xml";

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(fileName);
            if (!xml.exists())
            {
                System.err.println("**** XML File '" + fileName + "' cannot be found");
                System.exit(-1);
            }

            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();

            NodeList orderEntries = doc.getDocumentElement().getChildNodes();
            //Get each Item Node
            for (int i = 0; i < orderEntries.getLength(); i++)
            {
                if (orderEntries.item(i).getNodeType() == Node.TEXT_NODE)
                {
                    continue;
                }

                String entryName = orderEntries.item(i).getNodeName();
                if (!entryName.equals("Order"))
                {
                    System.err.println("Unexpected node found: " + entryName);
                    return orderCollection;
                }

                NamedNodeMap aMap = orderEntries.item(i).getAttributes();
                String orderID = aMap.getNamedItem("Id").getNodeValue();

                Element elem = (Element) orderEntries.item(i);

                int orderTime = Integer.parseInt(elem.getElementsByTagName("OrderTime").item(0).getTextContent());
                String destination = elem.getElementsByTagName("Destination").item(0).getTextContent();

                NodeList itemList = elem.getElementsByTagName("Item");

                ArrayList<String> items = new ArrayList<String>();
                ArrayList<Integer> quantity = new ArrayList<Integer>();
                ArrayList<Integer> quantityOriginal = new ArrayList<Integer>();

                for (int l = 0; l < itemList.getLength(); l++) {

                    if (itemList.item(l).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }

                    entryName = itemList.item(l).getNodeName();
                    if (!entryName.equals("Item")) {
                        System.err.println("Unexpected node found: " + entryName);
                        return orderCollection;
                    }


                    elem = (Element) itemList.item(l);
                    String itemName = elem.getElementsByTagName("ItemName").item(0).getTextContent();
                    int quantityForItem = Integer.parseInt(elem.getElementsByTagName("ItemQuantity").item(0).getTextContent());
                    int quantityForItemOriginal = Integer.parseInt(elem.getElementsByTagName("ItemQuantity").item(0).getTextContent());

                    items.add(itemName);
                    quantity.add(quantityForItem);
                    quantityOriginal.add(quantityForItemOriginal);
                }

                if(orderID == null || destination == null || items == null || quantity == null||quantityOriginal==null)
                {
                    throw new DataException();
                }

                orderCollection.add(OrderFactory.createNewOrder(orderID, orderTime, destination, items, quantity,quantityOriginal));

            }

        }
        catch (ParserConfigurationException | SAXException | IOException | DOMException e)
        {
            e.printStackTrace();
        }

        return orderCollection;
    }

}
