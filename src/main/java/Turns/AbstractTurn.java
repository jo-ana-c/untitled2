package Turns;
import DicePackage.*;
import TurnResults.TurnResult;
import DiePackage.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

import java.util.HashMap;

public abstract class AbstractTurn {
    Dice dice = new Dice();
    TurnResult tr;
    int tempPoints = 0;

    protected AbstractTurn(TurnResult tr){
        this.tr = tr;
    }

    public final TurnResult templateTurn() {

        do {
            System.out.println("Let's roll the dice!...");
            delay(2000);
            dice.rollDice();
            dice.displayDice();
            if (nullThrow()){
                handleNull(); //points and plusminus to 0 as default implementation
                return tr;
            }
            selectDice();
            if (isTutto()){
                System.out.println("TUTTO!\n");
                tuttoPoints(tr); // modify TR after tutto
                if (tr.getFirework() || tr.getCloverleaf() == 1) {
                    this.dice = new Dice();
                }
                return tr;
            }
            else{
                tr.setPoints(tr.getPoints() + tempPoints);}
        } while (rollAgain());
        return tr;
    }

    public boolean isTutto(){
        for (Die d : this.dice){
            if (!d.isSelected()){
                return false;
            }
        }
        return true;
    }

    // decides if a throw is null as default implementation for all cards except straight
    protected boolean nullThrow() {
        HashMap<Integer, Integer> occurrences = populateHashmap(this.dice);
        for (int k : occurrences.keySet()){
            if (k == 1 || k == 5){
                return false;
            }
            else if (occurrences.get(k) >= 3){
                return false;
            }
        }
        System.out.println("NULL!\n");
        return true;
    }

    // this method is called after we already know that the dice-roll was not null. it calls the methods
    // select singles and triplets until any dice was removed. this method, as well as selectTriplets()
    // and select singles() is the default implementation for the majority of cards but has to be overridden by some
    void selectDice(){
        boolean anyDiceSelected =  false;
        while (!anyDiceSelected){
            boolean triplets = selectTriplets();
            boolean ones = selectSingles(1, 100);
            boolean fives = selectSingles(5, 50);
            anyDiceSelected = triplets || ones || fives;
        }
    }

    // player selects triplets iteratively. method returns true if any triplet was selected
    boolean selectTriplets() {
        boolean selected = false;

        while (true) {
            HashMap<Integer, Integer> occurrences = populateHashmap(this.dice);
            ArrayList<Integer> triplets = triplets(occurrences);

            if (!triplets.isEmpty()) {
                System.out.print("You can select triplets for the following dice values: ");
                for (int nr : triplets) {
                    System.out.print(nr + "   ");
                }
                System.out.println();
                System.out.print("Would you like to select a triplet? If yes, enter its die value. If no, enter 0: ");

                int input = validateInput();

                while (!triplets.contains(input) && input != 0) {
                    System.out.println("There is no triplet of value " + String.valueOf(input) + ".\nPlease enter a valid triplet value or a 0, in case you do not want to select one.");
                    input = validateInput();
                }




                if (input != 0){
                    dice.selectTripleDice(input);
                    System.out.println(tempPoints);
                    if (input == 1){this.tempPoints += 1000;}
                    else {this.tempPoints += input*100;}
                    selected = true;
                    System.out.println(tempPoints);
                }
                else {break;}
            }





            else {
                System.out.println("There are no triplets to select.\n");
                break;
            }
        }
        return selected;
    }

    // Player selects single occurrence dice he wants to select; method returns true if any die was selected
    // takes 2 arguments value & points so that we can use the same method for ones and fives
    boolean selectSingles(int value, int points){
        boolean selected = false;

        HashMap<Integer, Integer> occurrences = populateHashmap(this.dice);

        if (occurrences.containsKey(value)) {
        System.out.println("You can select " + occurrences.get(value) +" x " + String.valueOf(value) + "s.");
            System.out.println("How many do you want to select? In case you do not want to select any, enter 0.");

            int number = validateInput();

            while (number > occurrences.get(value) || number < 0) {
                System.out.println("There are only " + occurrences.get(value) + " x " + String.valueOf(value) + "s.\n" + "Select a valid number:\n");
                number = validateInput();
            }

            System.out.println(tempPoints);
            System.out.println(number*points);
            tempPoints += number*points;
            System.out.println(tempPoints);

            for (int i=0; i < number; i++){
                for (Die d : dice){
                    if (!d.isSelected() && d.getValue() == value){
                        d.select();
                        selected = true;
                        break;
                    }
                }
            }
        }
        return selected;
    }
    protected int occurrencesCount(Dice dice, int nr){
        int occurrences = 0;
        for (Die d: dice){
            if (!d.isSelected() && d.getValue() == nr){occurrences += 1;}
        }
        return occurrences;
    }

    //fill hashmap: for all unselected dice, key = dice number, value = occurrences
    protected HashMap<Integer, Integer> populateHashmap(Dice dice){
        HashMap<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (Die d : dice){
            if (!d.isSelected() && !occurrences.containsKey(d.getValue())){
                occurrences.put(d.getValue(), occurrencesCount(dice, d.getValue()));
            }
        }
        return occurrences;
    }

    private int validateInput() {
        Scanner sc = new Scanner(System.in);
        int number;
        while (! sc.hasNextInt()) {
            System.out.println("Input must be an integer.");
            sc.next();
        }
        number = sc.nextInt();
        return number;
    }

    // create list from hashmap: list contains die numbers that are triplets
    protected ArrayList<Integer> triplets(HashMap<Integer, Integer> occurrences){
        ArrayList<Integer> triplets = new ArrayList<>();
        for (int key : occurrences.keySet()) {
            if (occurrences.get(key) >= 3){
                triplets.add(key);
            }
        }
        return triplets;
    }

    protected void handleNull(){
        tr.setPoints(0);
        tr.setCloverleaf(0);
        tr.setPlusMinus(0);
        tr.setNewCard(false);
    }

    protected boolean rollAgain() {return true;}
    abstract void tuttoPoints(TurnResult tr);

    protected void delay(int time){
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException e) {e.printStackTrace();}
    }
}
