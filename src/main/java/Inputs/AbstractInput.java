package Inputs;

public abstract class AbstractInput {

    public abstract int askIntegerInput();

    public abstract String askStringInput();

    public String inputValidation_RD(String input){
        if (! input.equals("D") && !input.equals("R")) {
            System.out.println("Invalid Input. Try again.");
            return askStringInput();
        }
        else return input;
    }

    public String inputValidation_ED(String input){
        if (! input.equals("D") && !input.equals("E")) {
            System.out.println("Invalid Input. Try again.");
            return askStringInput();
        }
        else return input;
    }
}
