public class Dealer extends Person {
    public Dealer() {
        setName("Dealer");
    }

    public void printFirstHand() {
        System.out.println();
        System.out.println("Dealer's hand:");
        System.out.println(getHand().getCard(0) + " - [face down] - Valued at: ??");
    }
}
