package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * Represents a player in the card game.
 * Author: Nakul
 */
public abstract class Player {
    private String name;
    private ArrayList<Card> hand;

    /**
     * Constructs a Player with the specified name.
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    /**
     * Gets the name of the player.
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the player.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Abstract method to define how the player plays.
     * This must be implemented by subclasses.
     */
    public abstract void play();

    /**
     * Adds a card to the player's hand.
     * @param card the card to add
     */
    public void addCardToHand(Card card) {
        hand.add(card);
    }

    /**
     * Plays a card from the player's hand.
     * @return the card played, or null if the hand is empty
     */
    public Card playCard() {
        if (!hand.isEmpty()) {
            return hand.remove(0);
        } else {
            return null;
        }
    }

    /**
     * Gets the number of cards in the player's hand.
     * @return the number of cards in hand
     */
    public int getHandSize() {
        return hand.size();
    }

    /**
     * Checks if the player has a specific card in hand.
     * @param card the card to check for
     * @return true if the card is in hand, false otherwise
     */
    public boolean hasCard(Card card) {
        return hand.contains(card);
    }

    /**
     * Collects a list of cards into the player's hand.
     * @param cards the list of cards to collect
     */
    public void collectCards(ArrayList<Card> cards) {
        hand.addAll(cards);
    }

    /**
     * Plays a set of cards for the war phase.
     * @return a list of war cards played
     */
    public ArrayList<Card> playWarCards() {
        ArrayList<Card> warCards = new ArrayList<>();
        for (int i = 0; i < 5 && !hand.isEmpty(); i++) {
            warCards.add(hand.remove(0));
        }
        return warCards;
    }
}

/**
 * Represents a player specifically for the game War.
 */
class WarPlayer extends Player {
    /**
     * Constructs a WarPlayer with the specified name.
     * @param name the name of the WarPlayer
     */
    public WarPlayer(String name) {
        super(name);
    }

    /**
     * Implements the play logic for WarPlayer.
     * Currently, this method does not contain any specific logic.
     */
    @Override
    public void play() {
        // Implement the play logic for WarPlayer if needed
    }
}
