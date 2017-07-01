package Assignment1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Triangles {

    public static void main(String[] args) {
 
        boolean fileInput;

        Scanner keyboard = new Scanner(System.in);
        System.out.print("Read from \n1. File \n2. Keyboard? ");
        switch (keyboard.nextInt()) {
            case 1:
                fileInput = true;
                break;
            case 2:
                fileInput = false;
                break;
            default:
                System.out.print("Invalid input. Please re-enter.");
                fileInput = false;
        }


        if (!fileInput) {

            int side1, side2, side3;

            System.out.println("\nPlease enter three sides of the triangle");

            System.out.print("Side 1: ");
            side1 = keyboard.nextInt();

            System.out.print("Side 2: ");
            side2 = keyboard.nextInt();

            System.out.print("Side 3: ");
            side3 = keyboard.nextInt();

            String result = checkTriangle(side1, side2, side3);
            System.out.print("The triangle is "+ result);
        } else{
            try {
                FileInputStream fstream = new FileInputStream("TextFile.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

                String strLine;


                while ((strLine = br.readLine()) != null)   {
                  
                    String[] split = strLine.split(" ");

                    int side1 = Integer.parseInt(split[5]);
                    int side2 = Integer.parseInt(split[6]);
                    int side3 = Integer.parseInt(split[7]);
                    String actualResult = split[9];
                    String testCaseID = split[2];
                    String expectedResult = checkTriangle(side1,side2,side3);

                    System.out.print("\n\nTest case: " + testCaseID + ", \nExpected Result: " + expectedResult + ", Actual Result: "+ actualResult + ", \nVerdict: " + (actualResult.equals(expectedResult)?"Pass":"Fail"));
                }


                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static String checkTriangle(int side1, int side2, int side3) {
        int maxside=side1;
        int sum=0;
        String result;
        if(side2>maxside)
        {
            maxside=side2;
            sum=side1 + side3;
        }
        else if(side3>maxside)
        {
            maxside=side3;
            sum=side1+side2;
        }
        else
            sum=side2 + side3;

        if(sum<maxside)
            result = "Invalid";

        else
        {

            
            if((side1==side2) && (side2==side3))
            {
                result = "Equilateral";
            }//if

           
            else if ((side1 == side2) & (side2 != side3) || (side2 == side3) & (side3!= side1) || (side1 == side3) & (side2!= side1))
            {
                result = "Isoceles";
            }

            
            else
            {
                result = "Scalene";
            }

        }
        return result;
    }

}