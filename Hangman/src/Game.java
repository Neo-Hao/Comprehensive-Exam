import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
	public static String theWord;
	public static String theUpdatedWord;
	
	// initialize the words
	public static void initializeWords() {
		theWord = RandomWord.newWord();
		theUpdatedWord = Game.updatedWord(theWord);
		
		System.out.println(theWord);
		System.out.println(theUpdatedWord);
	}
	
	// determine whether the user wins the round
	public static boolean whetherWin(char difficulty) {
		int numberOfGuess, numberOfSpaces;
		char guess;
		
		// decide the number of guesses and number of digits
		if (difficulty == 'e') {
			numberOfGuess = 15;
			numberOfSpaces = 4;
		} else if (difficulty == 'i') {
			numberOfGuess = 12;
			numberOfSpaces = 3;
		} else {
			numberOfGuess = 10;
			numberOfSpaces = 2;
		}
		
		int[] spaces = new int[numberOfSpaces];
		
		// play
		for (int i = numberOfGuess; i >= 1; i--) {
			// get a valid guess
			guess = validGuess();
			spaces = validSpaces(numberOfSpaces);
			// determine whether it is guessed out
			if (whetherCorrectGuess(guess, i)) {
				// do more than return true
				
				return true;
			}
		}

		return false;
	}
	
	
	// determine whether the guess is valid
	public static char validGuess() {
		while (true) {
			System.out.println("Please enter the letter you want to check:");
			Scanner keyboard = new Scanner(System.in);
			String input = keyboard.nextLine().toLowerCase();
			
			try {
				
				if (input.length() == 1 && Character.isLetter(input.charAt(0))){
					return input.charAt(0);
				} else {
					System.out.println("Your input is invalid. Try again");
				}

			} catch (NumberFormatException nFE){
				System.out.println("Your input is invalid. Try again");
	        }
		}
	}
	
	// determine whether the given spaces are valid
	public static int[] validSpaces(int numberOfSpaces) {
		int[] correctSpaces = new int[numberOfSpaces];
		
		while (true) {
			System.out.println("Please enter the spaces you want to check (separated by spaces):");
			Scanner keyboard = new Scanner(System.in);
			String input = keyboard.nextLine();
						
			try {
				String[] spaces = input.trim().split(" ");
				
				if (spaces.length == numberOfSpaces) {
					for (int i = 0; i < spaces.length; i++) {
						int number = Integer.parseInt(spaces[i]);
						correctSpaces[i] = number;
					}
					
					return correctSpaces;
				}
				
				System.out.println("Your input is invalid. Try again");
				
			} catch (NumberFormatException nFE){
				System.out.println("Your input is invalid. Try again");
	        }
		}
	}
	
	// determine whether a word is correctly guessed out
	public static boolean whetherCorrectGuess(char guess, int remaining) {
		int totalSpaces = theWord.length() - 1;
		
		boolean flag = false;
		for (int i = 0; i <= totalSpaces; i++) {
			if (theWord.charAt(i) == guess) {
				theUpdatedWord = replaceCharAt(theUpdatedWord, i, guess);
				flag = true;
			} 
		}
		
		// output info
		if (flag) {
			System.out.println("Your guess is in the word");
			System.out.println("The updated word is: " + theUpdatedWord);
		} else {
			System.out.println("Your guess is not in the word");
			System.out.println("The word is: " + theWord);
			System.out.println("The updated word is: " + theUpdatedWord);
		}
		
		if (theUpdatedWord.equals(theWord)) {
			return true;
		} else {
			System.out.println("Guess remaining: " + (remaining - 1));
			return false;
		}
	}
	
	// pre-process the updated word
	public static String updatedWord(String theWord) {
		String theUpdatedWord = "";
		int wordLegnth = theWord.length();
		for (int i = 0; i < wordLegnth; i++) {
			theUpdatedWord += '-';
		}
		return theUpdatedWord;
	}
	
	// replace certain positions of a string
	public static String replaceCharAt(String s, int pos, char c) {
		   return s.substring(0, pos) + c + s.substring(pos + 1);
	}
	
}