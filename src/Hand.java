import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<Card>();
    }

    public Card getCard(int index) {
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
        int aceCount = 0;

        for (Card card : hand) {
            value += card.getValue();
            if (card.getValue() == 11) {
                aceCount++;
            }
        }

        if (value > 21 && aceCount > 0) {
            aceCount--;
            value -= 10;
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
