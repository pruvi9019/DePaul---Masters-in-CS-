package facilities;

import java.util.ArrayList;

import exceptions.ItemException;

public interface FacilitiesLoggable {
	
	public void facilitiesLog(ArrayList<Facility> facilities) throws NullPointerException, ItemException, Exception;

}