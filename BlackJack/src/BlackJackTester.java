import java.util.ArrayList;


public class BlackJackTester extends BlackJackApp {

	/**
	 * Runs tests on the BlackJack game created by the students. If they pass, the game is launched.
	 * 
	 * @param args
	 */
	public static void main(String [] args) {
		boolean passed;

		passed = testCardClass();
		if(!passed) return;
		System.out.println("The Card class has passed all tests\n");

		passed = testHandClass();
		if(!passed) return;
		System.out.println("The Hand class has passed all tests\n");
		
		passed = testDeckClass();
		if(!passed) return;
		System.out.println("The Deck class has passed all tests\n");
		
		passed = blackJackClass();
		if(!passed) return;
		System.out.println("The BlackJack class has passed all tests\n");
		launch(args);
	}

	/**
	 * Tests the Card class
	 * 
	 * @return boolean true if all the tests pass
	 */
	private static boolean testCardClass() {
		Card.Suit [] suits = Card.Suit.values();
		Card.Type [] types = Card.Type.values();
		int count = 1;
		for(int i = 0; i < suits.length; i++) {
			for(int j = 0; j < types.length; j++) {

				// Expected Values
				Card.Suit expectedSuit = suits[i];
				Card.Type expectedType = types[j];
				String expectedString = expectedType + " of " + expectedSuit + "S";

				Card testCard = new Card(types[j], suits[i]);
				Card.Suit testCardSuit = testCard.getSuit();
				Card.Type testCardType = testCard.getType();
				String testCardString = testCard.toString();


				// Test getSuit()
				if(testCardSuit != expectedSuit) {
					System.out.println("Card did not return expected Suit from the getSuit() method");
					System.out.println("Expected: " + expectedSuit);
					System.out.println("Actual: " + testCardSuit);
					// test has failed, return false
					return false;
				}
				// Test getType()
				if(testCardType != expectedType) {
					System.out.println("Card did not return expected Type from the getType() method");
					System.out.println("Expected: " + expectedType);
					System.out.println("Actual: " + testCardType);
					return false;
				}

				// Test toString()
				if(!testCardString.equalsIgnoreCase(expectedString)) {
					System.out.println("Card did not return expected String from the toString() method");
					System.out.println("Expected: " + expectedString);
					System.out.println("Actual: " + testCardString);
					return false;
				}
			}
		}

		// if all tests have passed
		return true;
	}
	/**
	 * Tests the Hand class
	 * 
	 * @return boolean true if all the tests pass
	 */
	private static boolean testHandClass() {
		Card.Suit [] suits = Card.Suit.values();
		Card.Type [] types = Card.Type.values();

		int testHandSize;
		Card [] testHandCards;
		String testHandString;
		// Create a new hand for purpose of testing
		Hand testHand = new Hand();

		// Test that there are no cards in the hand
		testHandSize = testHand.size();
		if(testHandSize != 0) {
			System.out.println("Hand did not return expected integer from the size() method");
			System.out.println("Expected: " + 0);
			System.out.println("Actual: " + testHandSize);
			return false;
		}
		// Test that the getCards returns a clone of the cards
		testHandCards = testHand.getCards();
		if(testHandCards.length != 0) {
			System.out.println("Hand did not return expected value from the getCards() method");
			System.out.println("Expected: An empty array of Cards with length of zero");
			System.out.println("Actual: A non empty array of Cards with a length of " + testHandCards.length);
			return false;
		}
		testHandString = testHand.toString();
		if(!testHandString.equalsIgnoreCase("Empty hand")) {
			System.out.println("Hand did not return expected value from the toString() method");
			System.out.println("Expected: \"Empty hand\"");
			System.out.println("Actual: " + testHandString);
			return false;
		}
		
		//Make sure that getCards() is not returning a reference to the instance variable
		Card[] hand1 = testHand.getCards();
		Card[] hand2 = testHand.getCards();
		//If hand1 and hand2 store the same memory address, we are not returning a copy of the instance
		//variable cards.  The getCards method should create a new array and copy the contents of cards into
		//this new array.
		if(testHand.getCards() == testHand.getCards())
		{
			System.out.println("The getCards method is not returning a copy of the instance variable cards");
		}
		
		//Keep adding cards to the hand & checking that they are indeed added while ensuring a clone of instead of the reference to the array in the class is returned
		for(int i = 0; i < 5; i++) {
			Card testCard = new Card(types[i], suits[(i % 4)]);
			testHand.addCard(testCard);
			
			if(testHandCards.length != i) {
				System.out.println("Hand did not return expected value from the getCards() method");
				System.out.println("Expected: A clone of the cards in the hand");
				System.out.println("Actual: A reference to the actual cards in the hand.");
				return false;
			}

			testHandCards = testHand.getCards();
			if(testHandCards.length != i+1) {
				System.out.println("Hand did not return expected value from the getCards() method");
				System.out.println("Expected: An array of Cards with a length of " + (i+1));
				System.out.println("Actual: An array of Cards with a length of " + testHandCards.length);
				return false;
			}

			if(!testHandCards[i].equals(testCard)){
				System.out.println("Hand did not return expected value from the getCards() method");
				System.out.println("Expected: An array containing one card ( " + testCard + " )");
				System.out.println("Actual: An array containing the card" + testHandCards[i]);
				return false;
			}
		}
		//Test the emptyHand() method
		testHandCards = testHand.getCards();
		Card [] discardedCards = testHand.emptyHand();
		if(testHandCards.length != discardedCards.length) {
			System.out.println("Hand did not return expected value from the emptyHand() method");
			System.out.println("Expected: An array of cards that were formerly contained in the hand");
			System.out.println("Actual: An array of cards that did not match those formerly contained in the hand");
			System.out.println("HINT: Use a breakpoint in the debugger here");
			return false;
		}
		
		for(int i = 0; i < discardedCards.length; i++) {
			if(!testHandCards[i].equals(discardedCards[i])) {
				System.out.println("Hand did not return expected value from the emptyHand() method");
				System.out.println("Expected: An array of cards that were formerly contained in the hand");
				System.out.println("Actual: An array of cards that did not match those formerly contained in the hand");
				System.out.println("HINT: Use a breakpoint in the debugger here");
				return false;
			}
		}
		
		testHandCards = testHand.getCards();
		if(testHandCards.length != 0) {
			System.out.println("Hand did not actual empty after the call to the emptyHand() method");
			System.out.println("Expected: An empty array");
			System.out.println("Actual: An array of length " + testHandCards.length);
			return false;
		}
		
		return true;
	}

