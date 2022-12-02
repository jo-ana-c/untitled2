package Turns;

import DiePackage.Die;
import Inputs.AbstractInput;
import TurnResults.TurnResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AbstractTurnTest {

    class MockInput extends AbstractInput {
        public int mockInput;

        public MockInput(int mockInput) {
            this.mockInput = mockInput;
        }

        @Override
        public int askIntegerInput() {
            return mockInput;
        }

        @Override
        public String askStringInput() {
            if (mockInput == 1) {
                return "E";
            } else if (mockInput == 2) {
                return "D";
            } else {
                return "R";
            }
        }
    }

    class ConcreteAbstractTurn extends AbstractTurn {

        protected ConcreteAbstractTurn(TurnResult tr) {
            super(tr);
        }

        @Override
        void tuttoPoints(TurnResult tr) {
            return;
        }
    }

    ConcreteAbstractTurn cat = new ConcreteAbstractTurn(new TurnResult());

    @Test
    void test_first_not_null(){
        assertFalse(cat.nullThrow());
    }

    @Test
    void test_roll_untill_one(){
        boolean control = true;
        while(control) {
            cat.dice.rollDice();
            for (Die d : cat.dice) {
                if (d.getValue() == 1 || d.getValue() == 5) {
                    control = false;
                    break;
                }
            }
        }
        assertFalse(cat.nullThrow());
    }

    /*@Test
    void test_hashmap(){
        HashMap<Integer, Integer> hm = cat.populateHashmap(cat.dice);
        assertEquals(hm.get(0), 6);
    }*/

    /*@Test
    public void test_IsTutto_And_Not() {
        assertFalse(cat.isTutto());
        for (Die d : cat.dice) {
            d.select();
        }
        assertTrue(cat.isTutto());
    }*/

    /*@Test
    void test_there_are_triplets(){
        ArrayList<Integer> triplets = cat.triplets(cat.populateHashmap(cat.dice));
        assertTrue(triplets.contains(0));
    }*/

    @Test
    void test_template_null() {
        for (int i = 0; i < 6; i++) {
            cat.dice.selectSingleDice(0);
        }

        TurnResult tr1 = cat.templateTurn();
        assertEquals(0, tr1.getPoints());
        assertEquals(0,tr1.getPlusMinus());
        assertEquals(0, tr1.getCloverleaf());
        assertEquals(false, tr1.getNewCard());
    }

    @Test
    public void select_singles_with_input() {
        boolean control = true;
        while(control) {
            cat.dice.rollDice();
            for (Die d : cat.dice) {
                if (d.getValue() == 1) {
                    control = false;
                    break;
                }
            }
        }
    }



}