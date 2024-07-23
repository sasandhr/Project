package ca.sheridancollege.project;

// Abstract class representing a card
// Author: Vraj

public abstract class Card {
    // Abstract method to be implemented by subclasses
    @Override
    public abstract String toString();
}

// Enum representing different suits of playing cards
enum Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES
}

// Enum representing different ranks of playing cards with associated values
enum Rank {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);

    private final int value;

    // Constructor for Rank enum
    Rank(int value) {
        this.value = value;
    }

    // Method to retrieve the integer value of the rank
    public int getValue() {
        return value;
    }
}

// Class representing a playing card with suit and rank
class PlayingCard extends Card {
    private Suit suit;
    private Rank rank;

    // Constructor to initialize a PlayingCard with given suit and rank
    public PlayingCard(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // Getter for the suit of the card
    public Suit getSuit() {
        return suit;
    }

    // Getter for the rank of the card
    public Rank getRank() {
        return rank;
    }

    // Getter for the integer value of the rank
    public int getRankValue() {
        return rank.getValue();
    }

    // Override method to represent the PlayingCard as a string
    @Override
    public String toString() {
        return rank + " of " + suit;
   }
}
