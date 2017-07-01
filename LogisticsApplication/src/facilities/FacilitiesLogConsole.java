package facilities;

import java.util.ArrayList;
import java.util.Collections;

import exceptions.ItemException;

public class FacilitiesLogConsole implements FacilitiesLoggable {

	private static final int ITEM_NAME_LONG = 8;
	private static final int DOUBLE_DIGIT_DAYS = 10;

	public void facilitiesLog(ArrayList<Facility> facilities) throws NullPointerException, ItemException, Exception {

		if (facilities == null) {
			throw new NullPointerException();
		}

		for(Facility facility: facilities)
		{
			
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println(facility.getName());
			System.out.println("-----------");
			System.out.println("Rate per Day:\t" + facility.getItemsPerDay());
			System.out.println("Cost per Day:\t" + facility.getRatePerDay());
			
			System.out.println();
			 
			System.out.println("Direct Links:");
			
			ArrayList<FacilityLink> facilitylinks = facility.getFacilityLinks();
			Collections.sort(facilitylinks);
			
			for(FacilityLink facilitylink : facilitylinks)
			{
				double days = (double)facilitylink.getMiles()/(50*8);
				String daysOutput =  String.format("%.1f",days);
				System.out.print(facilitylink.getFacilityLink() + " ("+  daysOutput +   "d); "); 
			}
			System.out.println("\n");
			
			System.out.println("Active Inventory:");

			Inventory inventory = facility.getInventory();

			ArrayList<String> items = inventory.getItem();

			ArrayList<Integer> quantity =  inventory.getQuantity();

			System.out.println("   Item ID \t\t Quantity");
			
			for(int j = 0; j < items.size(); j++)
			{
				
				if(items.get(j).length()< ITEM_NAME_LONG)
				{
					System.out.println("   " + items.get(j)+"\t\t   "+quantity.get(j));
				}
				else
				{
					System.out.println("   " +items.get(j)+"\t   "+quantity.get(j));
				}
			}
			
			
			System.out.println();

			System.out.print("Depleted (Used-Up) Inventory: ");

			ArrayList<String> depletedItem = facility.getDepletedItems();

			if (depletedItem == null || depletedItem.size() == 0) {
				System.out.print("None");
			} else {
				for (String item : depletedItem) {
					System.out.print(item + "; ");
				}
			}

			System.out.println();
			
			System.out.println("Schedule:");
			Schedule schedule = facility.getSchedule();
			int scheduleLength = schedule.scheduleLength();

			ArrayList<Integer> availableItems = schedule.getAvailable();

			StringBuffer daysString = new StringBuffer();
			StringBuffer availableItemsString = new StringBuffer();

			for (int i = 0; i < scheduleLength; i++) {
				if (i < DOUBLE_DIGIT_DAYS) {
					daysString.append(" ");
					daysString.append(i + 1);
					daysString.append(" ");
				} else {
					daysString.append(i + 1);
					daysString.append(" ");
				}

				int availableItemsRecord = availableItems.get(i);
				if (i >= 10 || (i == 0 && availableItemsRecord >= 10)) {
					availableItemsString.append(" ");
				}
				if (availableItemsRecord < 10) {
					availableItemsString.append(" ");
					availableItemsString.append(availableItemsRecord);
					availableItemsString.append(" ");
				} else {
					availableItemsString.append(availableItemsRecord);
					availableItemsString.append(" ");
				}
			}

			System.out.print("Day: \t\t");
			System.out.println(daysString.toString());
			System.out.print("Available: \t");
			System.out.println(availableItemsString.toString());

			System.out.println();

			System.out
					.println("--------------------------------------------------------------------------------------");
		}

	}
}