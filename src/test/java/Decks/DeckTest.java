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

    @Test
    void test_shuffles_if_empty(){
        for (int i = 0; i < 57; i++) {
            deck.draw();
        }
    }
}