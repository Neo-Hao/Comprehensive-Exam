import java.util.Scanner;


public class Hangman2 {
	
	public static void main(String[] args) {
		// variables
		boolean whetherPlay, whetherWin;
		char difficulty;
		String theWord;
		
		// initiate the game
		whetherPlay = true;

		while (whetherPlay == true) {
			// ask users to give difficulty level
			difficulty = User.difficulty();
			// initialize words
			Game.initializeWords();
			
			// play one round
			whetherWin = Game.whetherWin(difficulty);

			if (whetherWin) {
				System.out.println("Congratulations, yon won.\n");
			} else {
				System.out.println("Sorry, you lost.\n");
			}

			// game ends for this round; re-ask whether the user wants a new round
			whetherPlay = User.whetherPlay();
		} 

		System.out.println("The game has terminated. Goodbye.\n");
		
	}

}