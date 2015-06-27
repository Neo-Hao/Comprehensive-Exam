
/**
 * Class representing a single player blackjack game
 */
public class BlackJack {
	
	private Deck deck;
	private Dealer dealer;
	private Player player;


	/**
	 * Constructs and prepares for a new game of BlackJack.
	 * Creates player, dealer and deck objects then shuffles
	 * the deck and gives both the dealer and player two cards.
	 */
	public BlackJack() {
		player = new Player();
		dealer = new Dealer();
		
		deck = new Deck();
		deck.shuffle();
		
		Card temp = dealer.playTurn(deck);
		temp = dealer.playTurn(deck);
		
		temp = deck.draw();
		player.getHand().addCard(temp);
		temp = deck.draw();
		player.getHand().addCard(temp);
	}
	/**
	 * Restarts in a few steps
	 * 1. The Player and dealer return their cards to the deck.
	 * 2. The deck is shuffled.
	 * 3. Both the player and the dealer receive two cards drawn form the top of the deck.
	 */
	public void restart() {
		dealer.returnCardstoDeck(deck);
		player.returnCardstoDeck(deck);
		
		deck.shuffle();
		
		Card temp = dealer.playTurn(deck);
		temp = dealer.playTurn(deck);
		
		temp = deck.draw();
		player.getHand().addCard(temp);
		temp = deck.draw();
		player.getHand().addCard(temp);
	}
	/**
	 * Returns the value of a card in a standard game of Blackjack based on the type of the card
	 * ex. An Ace would return 1, a 2 would return 2... 
	 * @param c card whose value is extracted
	 * @return value of the card
	 */
	public static int getValueOfCard(Card c) {
		if (c.getType().equals(Card.Type.JACK) || c.getType().equals(Card.Type.QUEEN) || c.getType().equals(Card.Type.KING)) {
			return 10;
		}
		if (c.getType().equals(Card.Type.ACE)) {
			return 1;
		}
		if (c.getType().equals(Card.Type.TWO)) {
			return 2;
		}
		if (c.getType().equals(Card.Type.THREE)) {
			return 3;
		}
		if (c.getType().equals(Card.Type.FOUR)) {
			return 4;
		}
		if (c.getType().equals(Card.Type.FIVE)) {
			return 5;
		}
		if (c.getType().equals(Card.Type.SIX)) {
			return 6;
		}
		if (c.getType().equals(Card.Type.SEVEN)) {
			return 7;
		}
		if (c.getType().equals(Card.Type.EIGHT)) {
			return 8;
		}
		if (c.getType().equals(Card.Type.NINE)) {
			return 9;
		}
		
		return -1;
	}
	/**
	 * Returns the maximum value of the hand that does not result in a bust
	 * @param h Hand whose value is returned
	 * @return value of h
	 */
	public static int getValueOfHand(Hand h) {
		int sum = 0, numberOfAce = 0;
		
		Card[] cards = h.getCards();
		
		for (Card card: cards) {
			if (card.getType().equals(Card.Type.ACE)) {
				numberOfAce++;
			}
			sum += getValueOfCard(card);
		}
		
		if (numberOfAce == 0) {
			return sum;
		}
		
		if ((sum + 10 <= 21) && (numberOfAce > 0)) {
			sum += 10;
		}
		
		return sum;
	}

	/**
	 * @return Deck used to play
	 */
	public Deck getDeck() {
		return deck;
	}
	
	/**
	 * @return Dealer of the game
	 */
	public Dealer getDealer() {
		return dealer;
	}
	
	/**
	 * @return Player playing the blackjack game
	 */
	public Player getPlayer() {
		return player;
	}

}
