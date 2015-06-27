
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Class representing a standard 52 card deck. Allows users to shuffle, draw,
 * and add cards to the bottom for the purpose of playing various card games.
 */
public class Deck {
	
	/**
	 * Cards in the deck
	 */
	private Card [] cards;
	
	/**
	 * The number of cards in the physical deck on the table.
	 */
	public int numCardsInDeck;
	
	/**
	 * Create a standard 52 card deck w/ one of each type (Ace, King, Queen...) for each Suit (Hearts, Spades ..)
	 */
	public Deck() {
		
		Card.Suit [] suits = Card.Suit.values(); //creates an array of all of the Suits
		Card.Type [] types = Card.Type.values(); //creates an array of all of the Types
		cards = new Card[suits.length*types.length]; //sets the size of cards to 52 (types & suits)
		numCardsInDeck = 0;
		
		for(int i =0; i<suits.length; i++)
		{
			for(int j = 0; j<types.length; j++)
			{
				//Assigns 52 unique cards to the cards deck
				cards[numCardsInDeck] = new Card(types[j], suits[i]); 
				numCardsInDeck++; //increments the number of cards in the deck
			}
		}	
	}
	/**
	 * Draws card from the top
	 * @return the top card
	 */
	public Card draw() {
		if (numCardsInDeck == 0) {
			System.out.println("No card in the deck.");
			return null;
		} else {
			numCardsInDeck--;
			return cards[numCardsInDeck];
		}
		
		
	}

	/**
	 * Swaps card at a with card at b
	 * @param a card to be swapped with b
	 * @param b card to be swapped with a
	 */
	private void swap(int a, int b) {
		Card temp = cards[a];
		cards[a] = cards[b];
		cards[b] = temp;
	}
	
	/**
	 * Shifts all the cards in the deck right by one place
	 */
	private void shiftRightOne() {
		if (numCardsInDeck == 52) {
			System.out.println("The deck is full.");
		} else {
			numCardsInDeck++;
			for (int i = numCardsInDeck - 1; i > 0; i--) {
				cards[i] = cards[i-1];
			}
		}
	}	
	/**
	 * Add c to the bottom of the deck
	 * Print "Deck Full" when count equals the size of the deck
	 * @param c card to add
	 */	
	public void addToBottom(Card c) {
		if (numCardsInDeck == 52) {
			System.out.println("The deck is full.");
		} else if (numCardsInDeck == 0) {
			numCardsInDeck++;
			cards[0] = c;
		} else {
			shiftRightOne();
			cards[0] = c;
		}
 	}

	/**
	 * Shuffles the deck by swapping the cards 100 times
	 */
	public void shuffle() {
				
		for (int i = 0; i < 100; i++) {
			int target1 = randInt(0, numCardsInDeck - 1);
			int target2 = randInt(0, numCardsInDeck - 1);
			if (target1 != target2) {
				swap(target1, target2);
			} else {
				target2 = randInt(0, numCardsInDeck - 1);
				swap(target1, target2);
			}
		}
	}
	/**
	 * Shuffles the deck by swapping cards n times
	 * @param n number of swaps
	 */
	public void shuffle(int n) {
		
		for (int i = 0; i < n; i++) {
			int target1 = randInt(0, numCardsInDeck - 1);
			int target2 = randInt(0, numCardsInDeck - 1);
			if (target1 != target2) {
				swap(target1, target2);
			} else {
				target2 = randInt(0, numCardsInDeck - 1);
				swap(target1, target2);
			}
		}
	}
	
	private int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

}
