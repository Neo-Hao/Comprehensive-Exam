
/**
 * 
 * @author ghyzel
 *
 */
public class Hand {
	
	/**
	 * The cards in the hand
	 */
	private Card [] cards;
	
	/**
	 * Creates an empty hand
	 */
	public Hand() {
		//Initializing an empty array. 
		//Calling cards.length on this array would return 0
		cards = new Card[0];		
	}

	/**
	 * Adds Card c to the hand
	 * 
	 * @param c card to be added
	 */
	public void addCard(Card c) {
		Card[] newCards = new Card[cards.length + 1];
		
		for (int i = 0; i < cards.length; i++) {
			newCards[i] = cards[i];
		}
		
		newCards[cards.length] = c;
		
		cards = newCards;
	}
	
	/**
	 * @return number of cards in the hand
	 */
	public int size() {
		return cards.length;
	}
	
	/**
	 * Returns an array of all the cards in the hand
	 * 
	 * @return the cards in the hand
	 */
	public Card[] getCards() {
		// Ensure you return reference to the copy of the cards array
		// and not a reference actual cards array!
		Card[] cardsToReturn = new Card[cards.length];
		
		for (int i = 0; i < cards.length; i++) {
			cardsToReturn[i] = cards[i];
		}
		
		return cardsToReturn;
	}
	
	/**
	 * Empties the hand, and returns an array containing the discarded cards.
	 * 
	 * @return the discarded cards
	 */
	public Card[] emptyHand() {
		Card[] toDiscard = cards;
		cards = new Card[0];
		return toDiscard;
	}
	
	/**
	 * Returns a String representation of the hand
	 * 
	 * E.g.
	 * 
	 * "Empty Hand"
	 * "1. ACE OF SPADES\n2. QUEEN OF HEARTS"
	 * 
	 * @return a String representing the hand
	 */
	@Override
	public String toString() {
		// HINT: Use the toString() method of the card class
		
		if (cards.length == 0) {
			return "Empty hand";
		}
		
		String output = "";
		
		for (int i = 1; i <= cards.length; i++) {
			output += i + ". " + cards[i-1].toString() + "\n";
		}
		
		return output;
	}
}
