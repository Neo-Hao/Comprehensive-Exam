import java.util.Scanner;


public class User{
	
	// ask user whether to play
	public static boolean whetherPlay() {
		while(true) {
			System.out.println("Would you like to play again? Yes(y) or No(n)");
			Scanner keyboard = new Scanner(System.in);
			String input = keyboard.nextLine().toLowerCase();
			
			try {
				if (input.equals("yes") || input.equals("y")) {
					keyboard.close();
					return true;
				} else if (input.equals("no") || input.equals("n")) {
					keyboard.close();
					return false;
				} else {
					System.out.println("Your input is invalid.");
				}
			} catch (NumberFormatException nFE){
				System.out.println("Your input is invalid.");
			}
			
		}
	}	
	
	public static char difficulty() {
		
		char level;
		
		while(true) {
			System.out.println("Enter your difficulty level:");
			Scanner keyboard = new Scanner(System.in);
			String input = keyboard.nextLine().toLowerCase();
			
			try {
				if (validation(input) == true) {
					level = getLevel(input);
					return level;
				} else {
					System.out.println("Your input is invalid. Please re-enter your difficulty level:");
				}
			} catch (NumberFormatException nFE){
				System.out.println("Your input is invalid. Please re-enter your difficulty level:");
			}
			
		}
	}
	
	// validate the input
	private static boolean validation(String str) {
		if (str.equals("e") || str.equals("i") || str.equals("h")) {
			return true;
		}
		
		if (str.equals("easy") || str.equals("intermediate") || str.equals("hard")) {
			return true;
		}
		
		int i;
		for(i = 0; i <= str.length() - 1; i++) {
			if (!(str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'h')) {
				return false;
			}
		}
		if (i == str.length()) {
			return true;
		}
		
		return false;
	}
	
	// return the difficulty level
	// assumes the validation is already done
	public static char getLevel(String str) {
		return str.charAt(0);
	}
}