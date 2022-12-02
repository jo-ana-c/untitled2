package TurnResults;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurnResultTest {

    TurnResult tr = new TurnResult();

    @Test
    public void test_cloverleaf(){
        assertFalse(tr.cloverleafWon());
        tr.setCloverleaf(1);
        assertFalse(tr.cloverleafWon());
        tr.setCloverleaf(2);
        assertTrue(tr.cloverleafWon());
    }

    @Test
    public void test_set_get_Cloverleaf() {
        assertEquals(0, tr.getCloverleaf());
        tr.setCloverleaf(1);
        assertEquals(1, tr.getCloverleaf());
    }

    @Test
    public void test_set_get_Firework(){
        assertFalse(tr.getFirework());
        tr.setFirework(true);
        assertTrue(tr.getFirework());
    }

    @Test
    public void test_set_get_NewCard(){
        assertFalse(tr.getNewCard());
        tr.setNewCard(true);
        assertTrue(tr.getNewCard());
    }

    @Test
    public void test_set_get_PlusMinus() {
        assertEquals(0, tr.getPlusMinus());
        tr.setPlusMinus(1);
        assertEquals(1, tr.getPlusMinus());
    }

    @Test
    public void test_set_get_Points() {
        assertEquals(0,tr.getPoints());
        tr.setPoints(300);
        assertEquals(300,tr.getPoints());
    }
}
