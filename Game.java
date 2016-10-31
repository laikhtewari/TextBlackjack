import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Game {
	public static void main(String[] args) {
		Deck deck;

		Player player = new Player();
		Dealer dealer = new Dealer();

		boolean done = false;
		while (!done) {
			deck = new Deck();

			int bet = player.bet();

			dealer.addCard(deck.dealCard());
			player.addCard(deck.dealCard());
			dealer.addCard(deck.dealCard());
			player.addCard(deck.dealCard());

			dealer.showFirst();
			player.showCards();

			boolean bust = false;
			while(!bust && player.move()) {
				player.addCard(deck.dealCard());
				player.showCards();
				System.out.println("SUM: " + player.sumCards());
				if (player.sumCards() > 21) {
					bust = true;
					System.out.println("Bust!");
				}
			}

			if (!bust) {
				dealer.move();

				if (dealer.sumCards() > 21) {
					//player wins
				} else if (dealer.sumCards() == 21) {
					if (player.sumCards() == 21) {
						//push
					} else {
						//player loses
					}
				} else {
					if (player.sumCards() > dealer.sumCards()) {
						//player wins
					} else if (player.sumCards() == dealer.sumCards()) {
						//push
					} else {
						//player loses
					}
				}
			}
		}
	}

	public static int getBuyIn() {
		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.print("How much would you like to buy in for (minimum $100)?: $")
			try {
				double bet = in.nextDouble();
				if (bet < 100.0) {
					System.err.println("Minimum buy-in is $100")
				} else {
					return bet;
				}
			} catch (InputMismatchException ime) {
				System.err.println("Buy-in must be a number")
			}
		}
	}
}