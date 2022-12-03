package Turns;

import DiePackage.Die;
import TurnResults.TurnResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FireworkTurnTest extends AbstractTurnTest{
    FireworkTurn ft = new FireworkTurn(new TurnResult());

    @Test
    public void test_selects_triplets_if_there_are() {
        ft.selectTriplets();
        int counter = 0;
        for (Die d : ft.dice) {
            if (d.isSelected()) {counter += 1;}
        }
        assertEquals(3,counter);
    }

    @Test
    public void test_does_not_select_triplets_if_there_are_not() {
        assertTrue(ft.selectTriplets());
        ft.dice.selectSingleDice(0);
        assertFalse(ft.selectTriplets());
        int counter = 0;
        for (Die d : ft.dice) {
            if (d.isSelected()) {counter += 1;}
        }
        assertEquals(4, counter);
    }

    @Test
    public void test_does_select_singles_values_possible(){
        ft.dice.rollDice();
        int value = 0;
        int count = 0;
        for (Die d : ft.dice) {
                value = d.getValue();
                break;
            }
        for(Die d : ft.dice){
            if(d.getValue() == value){
                count++;
            }
        }
        assertTrue(ft.selectSingles(value, 0));
        int selectedDice = 0;
        for(Die d : ft.dice){
            if(d.isSelected()){
                selectedDice++;
            }
        }
        assertEquals(count, selectedDice);
        }

    @Test
    public void test_does_not_select_singles_values_impossible(){
        ft.dice.rollDice();
        int value = 0; // 0 will never be in rolled dice
        int count = 0;
        for(Die d : ft.dice){
            if(d.getValue() == value){
                count++;
            }
        }
        assertFalse(ft.selectSingles(value, 0));
        int selectedDice = 0;
        for(Die d : ft.dice){
            if(d.isSelected()){
                selectedDice++;
            }
        }
        assertEquals(count, selectedDice);
    }

    @Test
    public void test_handle_null_firework() {
            for (int i = 0; i < 6; i++) {
                ft.dice.selectSingleDice(0);
            }
            ft.tr.setPoints(1000);
            ft.tr.setPlusMinus(1);
            TurnResult tr = ft.templateTurn();
            assertEquals(1000, tr.getPoints());
            assertEquals(1,tr.getPlusMinus());
            assertEquals(0, tr.getCloverleaf());
            assertEquals(false, tr.getNewCard());
    }

    @Test
    public void test_tuttoPoints_sets_true() {
        assertFalse(ft.tr.getFirework());
        ft.tuttoPoints(ft.tr);
        assertTrue(ft.tr.getFirework());
    }

    @Test
    public void test_constructor() {
        assertTrue(ft.tr instanceof TurnResult);
    }

    @Test
    public void test_select_triplets_of1() {
        int counter = 0;
        while (counter < 3) {
            counter = 0;
            ft.dice.rollDice();
            for (Die d : ft.dice) {
                if (d.getValue() == 1) {
                    counter++;
                }
            }
        }
        assertTrue(ft.selectTriplets());
    }
}




