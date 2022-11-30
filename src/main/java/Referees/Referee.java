package Referees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;
import Decks.Deck;
import java.util.Map.Entry;
import Cards.*;
import TurnResults.TurnResult;

public final class Referee {

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

    public void gameFlow(){
        while(onGoing()) {

            for (Entry<String, Integer> PlayerAtTurn: players.entrySet()){
                System.out.println("\n\n**************** It's " + PlayerAtTurn.getKey() + "'s turn! ****************\n");
                askRollOrDisplay();
                TurnResult tr = new TurnResult();
                // While player is playing, reaching tutto and drawing a new card

                while(true){
                    AbstractCard drawnCard = deck.draw();
                    tr = drawnCard.initTurn(tr);
                    if (!tr.getNewCard()){break;}
                    else if (tr.getCloverleaf() != 2 && askEndTurn()){break;}
                }
                // if Cloverleaf and 2xTutto = Game finished and player won
                if(tr.getCloverleaf() == 2){
                    declareWinner(PlayerAtTurn.getKey());
                    PlayerAtTurn.setValue(maxScore);
                    break;
                }

                // if plusminus was drawn and player at turn is not leading, apply minus rule to leading players
                if(tr.getPlusMinus() > 0 && !getLeadingPlayers().contains(PlayerAtTurn.getKey())){
                    for (Entry<String, Integer> leadingEntry: players.entrySet()){
                        if (getLeadingPlayers().contains(leadingEntry.getKey())){
                            players.put(leadingEntry.getKey(), leadingEntry.getValue()-(tr.getPlusMinus()*1000));
                        }

                    }
                    // receive 1000 * times PlusminusCard Drawn for player at turn
                    players.put(PlayerAtTurn.getKey(), PlayerAtTurn.getValue()+(tr.getPlusMinus()*1000));
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
    public void askRollOrDisplay(){
        System.out.println("Would you like to display the score or roll the dice?");
        System.out.println("Enter D for display or R for roll.");
        Scanner askRollorDisplay = new Scanner(System.in);
        String rodInput = askRollorDisplay.next();
        // Input validation
        while(!inputValidation_RD(rodInput)){
            System.out.println("Invalid input. Try again.");
            rodInput = askRollorDisplay.next();
        }
        while(rodInput.equals("D")){
            // display points and ask again
            displayPoints();
            System.out.println();
            System.out.println("Would you still like to display the score or continue by rolling the dice?");
            // new Input Validation
            rodInput = askRollorDisplay.next();
            while(!inputValidation_RD(rodInput)){
                System.out.println("Invalid input. Try again.");
                rodInput = askRollorDisplay.next();
            }
        }
    }

    public boolean askEndTurn(){
        System.out.println("Would you like to end the turn or continue by rolling the dice again?");
        System.out.println("Enter E to end the turn or R to roll the dice again.");
        Scanner askRollorEnd = new Scanner(System.in);
        String roeInput = askRollorEnd.next();
        // ask for Input and validate
        while(!inputValidation_RE(roeInput)){
            System.out.println("Invalid Input. Try again.");
            roeInput = askRollorEnd.next();
        }
        return (roeInput.equals("E"));

    }

    public boolean inputValidation_RD(String inputPlayer){
        return (inputPlayer.equals("D") || inputPlayer.equals("R"));}

    public boolean inputValidation_RE(String inputPlayer){
        return (inputPlayer.equals("E") || inputPlayer.equals("R"));}



    public boolean onGoing(){
        return (Collections.max(players.values()) < this.maxScore);
    }
    //public void initTurn(){}

    //public void updatePlayerScore(String PlayerAtTurn, TurnResult tr){}

    public ArrayList<String> getLeadingPlayers(){

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
    //Fields: tempScores; Deck; Hashmap (Name, Points)
    //Methods: initTurn(); updatePlayerScore(); getLeadingPlayer(); getMaxPoints();

    public void declareWinner(String Winner){
        System.out.println(Winner + "won the game!");
        displayPoints();
    }

    public void displayPoints(){
        System.out.println("SCOREBOARD:");
        for (Entry<String, Integer> FinalPoints: players.entrySet()){
            System.out.print(FinalPoints.getKey() + ": " + FinalPoints.getValue() + " points" );
            System.out.println();
        }

    }


}
