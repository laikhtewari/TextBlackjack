import java.util.ArrayList;

public class Dealer {
	private ArrayList<Card> cards = new ArrayList<Card>();

	public void addCard(Card c) {
		this.cards.add(c);
	}

	public void showFirst() {
		System.out.println(cards.get(0) + " X_X");
	}

	public boolean move() {
		return sumCards() < 17;
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

	public void showCards() {
		for (Card c : cards) {
			System.out.print(c + " ");
		}
		System.out.println();
	}

	public void clearCards() {
		cards.clear();
	}
}