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
		
		/*
		 * Algorithm/Description:
		 * 
		 * This splits the input by the spaces and loops through
		 * each element until it finds one of the operations (i.e +, -, *, /)
		 * if it finds one of them then it will do the operation of the element previous to the operation and after the operation
		 * and replaces the element after the operation with the  result of the calculation
		 * 
		 * it will keep repeating this until it reaches the last element and it wil return the last element
		 * this is universal for as many operations you would like
		 */
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
		
		
	}
	
	
	/*ALGORITHM/DESCRIPTION:
	 * 
	 * @param: String input
	 * @return: String[]
	 * 
	 * this method parses the appropriate String (i.e. 1_3/4) into an array
	 * the purpose of converting each element into an array is so it is easier to deal with
	 * whole numbers, numerators, and denominators individually.
	 * 
	 * -the method checks if there is an "_"
	 * -if so then the split the string appropriately into the following format of the result array:
	 * 
	 * 						[wholeNumber,Numerator,Denominator]
	 * 
	 * this format is the universal setup for all the fractions calculated in this class
	 * -else if the "_" does not exist then the check where the "/" is and split the string accordingly
	 * 	with the whole number set to 0
	 * 			-else if it's only a whole number set the whole number as the input
	 * 				and set the numerator as "0" and the denominator as "1"
	 * 
	 * then return the result
	 */
	
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
	
	
	/*ALGORITHM/DESCRIPTION
	 * 
	 * @param: int[] inputFrac
	 * @return: String
	 * 
	 * this method converts an integer array to the appropriate answer in the form of a string
	 * 
	 * this method NEEDS to check the LENGTH of the array b/c when simplifying there are certain exception
	 * to fractions for example, I could have returned [0,3,4] for any fractions without a whole number
	 * however, it would be smoother if I sometimes have an array that just return the numerator or denominator
	 * i.e [3,4] so that the program does not return the 0 with the string too.(Yes I have tried this and didn't work too well)
	 * 
	 * We check if the length of the input is 3 and if it is then form the input result variable into the the appropriate String
	 * if the length is 2 and the denominator is 1 then just return the numerator
	 * if the length is 2 in general then return the numerator over the denominator
	 * 
	 * exceptional conditionals:
	 * if the array length is 2 and the formed string is 0/1 or the numerator is 0 then return 0
	 * if the length is 3 and the numerator is 0 just return the whole number
	 * if the length is 3 and numerator equals the denominator then just return 1 
	 * 
	 */
	
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
	
	
	/*ALGORITHM/DESCRIPTION
	 * 
	 * @param: Stirng[] inputFrac
	 * @return: int[]
	 * 
	 * converts a string array into a int array
	 * 
	 * the method takes each element of the String array and converts
	 * the element into an integer and stores it into a new integer array
	 * 
	 * to convert a string to an integer use Integer.parseInt(String)
	 * 
	 */
	
	
	public static int[] convStrtoInt(String[] input) {
		int[] result = new int[input.length];
		for (int i=0; i <input.length;i++) {
			result[i]=Integer.parseInt(input[i]);
		}
		return result;
	}
	
	
	/*ALGORITHM/DESCRIPTION
	 * Converts a Mixed Fraction into an Improper Fraction
	 * 
	 * @param: int[] inputFrac
	 * @return: int[]
	 * 
	 * this method convert the mixed input frac of a mixed number array
	 * into an array that has a length of 2 and stores the frac into the following form:
	 * 
	 * 				[wholeNum,numerator,denominator]->[numerator,denominator]
	 * 
	 * the method creates a result array with the length of 2
	 * and stores each element of input array into the following:
	 * 		x=wholeNum  y=Numerator  z=Denominator
	 * 
	 * the program then sets the numerator by multiplying x and z and then adding the y
	 * if the whole num is a negative make it a positive and then do the operations above and conv back into a negative
	 * the denominator stays the same 
	 * return the formed improper fraction
	 */
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
	
	
	
	/*ALGORTHM/DESCRIPTION
	 * 
	 * @param: int[] inputFrac
	 * @return: int[]
	 * 
	 * converts an improper fraction into a mixed fraction
	 * 
	 * this uses the modulo of the fraction to create the numerator
	 * the whole number would be the  numerator/denominator
	 * the denominator stays the same and is returned in an array with the length of 3
	 */
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
		return mixedFracResult;
	}
	
	
	/*ALGORITHM/DESCRIPTION
	 * 
	 * @param:String fracString1, String fracString2, String operation (arrays are always length 2)
	 * @return String
	 * 
	 * this method adds or subtracts the fraction using the well-known and famous BOWTIE METHOD
	 * 
	 * the methods converts the string fraction input in an improper fraction
	 * the main operations are stored in two corresponding variables
	 * the method checks what operation is inputed and subtracts or adds the corresponding operations
	 * 
	 * in the end the method returns a simplified string
	 * 
	 */
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
	
	/*ALGORITHM/DESCRIPTION
	 * 
	 * @param: String fracString1, String fracString2, String operation (arrays are always length 2)
	 * @return: String
	 * 
	 * the method multiplies the numerators and denominators of the first frac to the 
	 * numerators and denominators of the second frac in this order
	 * 
	 * to divide the method multiplies the numerators and denominators of the first frac to the 
	 * denominator and numerator of the second frac in this order
	 * 
	 * the method converts the input fracs into improper form and does the above algorithms^ depending on
	 * what the specified operation is
	 * 
	 * the method them returns the answer in a simplified string form
	 * 
 	 * 
	 */
	
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
	
	
	/*ALGORITHN/DESCRIPTION
	 * 
	 * @param: int num1, int num2
	 * @returnL int
	 * 
	 * the method find the gcf using the Euclid's method
	 * 
	 * 
	 * the method uses a form of recursion until the second number reached 0;
	 */
	public static int gcf(int num1, int num2) {
		if(num2==0) {
			return num1;
		}
		return gcf(num2,num1%num2);
	}
	
	
	/*ALGORITHM/DESCRIPTION
	 * 
	 * @param: int[] fracInput
	 * @return: int[]
	 * 
	 * 
	 * the method find the gcf of the improper fraction and divides the numerator and denominator
	 * by the gcf
	 * 
	 * the method also converts the answer into a mixed fraction array if needed
	 * 
	 * the method find the gcf of the fraction and divides accordingly to the numerator and denominator
	 * we then check if the abs value of the numerator is greater than the denominator if so then convert the
	 * improper to a mixed
	 * 	-if the denominator of the mixed frac is negative then make it a positive
	 * then return the mixed Frac if possible else return the improper frac
	 */
	
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
	}
	
	
	


}
