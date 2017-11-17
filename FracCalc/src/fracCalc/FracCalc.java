/*
 * Author: derfel terciano
 * 
 * Version:1
 * 
 * 
 * calculates fractions that are improper and mixed and reduces everything
 * 
 * 
 */
package fracCalc;

import java.util.*;

public class FracCalc {

	public static void main(String[] args) {
		// TODO: Read the input from the user and call produceAnswer with an equation
		boolean end = false;
		Scanner input = new Scanner(System.in);
		while(end==false) {
			System.out.println("Enter your fraction: ");
			String userFraction = input.nextLine();
			if(userFraction.equals("quit")) {
				System.out.println("Bye!");
				end=true;
			} else {
				System.out.println(produceAnswer(userFraction));
				
				
				
			}
		}
	}

	// ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
	// test your code
	// This function takes a String 'input' and produces the result
	//
	// input is a fraction string that needs to be evaluated. For your program, this
	// will be the user input.
	// e.g. input ==> "1/2 + 3/4"
	//
	// The function should return the result of the fraction after it has been
	// calculated
	// e.g. return ==> "1_1/4"
	public static String produceAnswer(String input) {
		// TODO: Implement this function to produce the solution to the input
		String[] splitInput = input.split(" ");
		String[] parsedResult = parseFrac(splitInput[2]);
		String whole = "whole:"+ parsedResult[0];
		String numerator = "numerator:"+ parsedResult[1];
		String denominator = "denominator:"+ parsedResult[2];
		return whole+" "+numerator+" "+ denominator;
	}
	
	// TODO: Fill in the space below with any helper methods that you think you will
	// need
	
	public static String[] parseFrac(String input) {
		String[] result = new String[3];
		if (input.indexOf("_")>0) {
			String[] wholeNum= input.split("_");
			result[0]=wholeNum[0];
			String[] frac = wholeNum[1].split("/");
			result[1]=frac[0];
			result[2]=frac[1];
		} else if(input.indexOf("_")<0){
			if(input.indexOf("/")>0) {
				result[0]="0";
				String[] fracWithoutWhole = input.split("/");
				result[1]=fracWithoutWhole[0];
				result[2]=fracWithoutWhole[1];
			} else {
				result[0]=input;
				result[1]="0";
				result[2]="1";
			}
		}
		return result;
	}
	
	
	public static int[] convStrtoInt(String[] input) {
		int[] result = new int[3];
		for (int i=0; i <input.length;i++) {
			result[i]=Integer.parseInt(input[i]);
		}
		return result;
	}
	

}
