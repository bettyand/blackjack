import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<Card>();
    }

    public Card getCard(int index) {
        return hand.get(index);
    }

    public Card getLast() {
        int index = this.hand.size() - 1;
        return hand.get(index);
    }

    public void takeCardFromDeck(Deck deck) {
        hand.add(deck.takeCard());
    }

    public void discardHand(Deck discardDeck) {
        discardDeck.addCards(hand);
        hand.clear();
    }

    public int calculateValue() {
        int value = 0;
        int aces = 0;

        for (Card card : hand) {
            if (card.getValue() == 11) {
                aces++;
            } else {
                value += card.getValue();
            }
        }

        for (int i = 0; i < aces; i++) {
            if (value > 10) {
                value += 1;
            } else {
                value += 11;
            }
        }

        return value;
    }

    @Override
    public String toString() {
        String output = "";
        for (Card card : hand) {
            output += card + " - ";
        }
        return output;
    }
}
