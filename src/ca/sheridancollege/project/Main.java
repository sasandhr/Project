package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Author: Dhruv
 * 
 * This is the main class for the War Card Game. It initializes the game,
 * creates players, and sets up the deck of cards.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Welcome message
        System.out.println("Welcome to the War Card Game!");

        // Get the name of player 1
        System.out.print("Enter the name of player 1: ");
        String player1Name = scanner.nextLine();
        WarPlayer player1 = new WarPlayer(player1Name);

        // Get the name of player 2
        System.out.print("Enter the name of player 2: ");
        String player2Name = scanner.nextLine();
        WarPlayer player2 = new WarPlayer(player2Name);

        // Initialize the WarGame with a name
        WarGame warGame = new WarGame("War Card Game");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        warGame.setPlayers(players);

        // Create and shuffle the deck of cards
        GroupOfCards deck = new GroupOfCards(52);
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.addCard(new PlayingCard(suit, rank));
            }
        }
        deck.shuffle();

        // Distribute the cards to the players
        for (int i = 0; i < 52; i++) {
            if (i % 2 == 0) {
                player1.addCardToHand(deck.drawCard());
            } else {
                player2.addCardToHand(deck.drawCard());
            }
        }

        // Start the game
        warGame.play();
    }
}
