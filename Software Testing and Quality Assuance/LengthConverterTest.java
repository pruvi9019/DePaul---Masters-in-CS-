package assignment3;

import org.junit.*;
import static org.junit.Assert.*;
import static assignment3.LengthConverter.*;


public class LengthConverterTest {

	 /**
	   * Testing kilometers to miles. 
	   */
	@Test
	 public void LengthConverterTest1(){
		Double newExp, newAct; 
		
		try {
			KilometersToMile(-210.5);
			fail("Exception should have occurred");
		}
			catch (IllegalArgumentException IllegalArgumentException) {
			String expected = "Kilometers can't be negative.";
			String actual = IllegalArgumentException.getMessage();
			Assert.assertEquals(expected, actual);
							
		}
			newExp = 210.5 * kmToMile;
			newAct = KilometersToMile(210.5);
			assertEquals(newExp, newAct,0.0001);
			 
		/**
		  * Testing miles to kilometers. 
		  */
		try {
			MilesToKilometers(-984.354);
			fail("Exception should have occurred");
		}
			catch (IllegalArgumentException IllegalArgumentException) {
			String expected = "Miles can't be negative.";
			String actual = IllegalArgumentException.getMessage();
			Assert.assertEquals(expected, actual);			
		}
			newExp = 984.354 / kmToMile;
			newAct = MilesToKilometers(984.354);
			assertEquals(newExp, newAct,0.0001);
		}


}

