package exceptions;

@SuppressWarnings("serial")
public class ItemException extends Exception {

	// Item null exception
	public ItemException() {
		super("Item not found");
	}

}
