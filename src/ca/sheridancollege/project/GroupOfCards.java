package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents a group of cards. It provides methods to manage the cards within the group, 
 * including shuffling, adding, and drawing cards.
 * 
 * Author: Dev
 */
public class GroupOfCards {
    private ArrayList<Card> cards;
    private int size;

    /**
     * Constructor to create a group of cards with a specified size.
     * 
     * @param size the maximum size of the group of cards
     */
    public GroupOfCards(int size) {
        this.size = size;
        this.cards = new ArrayList<>(size);
    }

    /**
     * Gets the list of cards in the group.
     * 
     * @return an ArrayList of cards in the group
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Shuffles the cards in the group randomly.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Gets the maximum size of the group of cards.
     * 
     * @return the size of the group
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the maximum size of the group of cards.
     * 
     * @param size the new size of the group
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Adds a card to the group if the group is not already full.
     * 
     * @param card the card to be added to the group
     */
    public void addCard(Card card) {
        if (cards.size() < size) {
            cards.add(card);
        } else {
            System.out.println("Group is full.");
        }
    }

    /**
     * Draws a card from the group. The card is removed from the group.
     * 
     * @return the card drawn from the group, or null if the group is empty
     */
    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(cards.size() - 1);
        } else {
            return null;
        }
    }
}
