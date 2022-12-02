package Turns;

import TurnResults.TurnResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlusMinusTurnTest extends AbstractTurnTest{

    PlusMinusTurn pm = new PlusMinusTurn(new TurnResult());

    @Test
    public void test_tuttoPoints_plusminus() {
        pm.tuttoPoints(pm.tr);
        assertEquals(1, pm.tr.getPlusMinus());
        assertEquals(0, pm.tr.getPoints());
        assertTrue(pm.tr.getNewCard());
    }

    @Test
    public void test_constructor() {
        assertTrue(pm.tr instanceof TurnResult);
    }


}