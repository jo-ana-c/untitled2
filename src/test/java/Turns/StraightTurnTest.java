package Turns;

import TurnResults.TurnResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StraightTurnTest {
    StraightTurn st = new StraightTurn(new TurnResult());

    @Test
    void test_first_is_not_null(){
        assertFalse(st.nullThrow());
    }

    @Test
    void test_second_is_null(){
        st.dice.selectSingleDice(0);
        assertTrue(st.nullThrow());
    }

    /*@Test
    void test_select() {
        st.selectDice();
        assertEquals(1,1);
    }*/

}