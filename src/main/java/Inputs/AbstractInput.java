package Inputs;

import java.util.ArrayList;

public abstract class AbstractInput {

    public abstract int askIntegerInput();

    public abstract String askStringInput();

    public String inputValidation_RD(String input){
        if (! input.equals("D") && !input.equals("R")) {
            System.out.println("Invalid Input. Try again.");
            return inputValidation_RD(askStringInput());
        }
        return input;
    }

    public String inputValidation_ED(String input){
        if (! input.equals("D") && !input.equals("E")) {
            System.out.println("Invalid Input. Try again.");
            return inputValidation_ED(askStringInput());
        }
        return input;
    }

    public String inputValidation_ER(String input){
        if (! input.equals("R") && !input.equals("E")) {
            System.out.println("Invalid Input. Try again.");
            return inputValidation_ER(askStringInput());
        }
        return input;
    }

    public String inputValidation_YN(String input){
        if (! input.equals("Y") && !input.equals("N")) {
            System.out.println("Invalid Input. Try again.");
            return inputValidation_YN(askStringInput());
        }
        return input;
    }

    public int inputValidation_NumPlayer(int input) {
        if (input < 2 || input > 4) {
            System.out.println("Input must be a single digit between 2 and 4.");
            return inputValidation_NumPlayer(askIntegerInput());
        }
        return input;
    }

    public int inputValidation_MaxScore(int input) {
        if (input < 1000 || input > 10000) {
            System.out.println("Input must be an integer between 1'000 and 10'000.");
            return inputValidation_MaxScore(askIntegerInput());
        }
        return input;
    }

    public String inputValidation_Players(String input, ArrayList<String> array) {
        for (String x : array) {
            if (x.equals(input)) {
                System.out.println("Username taken! Please choose another one.");
                return inputValidation_Players(askStringInput(), array);
            }
        }
        return input;
    }
}
