package Decks;

import Cards.*;

import java.util.ArrayList;
import java.util.Collections;

public final class Deck {
    private ArrayList<AbstractCard> deck = new ArrayList<>(56);
    private ArrayList<AbstractCard> drawnCards = new ArrayList<>(56);

    public Deck() {
        this.deck.add(new CloverleafCard());
        for (int i = 0; i < 5; i++) {
            this.deck.add(new FireworkCard());
            this.deck.add(new StraightCard());
            this.deck.add(new PlusMinusCard());
            this.deck.add(new X2Card());
            this.deck.add(new BonusCard(200));
            this.deck.add(new BonusCard(300));
            this.deck.add(new BonusCard(400));
            this.deck.add(new BonusCard(500));
            this.deck.add(new BonusCard(600));
            for (int j = 0; j < 2; j++) {
                this.deck.add(new StopCard());
            }
        }
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(this.deck);
    }

    private boolean isEmpty() {
        return this.deck.size() == 0;
    }

    public AbstractCard draw() {
        if (isEmpty()) {
            this.deck = this.drawnCards;
            this.drawnCards = new ArrayList<>(56);
            shuffle();
        }
        AbstractCard card = this.deck.remove(0);
        this.drawnCards.add(card);
        if (card instanceof BonusCard) {
            int b = ((BonusCard) card).bonus;
            System.out.println("\n||A " + card.getClass().getSimpleName() + " (" + b + ") was drawn||\n");
        }
        else {System.out.println("\n||A " + card.getClass().getSimpleName() + " was drawn||\n");}
        return card;
    }
}