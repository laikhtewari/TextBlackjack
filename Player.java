import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Player {
	private double funds;
	private ArrayList<Card> cards = new ArrayList<Card>();
	private double buyIn;

	public Player(double buyIn) {
		this.funds = buyIn;
		this.buyIn = buyIn;
	}

	public void addCard(Card c) {
		this.cards.add(c);
	}

	//returns true when player hits and false when player stands
	public boolean move() {
		System.out.println();
		Scanner in = new Scanner(System.in);
		System.out.print("Hit or Stand?: ");
		return in.next().equalsIgnoreCase("Hit");
	}

	public void showCards() {
		for (Card c : cards) {
			System.out.print(c + " ");
		}
		System.out.println();
	}

	public int bet() {
		while (true) {
			System.out.print("How much would you like to bet (you have $" + funds + ")?: $");
			Scanner in = new Scanner(System.in);
			try {
				int bet = in.nextInt();
				if (bet <= funds) {
					if (bet >= 10.0 && bet <= 1000.0) {
						return bet;
					} else {
						System.err.println("Minimum bet is $10, maximum bet is $1000");
					}
				} else {
					System.err.println("Invalid bet: exceeds funds");
				}
			} catch (InputMismatchException ime) {
				System.err.println("Invalid bet: must be in whole dollars");
			}
		}
	}

	public int sumCards() {
		int sum = 0;
		int aceCounter = 0;
		for (Card c : cards) {
			if (c.getRank() == 11 || c.getRank() == 12 || c.getRank() == 13) {
				sum += 10;
			} else if (c.getRank() == 1) {
				aceCounter++;
			} else {
				sum += c.getRank();
			}
		}

		for (int i = 0; i < aceCounter; i++) {
			if (sum + 11 <= 21) {
				sum += 11;
			} else {
				sum++;
			}
		}

		return sum;
	}

	public void win(double bet) {
		funds += (1.5 * bet);
	}

	public void lose(double bet) {
		funds -= bet;
	}

	public void clearCards() {
		cards.clear();
	}

	public double getReturn() {
		return this.funds - this.buyIn;
	}
}