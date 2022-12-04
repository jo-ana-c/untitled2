package Inputs;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {

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

    AbstractInput input = new Input();

    @Test
    public void test_is_true_forE() {
        assertEquals("E", input.inputValidation_ER("E"));
    }
    @Test
    public void test_is_true_forR() {
        assertEquals("R", input.inputValidation_ER("R"));
    }
    @Test
    public void test_is_false_forZ() {
        input = new MockInput(3);
        assertEquals("R", input.inputValidation_ER("Z"));
    }
    @Test
    public void test8_is_false_forZ() {
        input = new MockInput(1);
        assertEquals("E", input.inputValidation_ER("Z"));
    }

    @Test
    public void test_is_true_forE2() {
        assertEquals("E", input.inputValidation_ED("E"));
    }
    @Test
    public void test_is_true_forD2() {
        assertEquals("D", input.inputValidation_ED("D"));
    }
    @Test
    public void test2_is_false_forZ() {
        input = new MockInput(2);
        assertEquals("D", input.inputValidation_ED("Z"));
    }
    @Test
    public void test7_is_false_forZ() {
        input = new MockInput(1);
        assertEquals("E", input.inputValidation_ED("Z"));
    }

    @Test
    public void test_is_true_forD() {
        assertEquals("D", input.inputValidation_RD("D"));
    }
    @Test
    public void test_is_true_forR2() {
        assertEquals("R", input.inputValidation_RD("R"));
    }
    @Test
    public void test3_is_false_forZ() {
        input = new MockInput(2);
        assertEquals("D", input.inputValidation_RD("Z"));
    }
    public void test6_is_false_forZ() {
        input = new MockInput(3);
        assertEquals("R", input.inputValidation_RD("Z"));
    }

    @Test
    public void test_is_true_forY() {
        assertEquals("Y", input.inputValidation_YN("Y"));
    }
    @Test
    public void test_is_true_forN() {
        assertEquals("N", input.inputValidation_YN("N"));
    }
    @Test
    public void test4_is_false_forZ() {
        input = new MockInput(4);
        assertEquals("Y", input.inputValidation_YN("Z"));
    }
    public void test5_is_false_forZ() {
        input = new MockInput(5);
        assertEquals("N", input.inputValidation_YN("Z"));
    }
    @Test
    public void test_numPlayers_2_valid(){

        assertEquals(2, input.inputValidation_NumPlayer(2));
    }
    @Test
    public void test_numPlayers_1_ivalid(){
        input = new MockInput(3);
        assertEquals(3, input.inputValidation_NumPlayer(1));
    }

    @Test
    public void test_maxPoints_1300_valid(){
        assertEquals(1300, input.inputValidation_MaxScore(1300));
    }

    @Test
    public void test_maxPoints_3_invalid(){
        input = new MockInput(1300);
        assertEquals(1300, input.inputValidation_MaxScore(3));
    }

    @Test
    public void test_players_not_taken() {
        String name = "name";
        TreeMap<String, Integer> names = new TreeMap<String, Integer> ();
        assertEquals(name, input.inputValidation_Players(name, names));
    }

    @Test
    public void test_players_taken() {
        input = new MockInput(3);
        String name = "name";
        TreeMap<String, Integer> names = new TreeMap<String, Integer> ();
        names.put(name,0);
        assertEquals("R", input.inputValidation_Players(name, names));
    }
}