package Referees;

import Cards.AbstractCard;
import Decks.Deck;
import Inputs.AbstractInput;
import Inputs.Input;
import TurnResults.TurnResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.TreeMap;

public final class Referee {

    protected AbstractInput inputObject = new Input();
    private final int maxScore;
    private final TreeMap<String, Integer> players = new TreeMap<>();
    private final Deck deck = new Deck();

    public Referee(int maxScore, ArrayList<String> Players){
        this.maxScore = maxScore;
        for (String p:Players){
            this.players.put(p, 0);
        }
        gameFlow();
    }

    private void gameFlow(){
        while(onGoing()) {

            for (Entry<String, Integer> PlayerAtTurn: players.entrySet()){
                System.out.println("\n******************* It's " + PlayerAtTurn.getKey() + "'s turn! *******************\n");
                askRollOrDisplay();
                TurnResult tr = new TurnResult();
                // While player is playing, reaching tutto and drawing a new card

                while(true){
                    AbstractCard drawnCard = deck.draw();
                    tr = drawnCard.initTurn(tr);
                    if (!tr.getNewCard()){break;}
                    else if (tr.getCloverleaf() != 2 && askEndTurn()) {break;}
                }
                // if Cloverleaf and 2xTutto = Game finished and player won
                if(tr.getCloverleaf() == 2){
                    declareWinner(PlayerAtTurn.getKey());
                    PlayerAtTurn.setValue(maxScore);
                    break;
                }

                // if plusminus was drawn and player at turn is not leading, apply minus rule to leading players
                if (tr.getPlusMinus() > 0) {
                    for (String player : getLeadingPlayers()) {
                        if (!player.equals(PlayerAtTurn.getKey())) {
                            players.put(player, players.get(player)-(tr.getPlusMinus()*1000));
                        }
                        else {
                            players.put(player, players.get(player)+(tr.getPlusMinus()*1000));
                        }
                    }
                }
                // receive points reached with other cards
                players.put(PlayerAtTurn.getKey(), PlayerAtTurn.getValue()+tr.getPoints());
                if (!onGoing()){
                    declareWinner(PlayerAtTurn.getKey());
                    break;
                }

            }

        }
    }

    private boolean askEndTurn(){
        System.out.println("Would you like to end the turn or continue by drawing another card?");
        System.out.println("Enter E to end the turn or D to draw another card.");
        String input = inputObject.inputValidation_ED(inputObject.askStringInput());
        return (input.equals("E"));
    }

    private void askRollOrDisplay(){
        System.out.println("Would you like to display the score or roll the dice?");
        System.out.println("Enter D for display or R for roll.");
        String input = inputObject.inputValidation_RD(inputObject.askStringInput());
        while(input.equals("D")){
            // display points and ask again
            displayPoints();
            System.out.println();
            System.out.println("Would you still like to display (D) the score or continue by rolling (R) the dice?");
            // new Input Validation
            input = inputObject.inputValidation_RD(inputObject.askStringInput());
        }
    }

    private boolean onGoing(){
        return (Collections.max(players.values()) < this.maxScore);
    }

    private ArrayList<String> getLeadingPlayers(){

        // get maxScore
        int max = (Collections.max(players.values()));

        // Iterate through TreeMap
        ArrayList<String> leadingPlayers = new ArrayList<>();

        // add players with maxScore to arraylist
        for (Entry<String, Integer> Entry: players.entrySet()){
            if (Entry.getValue() == max) {
                leadingPlayers.add(Entry.getKey());
            }
        }
        return leadingPlayers;
    }

    private void declareWinner(String Winner){
        System.out.println(Winner + " won the game!");
        displayPoints();
    }

    private void displayPoints(){
        System.out.println("SCOREBOARD");
        for (Entry<String, Integer> FinalPoints: players.entrySet()){
            System.out.print(FinalPoints.getKey() + ": " + FinalPoints.getValue() + " points" );
            System.out.println();
        }

    }

}
