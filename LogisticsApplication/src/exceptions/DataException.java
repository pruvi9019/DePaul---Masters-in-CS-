package exceptions;

@SuppressWarnings("serial")
public class DataException extends Exception {

	// Data Null exception
	public DataException() {
		super("Objects can't be created with null!");
	}

}
