package Turns;

import DiePackage.Die;
import TurnResults.TurnResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StraightTurnTest extends AbstractTurnTest{
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

    @Test
    public void test_tuttoPoints_straight() {
        st.tuttoPoints(st.tr);
        assertEquals(2000, st.tr.getPoints());
        assertTrue(st.tr.getNewCard());
    }

    @Test
    public void test_select_a_die(){
        MockInput mi = new MockInput(4);
        st.inputObject = mi;
        st.selectDice();
        int selected = 0;
        for (Die d : st.dice) {
            if(d.isSelected()){selected++;}
        }
        assertEquals(1,selected);
    }

}