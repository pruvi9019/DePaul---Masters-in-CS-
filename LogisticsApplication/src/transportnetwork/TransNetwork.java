package transportnetwork;

import java.util.ArrayList;

import exceptions.ItemException;
import facilities.FacilityLink;


public interface TransNetwork {
	

	public ArrayList<FacilityLink> getShortestPath(FacilityLink start, FacilityLink end)
			throws NullPointerException, ItemException, Exception;

}
