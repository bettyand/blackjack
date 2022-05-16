import java.util.Scanner;

public class BlackJack {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to the CTACasino, it's time to play B-B-B-BlackJack!");
        System.out.println("What can I call you? Enter nothing if you wish to remain anonymous.");
        String playerName = in.nextLine();

        Game blackjack;

        if (playerName.isBlank()) {
            System.out.println("Welcome to the table, stranger.");
            blackjack = new Game();
        } else {
            System.out.println("Welcome to the table, " + playerName);
            blackjack = new Game(playerName);
        }

        blackjack.startRound();

        if (!(blackjack.getPlayerMoney() > 0)) {
            System.out.println();
            System.out.println("You're broke :( Better luck next time!");
        } else {
            System.out.println();
            System.out.println("You're walking away with $" + blackjack.getPlayerMoney() + ", congratulations!");
            System.out.println("Make sure to visit the gift store on your way out ;)");
        }
    }
}
