package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the War Card Game!");

        System.out.print("Enter the name of player 1: ");
        String player1Name = scanner.nextLine();
        WarPlayer player1 = new WarPlayer(player1Name);

        System.out.print("Enter the name of player 2: ");
        String player2Name = scanner.nextLine();
        WarPlayer player2 = new WarPlayer(player2Name);

        WarGame warGame = new WarGame("War Card Game");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        warGame.setPlayers(players);

        GroupOfCards deck = new GroupOfCards(52);
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.addCard(new PlayingCard(suit, rank));
            }
        }
        deck.shuffle();

        for (int i = 0; i < 52; i++) {
            if (i % 2 == 0) {
                player1.addCardToHand(deck.drawCard());
            } else {
                player2.addCardToHand(deck.drawCard());
            }
        }

        warGame.play();
    }
}
