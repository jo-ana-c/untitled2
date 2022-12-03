package Inputs;

import java.util.Scanner;

public class Input extends AbstractInput {

    public int askIntegerInput() {
        Scanner sc = new Scanner(System.in);
        int number;
        while (! sc.hasNextInt()) {
            System.out.println("Input must be an integer.");
            sc.next();
        }
        number = sc.nextInt();
        return number;
    }

    public String askStringInput() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }
}

