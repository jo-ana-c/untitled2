package DicePackage;

import DiePackage.Die;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiceTest {
    // Same instance for all Tests
    private final Dice d = new Dice();
    //private final StubDice stubDice = new StubDice();


    @Test
    public void test1_die_initialized_with_six_dice() {
        int counter = 0;
        for (Die die : d) {
            counter += 1;
        }
        assertEquals(counter, 6);
    }

    @Test
    public void test2_die_rolling_not_selected() {
        d.rollDice();
        int counter = 0;
        for (Die die : d) {
            if (die.isSelected()) {
                counter += 1;
            }
        }
        assertEquals(counter, 0);
    }

    @Test
    public void test3_die_selected_not_roll_all_again() {
        d.rollDice();
        for (Die die : d) {
            if (0 < die.getValue() && die.getValue() < 7) {
                die.select();
            }
        }
        int counter = 0;
        d.rollDice();
        for (Die die2 : d) {
            if (die2.isSelected()) {
                counter += 1;
            }
        }
        assertEquals(counter, 6);
    }

    @Test
    public void test4_selectSingleDice() {
        int counter = 0;
        boolean selected = false;
        for (int i = 0; i < 6; i++) {
            d.selectSingleDice(i);
            for (Die die : d) {
                if (die.isSelected()) {
                    counter += 1;
                    selected = true;
                }
            }
            if (selected) {
                break;
            }


        }
        assertEquals(1, counter);
    }

    @Test
    public void test5_selectNoSingleDice() {
        int counter = 0;
        d.selectSingleDice(7);
        for (Die die : d) {
            if (die.isSelected()) {
                counter += 1;
            }
        }
        assertEquals(0, counter);
    }

    @Test
    public void test6_selectTripleDice(){
        int counter = 0;
        boolean control = true;
        while(control){
            d.rollDice();
            d.selectTripleDice(1);
            for (Die die: d){
                if (die.getValue() == 1){
                    counter += 1;
                    if(counter == 3){
                        break;
                    }
                }
            }
            if (counter == 3){
                control = false;
            }
        }
        assertEquals(3, counter);
    }

    @Test
    public void test7_displaySomething(){
        int counter = 0;
        boolean control = true;
        while(control){
            counter = 0;
            d.rollDice();
            for (Die die: d){
                if (die.getValue() == 1){
                    counter += 1;
                }
            }
            if (counter == 6){
                control = false;
            }
        }
        //d.displayDice();
        final ByteArrayOutputStream out = new ByteArrayOutputStream( 256 );
        PrintStream ps = new PrintStream( out );
        System.setOut( ps );
        d.displayDice();
        assertEquals( "|| No dice selected yet ||\n|| Rolled dice: 1 1 1 1 1 1 ||\n\n" , out.toString());
    }

    /*@Test
    public void test8_selectTripleDiceNoTriplets(){
        boolean control = true;
        int counter = 0;
        while(control){
            counter = 0;
            d.rollDice();
            d.selectTripleDice(1);
            for (Die die: d){
                if (die.getValue() == 1){
                    counter += 1;
                }
            }
            if (counter == 2){
                control = false;
            }
        }
        assertEquals(2, counter);
    }*/

    @Test
    public void test9_diceSelected(){
        boolean control = true;
        int counter = 0;
        while(control){
            counter = 0;
            d.rollDice();
            d.selectTripleDice(1);
            for (Die die: d){
                if (die.getValue() == 1){
                    counter += 1;
                    die.select();
                }
            }
            if (counter == 6){
                control = false;
            }
        }
        final ByteArrayOutputStream out = new ByteArrayOutputStream( 256 );
        PrintStream ps = new PrintStream( out );
        System.setOut( ps );
        d.displayDice();

        assertEquals( "|| The following dice are selected: 1 1 1 1 1 1 ||\n|| Rolled dice: ||\n\n" , out.toString());
    }

}