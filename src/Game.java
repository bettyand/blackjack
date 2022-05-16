import java.util.Scanner;

public class Game {
    private Dealer dealer;
    private Player player;

    private Deck deck;
    private Deck discarded;

    private int wins;
    private int losses;
    private int pushes;

    private int playerMoney;
    private int playerBet;

    public Game() {
        dealer = new Dealer();
        player = new Player();

        deck = new Deck(true);
        discarded = new Deck();

        playerMoney = 100;

        deck.shuffle();
    }

    public Game(String playerName) {
        dealer = new Dealer();
        player = new Player(playerName);

        deck = new Deck(true);
        discarded = new Deck();

        playerMoney = 100;

        deck.shuffle();
    }

    public int getPlayerMoney() {
        return this.playerMoney;
    }

    public void startRound() {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
            playerBet = Integer.valueOf(in.nextLine());
            if (playerBet > playerMoney) {
                System.out.println("You can't bet more money than you have. Try again.");
            } else if (playerBet < 0) {
                System.out.println("A joker, I see... bets must be positive numbers!");
            } else {
                break;
            }
        }

        dealer.getHand().takeCardFromDeck(deck);
        dealer.getHand().takeCardFromDeck(deck);

        player.getHand().takeCardFromDeck(deck);
        player.getHand().takeCardFromDeck(deck);

        dealer.printFirstHand();
        player.printHand();

        pause();

        boolean keepPlaying = checkForBlackJack(dealer, player);

        if (keepPlaying) {
            if (playerMoney >= playerBet * 2) {
                System.out.println();
                System.out.println("Before you decide to hit or stay, would you like to up the stakes?");
                System.out.println("Enter 2 to double down (or any other key to continue with your current bet)");
                int response = Integer.parseInt(in.nextLine());
                if (response == 2) {
                    playerBet *= 2;
                    System.out.println("Your new bet is $" + playerBet);
                } else {
                    System.out.println("Ok then, on with the game...");
                }
            }

            player.makeDecision(deck, discarded);

            if (player.getHand().calculateValue() > 21) {
                System.out.println("You have gone over 21 - Bust :(");
                losses++;
                playerMoney -= playerBet;
            } else {
                pause();

                dealer.printHand();
                while (dealer.getHand().calculateValue() < 17) {
                    dealer.hit(deck, discarded);
                }

                if (dealer.getHand().calculateValue() > 21) {
                    System.out.println("Dealer busts - you win!");
                    wins++;
                    playerMoney += playerBet;
                } else if (dealer.getHand().calculateValue() > player.getHand().calculateValue()) {
                    System.out.println("Dealer's hand is higher - you lose.");
                    losses++;
                    playerMoney -= playerBet;
                } else if (player.getHand().calculateValue() > dealer.getHand().calculateValue()) {
                    System.out.println("Your hand is higher - you win.");
                    wins++;
                    playerMoney += playerBet;
                } else {
                    System.out.println("It's a tie - push.");
                    pushes++;
                }
            }
        }
        pause();
        nthRound();
    }

    private void nthRound() {
        Scanner in = new Scanner(System.in);

        System.out.println();
        System.out.println("Wins: " + wins + " Losses: " + losses + " Pushes: " + pushes);
        System.out.println("You now have $" + playerMoney + ".");

        System.out.println();
        System.out.println("Would you like to keep playing?");
        System.out.println("Enter 1 for yes, or 2 to take your money and run!");
        int response = Integer.parseInt(in.nextLine());

        if (response == 2) {
            return;
        } else {
            System.out.println();
            System.out.println("Starting Next Round...");
            System.out.println();
            pause();

            dealer.getHand().discardHand(discarded);
            player.getHand().discardHand(discarded);

            if (deck.cardsLeft() < 4) {
                deck.reloadFromDiscard(discarded);
            }

            startRound();
        }
    }

    private boolean checkForBlackJack(Dealer dealer, Player player) {
        if (dealer.hasBlackJack() && player.hasBlackJack()) {
            dealer.printHand();
            System.out.println("You both have BlackJack - Push");
            pushes++;
            return false;
        } else if (dealer.hasBlackJack()) {
            dealer.printHand();
            System.out.println("Dealer has BlackJack. You lose.");
            losses++;
            playerMoney -= playerBet;
            return false;
        } else if (player.hasBlackJack()) {
            System.out.println("You have BlackJack! You win!");
            wins++;
            playerMoney += (int) (playerBet * 1.5);
            return false;
        } else {
            return true;
        }
    }

    public static void pause() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}