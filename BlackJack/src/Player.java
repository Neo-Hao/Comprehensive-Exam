/**
 * A player in a blackJack game
 *
 */
public class Player {
	/**
	 * The players hand
	 */
	private Hand hand;
	
	/**
	 * Instantiates the hand instance variable.
	 */
	public Player() {
		this.hand = new Hand();
	}
	
	/**
	 * @return true if the player has bused
	 */
	public boolean busted() {
		if (BlackJack.getValueOfHand(this.hand) > 21) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * empty the player hand into the cards
	 * @param d deck that receives the cards
	 */
	public void returnCardstoDeck(Deck d) {
		
		Card[] toReturn = this.hand.emptyHand();
		
		for(Card card:toReturn) {
			d.addToBottom(card);
		}
	}
	/**
	 * @return the player's hand
	 */
	public Hand getHand() {
		return this.hand;
	}
}
