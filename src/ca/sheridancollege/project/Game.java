package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class representing a generic game.
 * Author: Dhruv
 * Following SRP: This class only defines the structure and basic behavior of a Game
 */
public abstract class Game {
    private final String name; // Name of the game
    private ArrayList<Player> players; // List of players in the game

    public Game(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public abstract void play(); // Abstract method to play the game

    public abstract void declareWinner(); // Abstract method to declare the winner
}

/**
 * Class representing the War card game.
 * Following OCP: This class extends Game and implements the specific logic for the War game without modifying the Game class
 */
class WarGame extends Game {
    private int currentRound; // Current round number
    private Map<PlayingCard, Player> cardPlayerMap; // Map to track which player played which card

    public WarGame(String name) {
        super(name);
        currentRound = 1;
        cardPlayerMap = new HashMap<>();
    }

    @Override
    public void play() {
        // Play rounds until the maximum number of rounds is reached or the game is over
        while (currentRound <= 4 && !isGameOver()) {
            System.out.println("Round " + currentRound);
            playRound();
            currentRound++;
        }
        declareWinner();
    }

    private void playRound() {
        ArrayList<Card> cardsInMiddle = new ArrayList<>();
        ArrayList<Player> roundWinners = new ArrayList<>();
        cardPlayerMap.clear(); // Clear the map for each round

        // Each player plays a card
        for (Player player : getPlayers()) {
            PlayingCard card = (PlayingCard) player.playCard();
            if (card != null) {
                cardsInMiddle.add(card);
                cardPlayerMap.put(card, player); // Map the card to the player who played it
                System.out.println(player.getName() + " plays " + card);
            } else {
                System.out.println(player.getName() + " has no card to play.");
            }
        }

        // Determine the highest card played
        PlayingCard highestCard = null;
        for (Card card : cardsInMiddle) {
            if (highestCard == null || ((PlayingCard) card).getRankValue() > highestCard.getRankValue()) {
                highestCard = (PlayingCard) card;
                roundWinners.clear();
                roundWinners.add(cardPlayerMap.get((PlayingCard) card)); // Get the player from the map
            } else if (((PlayingCard) card).getRankValue() == highestCard.getRankValue()) {
                roundWinners.add(cardPlayerMap.get((PlayingCard) card)); // Get the player from the map
            }
        }

        // Resolve ties by going to war
        if (roundWinners.size() > 1) {
            resolveWar(roundWinners, cardsInMiddle);
        } else {
            if (!roundWinners.isEmpty()) {
                Player roundWinner = roundWinners.get(0);
                if (roundWinner != null) {
                    roundWinner.collectCards(cardsInMiddle);
                    System.out.println("Round winner: " + roundWinner.getName());
                } else {
                    System.out.println("Error: Round winner is null.");
                }
            } else {
                System.out.println("No round winner could be determined.");
            }
        }
    }

    private void resolveWar(ArrayList<Player> tiedPlayers, ArrayList<Card> cardsInMiddle) {
        ArrayList<Card> warCards = new ArrayList<>();
        PlayingCard highestWarCard = null;
        Player warWinner = null;

        // Each tied player plays cards for the war
        for (Player player : tiedPlayers) {
            if (player.getHandSize() >= 5) {
                ArrayList<Card> playerWarCards = player.playWarCards();
                if (!playerWarCards.isEmpty()) {
                    warCards.addAll(playerWarCards);
                    PlayingCard warCard = (PlayingCard) playerWarCards.get(playerWarCards.size() - 1);
                    if (highestWarCard == null || warCard.getRankValue() > highestWarCard.getRankValue()) {
                        highestWarCard = warCard;
                        warWinner = player;
                    }
                }
            } else {
                System.out.println(player.getName() + " does not have enough cards for war.");
            }
        }

        // Determine the winner of the war and collect all the cards
        if (warWinner != null) {
            warWinner.collectCards(cardsInMiddle);
            warWinner.collectCards(warCards);
            System.out.println("War winner: " + warWinner.getName());
        } else {
            System.out.println("Not enough cards to resolve war.");
        }
    }

    private Player getPlayerByCard(PlayingCard card) {
        return cardPlayerMap.get(card);
    }

    @Override
    public void declareWinner() {
        Player winner = null;
        int highestScore = 0;

        // Determine the player with the highest score
        for (Player player : getPlayers()) {
            int score = player.getHandSize();
            if (score > highestScore) {
                highestScore = score;
                winner = player;
            }
        }

        if (winner != null) {
            System.out.println("The overall winner is " + winner.getName() + " with " + highestScore + " cards.");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private boolean isGameOver() {
        // Check if any player has no cards left
        for (Player player : getPlayers()) {
            if (player.getHandSize() == 0) {
                return true;
            }
        }
        return false;
    }
}
