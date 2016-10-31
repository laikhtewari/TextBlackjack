import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> cards = new ArrayList<Card>(52);

	public Deck() {
		String suits = "HCSD";
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j <= 13; j++) {
				String name = j + "_" + suits.charAt(i);
				cards.add(new Card(name));
			}
		}
		shuffle();
	}

	public void shuffle() {
		for (int i = cards.size() - 1; i > 0; i--) {
			int rand = (int)(Math.random() * cards.size());
			Card temp = cards.get(i);
			cards.set(i, cards.get(rand));
			cards.set(rand, temp);
		}	
	}

	public ArrayList<Card> getCards() {
		return this.cards;
	}

	public Card dealCard() {
		if (cards.size() > 0) {
			Card temp = cards.get(0);
			cards.remove(0);
			return temp;
		} else {
			return null;
		}
	}
}