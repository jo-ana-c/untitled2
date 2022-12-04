package Cards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BonusCardTest {

    BonusCard bc = new BonusCard(200);

    @Test
    public void test_constructor(){
        assertEquals(200, bc.bonus);
    }

}