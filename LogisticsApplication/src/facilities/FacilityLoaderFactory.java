package facilities;

public class FacilityLoaderFactory {
	
	private FacilityLoaderFactory()
	{
	}
	
	public static FacilityLoader createNewFacilities()
	{
		return new FacilitiesXMLParser();
	}
	

}

