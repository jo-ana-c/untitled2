package Turns;

import TurnResults.TurnResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class X2TurnTest extends AbstractTurnTest{

    X2Turn x2 = new X2Turn(new TurnResult());

    @Test
    public void test_tuttoPoints() {
        x2.tempPoints = 200;
        x2.tuttoPoints(x2.tr);
        assertEquals(400, x2.tr.getPoints());
        assertTrue(x2.tr.getNewCard());
    }

    @Test
    public void test_not_roll_again(){
        MockInput mi = new MockInput(1);
        x2.inputObject = mi;
        assertFalse(x2.rollAgain());
        assertFalse(x2.tr.getNewCard());
    }

    @Test
    public void test_roll_again(){
        MockInput mi = new MockInput(3);
        x2.inputObject = mi;
        assertTrue(x2.rollAgain());
        assertTrue(x2.tr.getNewCard());
    }

}