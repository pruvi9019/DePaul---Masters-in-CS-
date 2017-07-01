package facilities;

import exceptions.ItemException;

public class FacilityLink implements Comparable<FacilityLink> {

	private String facilitylink;
	private int miles;

	public FacilityLink() {
		this.facilitylink = null;
		this.miles = 0;
	}

	public FacilityLink(String newFacilityLink, int newMiles) {
		this.facilitylink = newFacilityLink;
		this.miles = newMiles;
	}

	public String getFacilityLink() {
		return this.facilitylink;
	}

	public int getMiles() {
		return this.miles;
	}

	public boolean equals(FacilityLink facilitylinkChecked) {
		if (facilitylinkChecked == null) {
			throw new NullPointerException();
		}
		return (facilitylinkChecked.getFacilityLink().equals(this.facilitylink));
	}

	public Facility getFacility() throws NullPointerException, ItemException, Exception {
		return FacilityManager.getInstance().getFacility(this.facilitylink);
	}

	@Override
	public int compareTo(FacilityLink other) {
		return this.facilitylink.compareTo(other.facilitylink);
	}

}
