package assignment3;

import java.util.Arrays;
import static assignment3.LengthConverter.*;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


//static test of Kilometers to Miles and vice versa. 
@RunWith(Parameterized.class)
public class LengthConverterTest2 {
	
		@Parameters
		public static Collection<Double[]> data() {
			return Arrays.asList(new Double[][] {
				{0.0, 0.0},
				{58.11, 36.10788},
				{111.999, 69.593},
				{22648.222, 14072.9527062},
				{55555.55, 34520.618339},
				{987456.150, 613576.805},
				{-1.1,-1.770278400},
				{-0.2111,-0.3397},
				{-125.11000,-201.34503},
				{-89445.15151,-143948.017967},
				{-985646.151578,-1586243.720771}});
		}
		
		private Double Kilometers;
		private Double Miles;
		
		public LengthConverterTest2(Double newKilometers, Double newMiles){
			Kilometers = newKilometers;
			Miles = newMiles;			
		}
		
 
		/* Kilometers to miles test. */
		@Test
		 public void LengthConverterTest2KtoM (){
		 try{
			 	KilometersToMile(Kilometers);
		    	fail("Exception should have occurred");
		    	}
		    catch(IllegalArgumentException illegalArgumentException){
		    	assertEquals(illegalArgumentException.getMessage(), "Kilometers can't be negative.");
		  		}
		}
		
		/* Miles to Kilometers test. */
		@Test
		 public void LengthConverterTest2MtoK (){
		 try{
			 	MilesToKilometers(Miles);
		    	fail("Exception should have occurred");
		    	}
		    catch(IllegalArgumentException illegalArgumentException){
		    	assertEquals(illegalArgumentException.getMessage(), "Miles can't be negative.");
		  		}
		}
}