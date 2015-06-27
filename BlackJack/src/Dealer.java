
/**
 * The Dealer in your game of BlackJack. Draws until he/she gets 17 points or has 5 cards.
 *
 */
public class Dealer {
	/**
	 * The dealers hand
	 */
	Hand hand;
	
	/**
	 * Construct a new dealer with an empty hand
	 */
	public Dealer() {
		this.hand = new Hand();
	}
	
	/**
	 * Dealer draws a card if his hand is worth less than 17 points and has less than 5 cards in in his hand.
	 * 
	 * @param deck
	 * @return
	 */
	public Card playTurn(Deck deck) {
		if (this.isDone() != true) {
			Card theCard  = deck.draw();
			this.hand.addCard(theCard);
			return theCard;
		}
		
		return null;
	}
	
	/**
	 * A method to check if the dealer has busted
	 * @return boolean true if the dealer has busted
	 */
	public boolean busted() {
		if (BlackJack.getValueOfHand(this.hand) > 21) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * A method to check if the dealer will draw a card.
	 * @return
	 */
	public boolean isDone() {
		if (BlackJack.getValueOfHand(this.hand) >= 17 && this.hand.size() >= 5) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the dealers cards to the deck
	 * @param d Deck to return the cards to
	 */
	public void returnCardstoDeck(Deck d) {
		Card[] toReturn = this.hand.emptyHand();
		
		for(Card card:toReturn) {
			d.addToBottom(card);
		}
	}
	/**
	 * @return Hand the dealer's hand
	 */
	public Hand getHand() {
		return this.hand;
	}
}
