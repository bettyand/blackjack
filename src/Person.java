public abstract class Person {
    private String name;
    private Hand hand;

    public Person() {
        this.hand = new Hand();
        this.name = "";
    }

    public Hand getHand() {
        return this.hand;
    }
    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void printHand() {
        System.out.println(this.name + "'s hand looks like this:");
        System.out.println(this.hand + " Valued at: " + this.hand.calculateValue());
    }

    public void hit(Deck deck, Deck discard) {
        if (!deck.hasCards()) {
            deck.reloadFromDiscard(discard);
        }
        this.hand.takeCardFromDeck(deck);
        System.out.println(this.name + " gets a card");
        this.printHand();
    }

    public boolean hasBlackJack() {
        if (this.getHand().calculateValue() == 21) {
            return true;
        } else {
            return false;
        }
    }
}
