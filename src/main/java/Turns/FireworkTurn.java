package Turns;
import TurnResults.TurnResult;

import java.util.ArrayList;
import java.util.HashMap;

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
    protected void tuttoPoints(TurnResult tr) {
        tr.setFirework(true);
    }

    @Override
    protected boolean selectTriplets() {
        boolean selected = false;

        while (!selected) {
            HashMap<Integer, Integer> occurrences = populateHashmap(this.dice);
            ArrayList<Integer> triplets = triplets(occurrences);

            if (!triplets.isEmpty()) {
                for (int value : triplets){
                    this.dice.selectTripleDice(value);
                    System.out.println("You rolled a triplet of " + value + "s!\n");
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
    protected boolean selectSingles(int value, int points) {
        boolean selected = false;
        HashMap<Integer, Integer> occurrences = populateHashmap(this.dice);
        if (occurrences.containsKey(value)) {
            tempPoints += occurrences.get(value)*points;
            for (int i=0; i < occurrences.get(value); i++){
                selected = dice.selectSingleDice(value);
                }
            System.out.println("You rolled " + occurrences.get(value) + " x " + value + "s!\n");
            }

            delay(2000);
        return selected;
    }
}
