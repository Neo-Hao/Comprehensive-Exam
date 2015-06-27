import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
/**
 * "Well Done" Copyright 2013 by Iwan Gabovitch <qubodup.net> multi-licensed under Creative Commons Attribution 3.0 license <http://creativecommons.org/licenses/by/3.0/legalcode> and/or GPLv3+ <http://www.gnu.org/licenses/gpl.html>
 * Cards taken from http://www.jfitz.com/cards
 */
public class BlackJackApp extends Application {

	BlackJack game;
	StackPane root;
	ImageView [] playerCards;
	ImageView [] dealerCards;
	AudioClip cardFlip, clapping;
	ImageView hole;
	ImageView hit, stay, quit;
	Image hitUp, hitDown, stayUp, stayDown;
	int round, playerWins, dealerWins;
	Text dealerWinsText, playerWinsText, playerScoreText, dealerScoreText;
	boolean playerTurn;
	
	@Override
	public void start(Stage primaryStage) {
		game = new BlackJack();
		playerCards = new ImageView [5];
		dealerCards = new ImageView [5];
		dealerWinsText = new Text(0, 0, "");
		dealerWinsText.setFill(Color.WHITE);
		dealerWinsText.setFont(Font.font("Verdana", 20));
		dealerWinsText.setTranslateX(250);
		dealerWinsText.setTranslateY(225);
		playerWinsText = new Text(0, 0, "");
		playerWinsText.setFill(Color.WHITE);
		playerWinsText.setFont(Font.font("Verdana", 20));
		playerWinsText.setTranslateX(250);
		playerWinsText.setTranslateY(190);
		playerScoreText = new Text(0, 0, "");
		playerScoreText.setFill(Color.WHITE);
		playerScoreText.setFont(Font.font("Verdana", 20));
		playerScoreText.setTranslateX(-250);
		playerScoreText.setTranslateY(-190);
		cardFlip = new AudioClip("file:classic-cards/cardPlace1.wav");
		clapping = new AudioClip("file:classic-cards/wellDone.wav");
		//initialize buttons
		hole = new ImageView();
		hole.setTranslateX(100);
		hole.setTranslateY(-100);
		hole.setImage(new Image("file:classic-cards/b2fv.png"));
		hit = new ImageView();
		hitUp = new Image("file:classic-cards/hit.png");
		hitDown = new Image("file:classic-cards/hit-down.png");
		hit.setImage(hitUp);
		hit.setTranslateX(-200);
		hit.setTranslateY(200);
		hit.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				hit.setImage(hitDown);
			}
		});
		hit.setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				hit.setImage(hitUp);
				if(playerTurn)
					playerHit();
			}
		});
		stay = new ImageView();
		stayUp = new Image("file:classic-cards/stay.png");
		stayDown = new Image("file:classic-cards/stay-down.png");
		stay.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				stay.setImage(stayDown);
			}
		});
		stay.setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				stay.setImage(stayUp);
				if(playerTurn)
					playerStay();
			}
		});
		stay.setImage(stayUp);
		stay.setTranslateX(-20);
		stay.setTranslateY(200);

		round = 1;
		root = new StackPane();
		playerTurn = false;

		root.getChildren().add(hit);
		root.getChildren().add(stay);
		root.getChildren().add(dealerWinsText);
		root.getChildren().add(playerWinsText);
		root.getChildren().add(playerScoreText);
		Scene scene = new Scene(root, 800, 500);
		scene.setFill(Color.DARKGREEN);
		primaryStage.setTitle("BlackJack");
		primaryStage.setScene(scene);
		primaryStage.show();
		// start game

		newGame();
		playerTurn = true;
	}

	private void newGame() {
		game.restart();
		playerWinsText.setText("Player wins: " + playerWins);
		dealerWinsText.setText("Dealer wins: " + dealerWins);
		round = 1;
		Card [] pCards = game.getPlayer().getHand().getCards();
		Card [] dCards = game.getDealer().getHand().getCards();
		for(int i = 0; i < pCards.length; i++) {
			playerCards[i] = new ImageView();
			playerCards[i].setImage(pCards[i].getImage());
			playerCards[i].setTranslateX(-200 + i*100);
			playerCards[i].setTranslateY(100);
			root.getChildren().add(playerCards[i]);
			cardFlip.play();
		}

		for(int i = 0; i < dCards.length; i++) {
			dealerCards[i] = new ImageView();
			dealerCards[i].setImage(dCards[i].getImage());
			dealerCards[i].setTranslateX(200 - i*100);
			dealerCards[i].setTranslateY(-100);
			if(i == 0)
				root.getChildren().add(dealerCards[i]);
			else
				root.getChildren().add(hole);
			cardFlip.play();
		}
		playerScoreText.setText("Player score: " + BlackJack.getValueOfHand(game.getPlayer().getHand()));
		playerTurn();
	}
	private void dealerTurn() {
		Card c = game.getDealer().playTurn(game.getDeck());
		if(c != null) {
			dealerCards[round + 1] = new ImageView();
			dealerCards[round + 1].setImage(c.getImage());
			dealerCards[round + 1].setTranslateX(200 - (round + 1)*100);
			dealerCards[round + 1].setTranslateY(-100);
			//root.getChildren().add(dealerCards[round + 1]);
			//cardFlip.play();
		}

	}
	private void playerTurn() {
		playerTurn = true;
	}

	private void endGame(String message) {
		Image chipImg = new Image("file:classic-cards/chip.png");
		ImageView chip = new ImageView();
		chip.setImage(chipImg);
		chip.setScaleX(.33);
		chip.setScaleY(.33);
		chip.setTranslateZ(1);
		root.getChildren().add(chip);
		Text text = new Text(), text2 = new Text();
		if(!message.equals("Tie game!")) {
			text.setFill(Color.WHITE);
			text.setFont(Font.font("Verdana", 20));
			text.setTranslateY(15);
			text.setText(message.substring(6));
			text2.setText(message.substring(0, 6));
			text2.setFill(Color.WHITE);
			text2.setFont(Font.font("Verdana", 20));
			text2.setTranslateY(-10);
		} else {
			text.setFill(Color.WHITE);
			text.setFont(Font.font("Verdana", 20));
			text.setTranslateY(15);
			text.setText(message.substring(4));
			text2.setText(message.substring(0,3));
			text2.setFill(Color.WHITE);
			text2.setFont(Font.font("Verdana", 20));
			text2.setTranslateY(-10);
		}

		root.getChildren().add(text);
		root.getChildren().add(text2);

		clapping.play();
		final Animation animation = new Transition() {
			{
				setCycleDuration(Duration.millis(3000));
			}


			protected void interpolate(double frac) {
				chip.setRotate(1000*frac);
				double percentComplete = frac*100.0;
				if(percentComplete < 20.0) {
					double scaleFactor = (frac*100)/(20.0);
					chip.setScaleX(.33*scaleFactor);
					chip.setScaleY(.33*scaleFactor);
					text.setFont(Font.font("Veradana", (int) 20*scaleFactor));
					text2.setFont(Font.font("Verdana", (int) 20*scaleFactor));
					text.setTranslateY(15*scaleFactor);
					text2.setTranslateY(-10 * scaleFactor);
				}
				else if(percentComplete > 80.0) {
					double scaleFactor = (100 - percentComplete)/20.0;
					chip.setScaleX(.33*scaleFactor);
					chip.setScaleY(.33*scaleFactor);
					text.setFont(Font.font("Veradana", (int) 20*scaleFactor));
					text2.setFont(Font.font("Verdana", (int) 20*scaleFactor));
					text.setTranslateY(15*scaleFactor);
					text2.setTranslateY(-10 * scaleFactor);
				} else {
					chip.setScaleX(.33);
					chip.setScaleY(.33);
					text.setFont(Font.font("Verdana", 20));
					text2.setFont(Font.font("Verdana", 20));
				}
			}
		};
		animation.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				root.getChildren().remove(text);
				root.getChildren().remove(text2);
				root.getChildren().remove(hole);
				root.getChildren().remove(chip);
				root.getChildren().remove(dealerScoreText);
				clearTable();
				newGame();
			}
		});
		animation.play();

	}
	private void playerHit() {
		playerTurn = false;
		Card c = game.getDeck().draw();
		game.getPlayer().getHand().addCard(c);
		int i = round + 1;
		playerCards[i] = new ImageView();
		playerCards[i].setImage(c.getImage());
		playerCards[i].setTranslateX(-200 + (i)*100);
		playerCards[i].setTranslateY(100);
		root.getChildren().add(playerCards[i]);
		cardFlip.play();
		playerScoreText.setText("Player score: " + BlackJack.getValueOfHand(game.getPlayer().getHand()));
		if(game.getPlayer().busted()) {
			dealerWins++;
			endGame("Dealer wins!");
		} else {
			dealerTurn();
			if(playerTurn) {
				return;
			}
			else if(round == 3) {
				dealerShow();
				endGame(decideWinner());
			} else {
				round++;
				playerTurn = true;
			}
		}
	}
	private void dealerShow() {
		root.getChildren().remove(hole);
		Text text = new Text(0, 0, "Dealer score: " + BlackJack.getValueOfHand(game.getDealer().getHand()));
		text.setFill(Color.WHITE);
		text.setFont(Font.font("Verdana", 20));
		text.setTranslateX(-50);
		text.setTranslateY(-190);
		root.getChildren().add(text);
		dealerScoreText = text;
		for(int i = 1; i < game.getDealer().getHand().size(); i++) {
			root.getChildren().add(dealerCards[i]);
			cardFlip.play();
		}
	}
	private void playerStay() {
		playerTurn = false;
		while(!playerTurn) {
			dealerTurn();
			if(game.getDealer().busted()) {
				dealerShow();
				playerWins++;
				endGame("Player wins!");
				return;
			}
			else if(round == 3) {
				dealerShow();
				endGame(decideWinner());
				return;
			} else {
				round++;
			}
		}
	}
	private String decideWinner() {
		if(BlackJack.getValueOfHand(game.getPlayer().getHand()) == 
				BlackJack.getValueOfHand(game.getDealer().getHand())) {
			return "Tie game!";
		} else if (BlackJack.getValueOfHand(game.getPlayer().getHand()) >
		BlackJack.getValueOfHand(game.getDealer().getHand())) {
			playerWins++;
			return "Player wins!";
		} else {
			dealerWins++;
			return "Dealer wins!";
		}
	}
	private void clearTable() {
		//PAUSES GAME FOR 3 seconds
		try
		{
			Thread.sleep(3000);
		}
		catch(InterruptedException e)
		{
			
		}
		
		for(ImageView card : playerCards) {
			root.getChildren().remove(card);
		}
		for(ImageView card : dealerCards) {
			root.getChildren().remove(card);
		}
	}
	public static void main(String [] args) {
		BlackJackApp.launch(args);
	}
}
