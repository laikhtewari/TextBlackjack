import java.lang.NumberFormatException;

public class Card {
	String name; //formatted as <rank>_<suit> i.e. 13_H = King of Hearts, 4_C = 4 of Clubs
	int rank;
	char suit;

	public Card(String name) {
		try {
			int indxDash = name.indexOf("_");
			if (name.length() > 4 || name.length() < 3 || indxDash == -1) {
				throw new Exception("Poorly formatted card name");
			}

			this.rank = Integer.parseInt(name.substring(0,indxDash));

			String suit = name.substring(indxDash + 1);
			String possibleSuits = "HCSD";
			if (suit.length() != 1 || !possibleSuits.contains(suit)) {
				throw new Exception("Invalid suit");
			} else {
				this.suit = suit.charAt(0);
			}

			this.name = name;
		} catch (NumberFormatException e) {
			System.err.println("Internal Error: Invalid rank");
		} catch (Exception e) {
			System.err.println("Internal Error: " + e.getMessage());
		}
	}

	public String toString() {
		return name;
	}

	public int getRank() {
		return this.rank;
	}
}