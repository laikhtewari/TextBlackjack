import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Game {
	public static void main(String[] args) {
		instructions();

		Deck deck;
		double buyIn = getBuyIn();
		Player player = new Player(buyIn);
		Dealer dealer = new Dealer();

		boolean done = false;
		while (!done) {
			deck = new Deck();
			player.clearCards();
			dealer.clearCards();

			int bet = player.bet();

			dealer.addCard(deck.dealCard());
			player.addCard(deck.dealCard());
			dealer.addCard(deck.dealCard());
			player.addCard(deck.dealCard());

			System.out.print("Dealer's cards: ");
			dealer.showFirst();
			System.out.print("Your cards: ");
			player.showCards();
			System.out.print("Your current sum: " + player.sumCards());

			boolean bust = false;
			while(!bust && player.move()) {
				player.addCard(deck.dealCard());
				System.out.print("Your cards: ");
				player.showCards();
				System.out.print("Your current sum: " + player.sumCards());
				if (player.sumCards() > 21) {
					bust = true;
					System.out.println("Bust! You lose $" + ((double)bet) + ".\n");
					player.lose(bet);
				}
			}

			if (!bust) {
				while (dealer.move()) {
					dealer.addCard(deck.dealCard());
				}

				System.out.print("Dealer's cards: ");
				dealer.showCards();
				System.out.print("Your cards: ");
				player.showCards();

				if(player.sumCards() == 21) {
					if (dealer.sumCards() == 21) {
						System.out.println("Both you and the dealer have blackjack.\n");
					} else {
						System.out.println("You win $" + (1.5 * (double)bet) + "!\n");
						player.win((double)bet);
					}
				} else {
					if (dealer.sumCards() > 21) {
						System.out.println("You win $" + ((double)bet) + "!\n");
						player.win((double)bet);
					} else {
						if (player.sumCards() > dealer.sumCards()) {
							System.out.println("You win $" + ((double)bet) + "!\n");
							player.win((double)bet);
						} else if (player.sumCards() == dealer.sumCards()) {
							System.out.println("Tie.\n");
						} else {
							System.out.println("Dealer wins. You lose $" + ((double)bet) + ".\n");
							player.lose((double)bet);
						}
					}
				}
			}

			System.out.print("Would you like to play again (y/n)?: ");
			Scanner in = new Scanner(System.in);
			if (in.next().charAt(0) == 'n') {
				done = true;
				System.out.println("Thanks for playing! Your return is $" + player.getReturn());

			}
		}
	}

	public static double getBuyIn() {
		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.print("How much would you like to buy in for (minimum $100)?: $");
			try {
				double buyIn = in.nextDouble();
				if (buyIn < 100.0) {
					System.err.println("Minimum buy-in is $100");
					in.nextLine();
				} else {
					return buyIn;
				}
			} catch (InputMismatchException ime) {
				System.err.println("Buy-in must be a number");
			}
		}
	}

	public static void instructions() {
		System.out.print("==========Welcome to Blackjack!==========\n\nWelcome to a text-based blackjack game.\nFollow the prompts to play the game.\nCards will be displayed in the form of <rank>_<suit>, so 13_H is the King of Hearts while 1_S is the Ace of Spades.\nLet's get started!\n\n");
	}
}