package facilities;

public class FacilitiesLogFactory {
	
	private FacilitiesLogFactory()
	{
		
	}

	public static FacilitiesLoggable createNewFacilitiesLog()
	{
		return new FacilitiesLogConsole();
	}


}
