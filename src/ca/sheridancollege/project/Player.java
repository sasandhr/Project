package ca.sheridancollege.project;

import java.util.ArrayList;

public abstract class Player {
    private String name;
    private ArrayList<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void play();

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public Card playCard() {
        if (!hand.isEmpty()) {
            return hand.remove(0);
        } else {
            return null;
        }
    }

    public int getHandSize() {
        return hand.size();
    }

    public boolean hasCard(Card card) {
        return hand.contains(card);
    }

    public void collectCards(ArrayList<Card> cards) {
        hand.addAll(cards);
    }

    public ArrayList<Card> playWarCards() {
        ArrayList<Card> warCards = new ArrayList<>();
        for (int i = 0; i < 5 && !hand.isEmpty(); i++) {
            warCards.add(hand.remove(0));
        }
        return warCards;
    }
}

class WarPlayer extends Player {
    public WarPlayer(String name) {
        super(name);
    }

    @Override
    public void play() {
        // Implement the play logic for WarPlayer if needed
    }
}
