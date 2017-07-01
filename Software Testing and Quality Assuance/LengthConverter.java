package assignment3;
public class LengthConverter {
	
	public static final double kmToMile = 0.621371192;
	 /**
	   * Converting kilometers to miles. 
	   */
	  public static double KilometersToMile (double Kilometers) throws IllegalArgumentException {
			if (Kilometers < 0){
				throw new IllegalArgumentException("Kilometers can't be negative.");
			}
			return Kilometers * kmToMile;
	  		}
	  
	  /**
	   * Converting miles to kilometers. 
	   */
	  public static double MilesToKilometers(double Miles) throws IllegalArgumentException{
			 if (Miles < 0){
				throw new IllegalArgumentException("Miles can't be negative.");
			}
			 return Miles/ kmToMile; 
	  		}
  	  
}