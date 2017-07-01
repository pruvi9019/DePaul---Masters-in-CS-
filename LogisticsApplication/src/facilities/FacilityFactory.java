package facilities;

import java.util.ArrayList;

public class FacilityFactory {

	public static Facility createNewFacility(String name, int ratePerDay, int itemsPerDay,
			ArrayList<FacilityLink> facilitylink, Schedule schedule, Inventory inventory)

			throws Exception {
		if (inventory == null || schedule == null || facilitylink == null || name == null) {
			throw new NullPointerException();
		}

		return new FacilityImpl(name, ratePerDay, itemsPerDay, facilitylink, schedule, inventory);
	}

}
