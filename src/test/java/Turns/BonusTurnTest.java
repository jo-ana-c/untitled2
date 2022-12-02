package Turns;

import DiePackage.Die;
import TurnResults.TurnResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BonusTurnTest extends AbstractTurnTest{

    BonusTurn bt = new BonusTurn(new TurnResult(), 200);


    @Test
    public void test_tuttoPoints() {
        bt.tuttoPoints(bt.tr);
        assertEquals(200, bt.tr.getPoints());
        assertTrue(bt.tr.getNewCard());
    }

    @Test
    public void test_not_roll_again(){
        MockInput mi = new MockInput(1);
        bt.inputObject = mi;
        assertFalse(bt.rollAgain());
        assertFalse(bt.tr.getNewCard());
    }

    @Test
    public void test_roll_again(){
        MockInput mi = new MockInput(3);
        bt.inputObject = mi;
        assertTrue(bt.rollAgain());
        assertTrue(bt.tr.getNewCard());
    }


}