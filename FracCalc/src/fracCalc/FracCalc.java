/*
 * @author derfel terciano
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
		String result = null;
		for(int i =0; i < splitInput.length; i++) {
			if( splitInput[i].equals("+")||splitInput[i].equals("-")||splitInput[i].equals("*")||splitInput[i].equals("/")) {
				if (splitInput[i].equals("+")) {
					result=addOrSubtractFrac(splitInput[i-1],splitInput[i+1],"+");
				}else if(splitInput[i].equals("-")) {
					result=addOrSubtractFrac(splitInput[i-1],splitInput[i+1],"-");
				}else if(splitInput[i].equals("*")) {
					result = multOrDivFrac(splitInput[i-1],splitInput[i+1],"*");
				}else {
					result = multOrDivFrac(splitInput[i-1],splitInput[i+1],"/");
				}
				if(i<splitInput.length) {
					splitInput[i+1]=result;
				} else {
					splitInput[i]=result;
				}
			}

		}
		return splitInput[splitInput.length-1];
		
		/*String operation = splitInput[1];
		int[] frac1= toImproperFrac(convStrtoInt(parseFrac(splitInput[0])));
		int[] frac2= toImproperFrac(convStrtoInt(parseFrac(splitInput[2])));
		if (operation.equals("+")) {
			result=addOrSubtractFrac(frac1,frac2,"+");
		}else if(operation.equals("-")) {
			result=addOrSubtractFrac(frac1,frac2,"-");
		}else if(operation.equals("*")) {
			result = multOrDivFrac(frac1,frac2,"*");
		}else {
			result = multOrDivFrac(frac1,frac2,"/");
		}
		return result;*/
		
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
	
	public static String toString (int[] inputFrac) {
		String result = "";
		if (inputFrac.length==3) {
			result= inputFrac[0]+"_"+inputFrac[1]+"/"+inputFrac[2];
		}else if(inputFrac.length==2 && inputFrac[1]==1) {
			result= inputFrac[0]+"";
		}else if(inputFrac.length==2) {
			result= inputFrac[0]+"/"+inputFrac[1];
		}
			
		if(result.equals("0/1")||(inputFrac.length==2 && inputFrac[0]==0)) {
			result="0";
		}else if(inputFrac.length==3 && inputFrac[1]==0) {
			result=inputFrac[0]+"";
		}else if(inputFrac.length==2 && inputFrac[0]==inputFrac[1]) {
			result=1+"";
		}
		return result;
	}
	
	
	public static int[] convStrtoInt(String[] input) {
		int[] result = new int[input.length];
		for (int i=0; i <input.length;i++) {
			result[i]=Integer.parseInt(input[i]);
		}
		return result;
	}
	
	
	// Converts a Mixed Fraction into an Improper Fraction
	//TODO make less redundant
	public static int[] toImproperFrac(int[] inputFrac) {
		int[] result = new int[2];
		int x = inputFrac[0];
		int y = inputFrac[1];
		int z = inputFrac[2];
		int numerator =0;
		if(x<0) {
			x*=-1;
			numerator = ((x * z) + y)*-1;
		}else {
			numerator = (x * z) + y;
		}
		result[0]=numerator;
		result[1]=z;
		return result; 
	}
	public static int[] toMixedNum(int[] inputFrac) {
		int[] mixedFracResult= new int[3];
		int modOfFrac = inputFrac[0] % inputFrac[1];
		int wholeNum = inputFrac[0] / inputFrac[1];
		if(modOfFrac<0) {
			modOfFrac*=-1;
		}
		mixedFracResult[0]=wholeNum;
		mixedFracResult[1]=modOfFrac;
		mixedFracResult[2]=inputFrac[1];
		return mixedFracResult;// Converts an Improper Fraction into a Mixed Fractions
	}
	
	public static String addOrSubtractFrac(String fracString1, String fracString2, String operation) {
		int[] frac1= toImproperFrac(convStrtoInt(parseFrac(fracString1)));
		int[] frac2= toImproperFrac(convStrtoInt(parseFrac(fracString2)));
		int[] result = new int[2];
		int mainOper1=(frac1[0]*frac2[1]);
		int mainOper2=-(frac2[0]*frac1[1]);
		if (operation.equals("-")) {
			result[0]=mainOper1+mainOper2;
		} else{
			result[0]=mainOper1-mainOper2;
		}
		result[1]=(frac1[1]*frac2[1]);
		return toString(simplify(result));
	}
	

	
	public static String multOrDivFrac(String fracString1, String fracString2, String operation) {
		int[] frac1= toImproperFrac(convStrtoInt(parseFrac(fracString1)));
		int[] frac2= toImproperFrac(convStrtoInt(parseFrac(fracString2)));
		int[] result = new int[2];
		if (operation.equals("*")) {
			result[0]=frac1[0]*frac2[0];
			result[1]=frac1[1]*frac2[1];
		}else if (operation.equals("/")){
			result[0]=frac1[0]*frac2[1];
			result[1]=frac1[1]*frac2[0];
		}
		return toString(simplify(result));
	}
	
	
	public static boolean isDivisibleBy(int a, int b) {
		if (b == 0) {
			throw new IllegalArgumentException("You can't divide by zero!");
		}
		if (a % b == 0) {
			return true;
		} else {
			return false;// Checks if inputs are divisible to each other
		}
	}
	public static int gcf(int num1, int num2) {
		/*if (isPrime(num1) && isPrime(num2)) {
			return 1;
		}*/
		while (num1 != 0 && num2 != 0) {
			int num3 = num2;
			num2 = num1 % num2;
			num1 = num3;
		}
		return num1 + num2;
	}
	
	public static int[] simplify(int[] fracInput) {
		int[] result = new int[2];
		int divisibleNum = gcf(fracInput[0],fracInput[1]);
		result[0]=fracInput[0]/divisibleNum;
		result[1]=fracInput[1]/divisibleNum;
		
		int absOfNum= 0;
		if (result[0]<0) {
			absOfNum=result[0]*-1;
		}
		if ( result[0]>result[1]||absOfNum>result[1]) {
			int[] resultInMixed= toMixedNum(result);
			if(resultInMixed[2]<0) {
				resultInMixed[2]*=-1;
			}
			return resultInMixed;
		}else {
			return result;
		}
		//return result;
	}
	
	
	


}
