package Referees;

import Inputs.AbstractInput;
import org.junit.jupiter.api.Test;

import java.sql.Ref;

import static org.junit.jupiter.api.Assertions.*;

class RefereeTest {

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

    Referee r = new Referee();

    @Test
    public void test_askEnd(){
        MockInput mi = new MockInput(1);
        r.inputObject = mi;
        assertTrue(r.askEndTurn());
    }

    @Test
    public void test_askDraw(){
        MockInput mi = new MockInput(2);
        r.inputObject = mi;
        assertFalse(r.askEndTurn());
    }

    @Test
    public void test_askDisplay(){
        MockInput mi = new MockInput(2);
        r.inputObject = mi;
        assertTrue(r.askRollOrDisplay());
    }

    @Test
    public void test_askRoll(){
        MockInput mi = new MockInput(3);
        r.inputObject = mi;
        assertFalse(r.askRollOrDisplay());
    }

}