	/**
	 * Tests the Deck class
	 * 
	 * It should be noted this method does not test the shuffle method.
	 * 
	 * @return boolean true if all the tests pass
	 */
	public static boolean testDeckClass() {
		Deck testDeck = new Deck();
		ArrayList<Card> drawnCards = new ArrayList<Card>();
		Card testCard;
		//Draw 52 cards, ensure they are all unique
		for(int i = 0; i < 52; i++) {
			testCard = testDeck.draw();
			if(testCard == null) {
				System.out.println("Deck returned a null value after " + (i+1)  + " calls to the draw() method on a new Deck");
				System.out.println("Expected: A Card");
				System.out.println("Actual: A null value");
				return false;
			}
			for (Card drawnCard : drawnCards) {
				if(drawnCard.equals(testCard)) {
					System.out.println("Deck returned a duplicate card from a call to the draw() method");
					System.out.println("Expected: An unseen Card");
					System.out.println("Actual: A card already drawn");
					return false;
				}
			}
			drawnCards.add(testCard);
		}
		
		//draw a card expecting a null value
		testCard = testDeck.draw();
		if(testCard != null) {
			System.out.println("Deck did not return a null value after 52 draws");
			System.out.println("Expected: An null value");
			System.out.println("Actual: " + testCard);
			return false;
		}
		
		//add all of the cards back to the deck from the bottom.
		
		for (Card drawnCard : drawnCards) {
			testDeck.addToBottom(drawnCard);
		}
		
		//the first card we added to the bottom of the empty deck should be the first we draw
		
		for (Card drawnCard : drawnCards) {

			testCard = testDeck.draw();

			if(!drawnCard.equals(testCard)) {
				System.out.println("Cards were not added back to the deck in the expected order.");
				System.out.println("Expected: " + drawnCard);
				System.out.println("Actual: " + testCard);
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Tests the BlackJack class
	 * 
	 * @return boolean true if all the tests pass
	 */
	private static boolean blackJackClass() {
		Hand h = new Hand();
		h.addCard(new Card(Card.Type.JACK, Card.Suit.SPADE));
		h.addCard(new Card(Card.Type.ACE, Card.Suit.SPADE));
		h.addCard(new Card(Card.Type.TWO, Card.Suit.SPADE));
		h.addCard(new Card(Card.Type.THREE, Card.Suit.SPADE));
		h.addCard(new Card(Card.Type.FOUR, Card.Suit.SPADE));
		if(BlackJack.getValueOfHand(h) != 20)
		{
			System.out.println("The value of the hand is not correct");
			System.out.println("Your calculated value is: " + BlackJack.getValueOfHand(h));
			System.out.println("The actual value is: 20");
			return false;
		}
		return true;
	}
}
