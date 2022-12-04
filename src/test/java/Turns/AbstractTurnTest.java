package Turns;

import DiePackage.Die;
import Inputs.AbstractInput;
import TurnResults.TurnResult;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

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
            } else if (mockInput == 3) {
                return "R";
            } else if (mockInput == 4) {
                return "Y";
            } else if (mockInput == 5) {
                return "N";
            }
            return "Z";
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

    public int rollTriplet() {
        while (true) {
            ArrayList<Integer> occurrences = new ArrayList<Integer>(Collections.nCopies(6, 0));
            cat.dice.rollDice();
            for (Die d : cat.dice) {
                occurrences.set((d.getValue() - 1), occurrences.get(d.getValue() - 1) + 1);
            }
            for (int i = 0; i < 6; i++) {
                if (occurrences.get(i) >= 3) {
                    return i + 1;
                }
            }
        }
    }

    @Test
    void test_first_not_null() {
        assertFalse(cat.nullThrow());
    }

    @Test
    void test_roll_untill_one() {
        boolean control = true;
        while (control) {
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

    @Test
    void test_is_nullThrow() {
        int counter = 0;
        while (counter < 5) {
            for (Die d : cat.dice) {
                if (!d.isSelected()) {
                    d.select();
                    counter++;
                    break;
                }
            }
        }
        assertTrue(cat.nullThrow());
    }

    @Test
    void test_hashmap() {
        HashMap<Integer, Integer> hm = cat.populateHashmap(cat.dice);
        assertEquals(((HashMap<Integer, Integer>) hm).get(0), 6);
    }

    @Test
    void test_there_are_triplets() {
        ArrayList<Integer> triplets = cat.triplets(cat.populateHashmap(cat.dice));
        assertTrue(((ArrayList<Integer>) triplets).contains(0));
    }

    @Test
    void test_template_null() {
        for (int i = 0; i < 6; i++) {
            cat.dice.selectSingleDice(0);
        }

        TurnResult tr1 = cat.templateTurn();
        assertEquals(0, tr1.getPoints());
        assertEquals(0, tr1.getPlusMinus());
        assertEquals(0, tr1.getCloverleaf());
        assertEquals(false, tr1.getNewCard());
    }

    @Test
    public void select_singles_with_input() {
        boolean control = true;
        while (control) {
            cat.dice.rollDice();
            for (Die d : cat.dice) {
                if (d.getValue() == 1) {
                    control = false;
                    break;
                }
            }
        }
    }

    @Test
    public void test_select_triplets_of1() {
        MockInput mi = new MockInput(rollTriplet());
        cat.inputObject = mi;
        assertTrue(cat.selectTriplets());
    }

    @Test
    public void test_dont_select_triplets() {
        MockInput mi = new MockInput(0);
        cat.inputObject = mi;
        int counter = 0;
        while (counter < 3) {
            counter = 0;
            cat.dice.rollDice();
            for (Die d : cat.dice) {
                if (d.getValue() == 1) {
                    counter++;
                }
            }
        }
        assertFalse(cat.selectTriplets());
    }

    @Test
    public void test_select_single1() {
        MockInput mi = new MockInput(1);
        cat.inputObject = mi;
        int counter = 0;
        while (counter < 1) {
            counter = 0;
            cat.dice.rollDice();
            for (Die d : cat.dice) {
                if (d.getValue() == 1) {
                    counter++;
                }
            }
        }
        assertTrue(cat.selectSingles(1, 100));
        assertEquals(100, cat.tempPoints);
    }

    @Test
    public void test_no_ones_to_select() {
        MockInput mi = new MockInput(1);
        cat.inputObject = mi;
        assertFalse(cat.selectSingles(1, 100));
    }

    @Test
    public void test_choose_not_to_select_ones() {
        MockInput mi = new MockInput(0);
        cat.inputObject = mi;
        int counter = 0;
        while (counter < 1) {
            counter = 0;
            cat.dice.rollDice();
            for (Die d : cat.dice) {
                if (d.getValue() == 1) {
                    counter++;
                }
            }
        }
        assertFalse(cat.selectSingles(1, 100));
    }

    @Test
    public void test_trueRollAgain() {
        assertTrue(cat.rollAgain());
    }

    @Test
    public void test_selectDice_changesDice_single() {
        cat.inputObject = new MockInput(1);
        int counter = 0;
        // select all dice but 1
        for (Die d : cat.dice) {
            d.select();
            counter++;
            if (counter == 5) {
                break;
            }
        }
        boolean control = true;
        while (control) {
            cat.dice.rollDice();
            for (Die d : cat.dice) {
                if (d.getValue() == 1) {
                    control = false;
                    break;
                }
            }
        }
        cat.selectDice();
        int count = 0;
        for (Die d : cat.dice) {
            if (d.isSelected()) {
                count++;
            }
        }
        assertEquals(6, count);
    }
}