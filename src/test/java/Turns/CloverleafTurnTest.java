package Turns;

import TurnResults.TurnResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CloverleafTurnTest extends AbstractTurnTest{

    CloverleafTurn ct = new CloverleafTurn(new TurnResult());

    @Test
    public void test_setCL(){
        ct.tuttoPoints(ct.tr);
        assertEquals(1,ct.tr.getCloverleaf());
    }

    @Test
    public void test_constructor() {
        assertTrue(ct.tr instanceof TurnResult);
    }


}