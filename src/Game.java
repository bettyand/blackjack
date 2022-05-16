public class Game {
    private Dealer dealer;
    private Player player;

    private Deck deck;
    private Deck discarded;

    private int wins;
    private int losses;
    private int pushes;

    public Game() {
        dealer = new Dealer();
        player = new Player();
        
        deck = new Deck(true);
        discarded = new Deck();

        deck.shuffle();
        startRound();
    }

    private void startRound() {
        if (wins > 0 || losses > 0 || pushes > 0) {
            System.out.println();
            System.out.println("Starting Next Round... Wins: " + wins + " Losses: " + losses + " Pushes: " + pushes);
            dealer.getHand().discardHand(discarded);
            player.getHand().discardHand(discarded);
        }

        if (deck.cardsLeft() < 4) {
            deck.reloadFromDiscard(discarded);
        }

        dealer.getHand().takeCardFromDeck(deck);
        dealer.getHand().takeCardFromDeck(deck);

        player.getHand().takeCardFromDeck(deck);
        player.getHand().takeCardFromDeck(deck);

        dealer.printFirstHand();
        player.printHand();

        if (dealer.hasBlackJack()) {
            dealer.printHand();

            if (player.hasBlackJack()) {
                System.out.println("You both have 21 - Push");
                pushes++;
                startRound();
            } else {
                System.out.println("Dealer has BlackJack. You lose.");
                losses++;
                startRound();
            }
        }

        if (player.hasBlackJack()) {
            System.out.println("You have BlackJack! You win!");
            wins++;
            startRound();
        }

        player.makeDecision(deck, discarded);

        if (player.getHand().calculateValue() > 21) {
            System.out.println("You have gone over 21 - bust :(");
            losses++;
            startRound();
        }

        dealer.printHand();
        while (dealer.getHand().calculateValue() < 17) {
            dealer.hit(deck, discarded);
        }

        if (dealer.getHand().calculateValue() > 21) {
            System.out.println("Dealer busts - you win!");
            wins++;
        } else if (dealer.getHand().calculateValue() > player.getHand().calculateValue()) {
            System.out.println("Dealer's hand is higher - you lose.");
            losses++;
        } else if (player.getHand().calculateValue() > dealer.getHand().calculateValue()) {
            System.out.println("Your hand is higher - you win.");
            wins++;
        } else {
            System.out.println("It's a tie - push.");
            pushes++;
        }

        startRound();
    }
}
