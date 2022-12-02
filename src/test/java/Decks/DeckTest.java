package Decks;

import org.junit.jupiter.api.Test;

class DeckTest {
    Deck deck = new Deck();

    @Test
    void returnsCard() {
        for (int i = 0; i < 55; i++) {
            deck.draw();
        }

    }

}