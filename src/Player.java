import java.util.Scanner;

public class Player extends Person {
    Scanner in = new Scanner(System.in);

    public Player() {
        super.setName("Player");
    }

    public void makeDecision(Deck deck, Deck discard) {
        int decision = 0;
        boolean getNum = true;

        while (getNum) {
            try {
                System.out.println("Enter 1 to hit or 2 to stand.");
                decision = Integer.parseInt(in.nextLine());
                getNum = false;
            } catch (Exception e) {
                System.out.println("That's not one of the options!");
                in.next();
            }
        }

        if (decision == 1) {
            this.hit(deck, discard);
            if (this.getHand().calculateValue() > 20) {
                return;
            } else {
                this.makeDecision(deck, discard);
            }
        } else {
            System.out.println("You stand.");
        }
    }
}
