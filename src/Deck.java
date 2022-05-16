import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<Card>();
    }

    public Deck(Deck c) {
        Collections.copy(this.deck, c.getCards());
    }

    public Deck(boolean makeDeck) {
        deck = new ArrayList<Card>();
        if (makeDeck) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    deck.add(new Card(suit, rank));
                }
            }
        }
    }

    public void addCard(Card card) {
        deck.add(card);
    }

    public void addCards(ArrayList<Card> cards) {
        deck.addAll(cards);
    }

    public ArrayList<Card> getCards() {
        return deck;
    }

    public int cardsLeft() {
        return deck.size();
    }

    public void reloadFromDiscard(Deck discard) {
        System.out.println("Ran out of cards, creating new deck from discard pile & shuffling deck...");
        this.addCards(discard.getCards());
        this.shuffle();        
        discard.emptyDeck();
    }

    public void shuffle() {
            Collections.shuffle(deck);
    }

    public Card takeCard() {
        Card cardToTake = new Card(deck.remove(0));
        return cardToTake;
    }

    public boolean hasCards() {
        if (deck.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void emptyDeck() {
        deck.clear();
    }

    @Override
    public String toString() {
        String output = "";

        for(Card card : deck) {
            output += card;
            output += "\n";
        }
        return output;
    }
}
