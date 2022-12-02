package Decks;

import Cards.AbstractCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DeckTest {
    Deck deck = new Deck();

    @Test
    void returnsCard() {
        assertTrue(deck.draw() instanceof AbstractCard);
    }
}