package DiePackage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DieTest {

    private final Die d = new Die();

    @Test
    public void newDie_selected_false() {
        //Die d = new Die();
        assertFalse(d.isSelected());
    }

    @Test
    public void newDie_no_value(){
        assertEquals(d.getValue(), 0);
    }

    @Test
    public void rollDie_value_isInteger(){
        d.rollDie();
        assertTrue(d.getValue()%1 == 0);
    }

    @Test
    public void rollDie_value_correctRange_and_getter_method(){
        d.rollDie();
        assertTrue(1 <= d.getValue() && d.getValue() <= 6);
    }

    @Test
    public void die_selected_isTrue(){
        d.select();
        assertTrue(d.isSelected());
    }
}