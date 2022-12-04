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

    /*@Test
    public void test_dont_select_a_die(){
        MockInput mi = new MockInput(5);
        st.inputObject = mi;
        st.selectDice();
        int selected = 0;
        for (Die d : st.dice) {
            if(d.isSelected()){selected++;}
        }
        assertEquals(0,selected);
    }*/

    @Test
    public void test_constructor() {
        assertTrue(st.tr instanceof TurnResult);
    }

    @Test
    public void test_select_one_twice(){
        st.inputObject = new MockInput(4);
        int counter = 0;
        // select all dice but 2
        for (Die d : st.dice) {
            d.select();
            counter++;
            if (counter == 4) {
                break;
            }
        }
        int count1 = 0;
        while (count1 < 2) {
            count1 = 0;
            st.dice.rollDice();
            for (Die d : st.dice) {
                if (d.getValue() == 1) {
                    count1++;
                }
            }
        }
        st.selectDice();
        int selected = 0;
        for (Die d : st.dice) {
            if(d.isSelected()){selected++;}
        }
        assertEquals(5,selected);
    }



}