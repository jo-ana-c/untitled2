package DicePackage;
import java.util.ArrayList;
import DiePackage.Die;
import java.util.*;
import java.lang.Iterable;

public final class Dice implements Iterable<Die>{
    private ArrayList<Die> dice = new ArrayList<>(6);
    public Dice() {
        for (int i = 0; i < 6; i++) {
            this.dice.add(new Die());
        }
    }
    @Override
    public Iterator<Die> iterator(){
        Iterator<Die> iter = new Iterator(){
             private int currentIndex = 0;
             @Override
             public boolean hasNext(){
                 return currentIndex < 6;
             }
             @Override
             public Die next(){
                 if (hasNext()) return dice.get(currentIndex++);
                 else throw new UnsupportedOperationException("Not supported");
             }
        };
        return iter;
    }

    public Dice rollDice() {
        for (Die d : dice) {
            if (! d.isSelected()) {d.rollDie();}
        }
        //not sure if we need to return it
        return this;
    }

    public void displayDice(){
        // Show selected
        String selected = "|| The following dice are selected: ";
        String non_selected = "|| No dice selected yet ";
        boolean at_least_one_selected = false;
        for (Die d : dice) {
            if (d.isSelected()) {
                if (! at_least_one_selected){
                    System.out.print(selected);
                    at_least_one_selected = true;
                }
                System.out.print(d.getValue()+ " ");

            }

        }


        if (!at_least_one_selected){
            System.out.print(non_selected);
        }
        System.out.println("||");
        // Show unselected
        System.out.print("|| Rolled dice: ");
        for (Die d : dice) {
            if (! d.isSelected()) {
                System.out.print(d.getValue()+ " ");
            }
        }
        System.out.println("||");
        System.out.println();
    }

    public void selectSingleDice(int value){
        for (Die d : dice) {
            if (d.getValue() == value) {d.select();break;}
        }
    }

    public void selectTripleDice(int value){
        int counter = 0;
        for (Die d : dice) {
            if (d.getValue() == value) {
                d.select(); counter += 1;
                if (counter == 3){break;}
            }
        }
    }

}
