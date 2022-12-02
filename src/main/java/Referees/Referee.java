package Referees;

import Inputs.AbstractInput;
import Inputs.Input;

public class Referee {
    AbstractInput inputObject = new Input();

    public boolean askEndTurn() {
        System.out.println("Would you like to end the turn or continue by drawing another card?");
        System.out.println("Enter E to end the turn or D to draw another card.");
        String input = inputObject.inputValidation_ED(inputObject.askStringInput());
        return (input.equals("E"));
    }

    public boolean askRollOrDisplay(){
        System.out.println("Would you like to display the score or roll the dice?");
        System.out.println("Enter D for display or R for roll.");
        String input = inputObject.inputValidation_RD(inputObject.askStringInput());
        return (input.equals("D"));
    }
}
