public class Card {
    private Suit suit;
    private Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Card(Card card) {
        this.suit = card.getSuit();
        this.rank = card.getRank();
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public int getValue() {
        return rank.rankValue;
    }

    @Override
    public String toString() {
        return ("[" + this.getRank() + " of " + this.getSuit() + "] (" + this.getValue() + ")");
    }
}
