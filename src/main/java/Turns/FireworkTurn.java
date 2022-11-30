package Turns;

import DiePackage.Die;
import TurnResults.TurnResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public final class FireworkTurn extends AbstractTurn{

   public FireworkTurn(TurnResult tr){
       super(tr);
   }

    @Override
    protected void handleNull(){
        tr.setPoints(tr.getPoints() + tempPoints);
        tr.setNewCard(false);
    }

    @Override
    void tuttoPoints(TurnResult tr) {
        return;
    }

    @Override
    boolean selectTriplets() {
        boolean selected = false;

        while (!selected) {
            HashMap<Integer, Integer> occurrences = populateHashmap(this.dice);
            ArrayList<Integer> triplets = triplets(occurrences);

            if (!triplets.isEmpty()) {
                for (int value : triplets){
                    dice.selectTripleDice(value);
                    System.out.println("You rolled a triplet of " + String.valueOf(value) + "s!\n");
                    delay(2000);
                    if (value == 1){this.tempPoints += 1000;}
                    else {this.tempPoints += value*100;}
                    selected = true;
                }
            }
            else {return selected;}
        }
        return selected;
   }

    @Override
    boolean selectSingles(int value, int points) {
        boolean selected = false;
        HashMap<Integer, Integer> occurrences = populateHashmap(this.dice);
        if (occurrences.containsKey(value)) {
            tempPoints += occurrences.get(value)*points;
            for (int i=0; i < occurrences.get(value); i++){
                for (Die d : dice){
                    if (!d.isSelected() && d.getValue() == value){
                        d.select();
                        selected = true;
                        break;
                    }
                }
            }
            System.out.println("You rolled " + occurrences.get(value) + " x " + value + "s!\n");
            delay(2000);
        }
        return selected;
    }
}
