import java.util.Scanner;

class StarGraph {

	public static void main(String[] args) {
		// get input
		int numberX = numberOfXToProcess();
		Double minX = inputNumberValidator();
		Double incrementX = incrementValidator();
		
//		prepare an array to store integer part of y
		int[] starnum = new int[numberX];
		
		// print x and y line by line
		System.out.println("\nValues: ");
		double x = minX;
		for (int i=0; i <= (numberX-1); i++) {
			double y =calculateY(x);
			printXY(x, y);
			// store y
			starnum[i] = (int)Math.floor(y);
			x += incrementX;
		}
		
//		print out the graph		
		System.out.println("\nGraphs: ");
		for (int i:starnum) {
			printALineOfStar(i);
		}
	}

/* User Input Validation */
//	user input validation - for number of x to process
	public static int numberOfXToProcess() {
		String numberOfXString;
		int numberOfX;
		while(true) {
			//	listen to input and save the values
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Please enter the number of x values to process:");
			numberOfXString = keyboard.nextLine();
			
			try{
				// convert the string to integer
				numberOfX = Integer.parseInt(numberOfXString);
				// return the int if it's bigger than or equal to 0
				if (numberOfX > 0) {
					return numberOfX;
				} else {
					System.out.println("Your input has to be an integer bigger than 0. Try again.");
				}
				
			} catch(NumberFormatException nFE) {
				System.out.println("Your input has to be an integer bigger than 0. Try again.");
			}
		}
	}
	
//	user input validation - for min x 
	public static Double inputNumberValidator() {
		String numberBeforeProcess;
		Double xmin;
		while(true) {
			//	listen to input and save the values
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Enter a minimum value for x: ");
			numberBeforeProcess = keyboard.nextLine();
			
			try{
				// convert the string to double
				xmin = Double.parseDouble(numberBeforeProcess);
				// return the number 
				return xmin;
				
			} catch(NumberFormatException nFE) {
				System.out.println("The minimum value for x has to be a number.");
			}
		}
	}

//	user input validation - for increment of x
	public static Double incrementValidator() {
		String numberBeforeProcess;
		Double xincrement;
		while(true) {
			//	listen to input and save the values
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Enter the amount to increment x: ");
			numberBeforeProcess = keyboard.nextLine();
			
			try{
				// convert the string to double
				xincrement = Double.parseDouble(numberBeforeProcess);
				// return if bigger than or equal to 0
				if (xincrement > 0) {
					return xincrement;
				} else {
					System.out.println("Your input has to be an number bigger than 0. Try again.");
				}
				
			} catch(NumberFormatException nFE) {
				System.out.println("Your input has to be an number bigger than 0. Try again.");
			}
		}
	}

/* Print x and y */
	private static double calculateY(double x) {
		double y = 20.0 * Math.abs(Math.sin(x));
		return y;
	}
	
	private static void printXY(double x, double y) {
		System.out.printf("x: %10.3f, y:%10.3f\n", x, y);
	}

/* Print a line of star */
	private static void printALineOfStar(int y) {
		System.out.print(": ");
		for(int i= 1; i<= y; i++) {
			System.out.print("*");
		}
		System.out.print("\n");
	}
}