package Inputs;

public abstract class AbstractInput {

    public abstract int askIntegerInput();

    public abstract String askStringInput();

    public String inputValidation_RD(String input){
        if (! input.equals("D") && !input.equals("R")) {
            System.out.println("Invalid Input. Try again.");
            return inputValidation_RD(askStringInput());
        }
        else return input;
    }

    public String inputValidation_ED(String input){
        if (! input.equals("D") && !input.equals("E")) {
            System.out.println("Invalid Input. Try again.");
            return inputValidation_ED(askStringInput());
        }
        else return input;
    }

    public String inputValidation_ER(String input){
        if (! input.equals("R") && !input.equals("E")) {
            System.out.println("Invalid Input. Try again.");
            return inputValidation_ER(askStringInput());
        }
        else return input;
    }

    public String inputValidation_YN(String input){
        if (! input.equals("Y") && !input.equals("N")) {
            System.out.println("Invalid Input. Try again.");
            return inputValidation_YN(askStringInput());
        }
        else return input;
    }
}
