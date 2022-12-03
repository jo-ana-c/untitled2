package Games;
import Cards.AbstractCard;
import Decks.Deck;
import Inputs.Input;
import Decisions.Decision;
import TurnResults.TurnResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;


public class Game {
    //private final int numPlayers;
    private final int maxPoints;

    private final TreeMap<String, Integer> players = new TreeMap<>();

    public Input inputObject = new Input();

    //private final Decision decision = new Decision();

    private final Deck deck = new Deck();

    public Game() {
        System.out.println("LET'S PLAY A GAME OF TUTTO!\n");
        System.out.println("How many Players (2-4) are playing? ");
        int numPlayers = inputObject.inputValidation_NumPlayer(inputObject.askIntegerInput());
        System.out.println("What is your maximum Score?");
        this.maxPoints = inputObject.inputValidation_MaxScore(inputObject.askIntegerInput());
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Please enter username of player " + (i+1) + ":");
            String nameInput = inputObject.inputValidation_Players(inputObject.askStringInput(), this.players);
            this.players.put(nameInput,0);}
        gameFlow();
    }
    private void gameFlow(){
        while(onGoing()) {

            for (Map.Entry<String, Integer> PlayerAtTurn: players.entrySet()){
                System.out.println("\n******************* It's " + PlayerAtTurn.getKey() + "'s turn! *******************\n");
                while(Decision.askRollOrDisplay()) {displayPoints();}
                TurnResult tr = new TurnResult();
                // While player is playing, reaching tutto and drawing a new card

                while(true){
                    AbstractCard drawnCard = deck.draw();
                    tr = drawnCard.initTurn(tr);
                    if (!tr.getNewCard()){break;}
                    else if (tr.getCloverleaf() != 2 && Decision.askEndTurn()) {break;}
                }
                // if Cloverleaf and 2xTutto = Game finished and player won
                if(tr.cloverleafWon()){
                    //declareWinner(PlayerAtTurn.getKey());
                    PlayerAtTurn.setValue(this.maxPoints);
                    //break;
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

    private boolean onGoing(){
        return (Collections.max(players.values()) < this.maxPoints);
    }

    protected ArrayList<String> getLeadingPlayers(){

        // get maxScore
        int max = (Collections.max(players.values()));

        // Iterate through TreeMap
        ArrayList<String> leadingPlayers = new ArrayList<>();

        // add players with maxScore to arraylist
        for (Map.Entry<String, Integer> Entry: players.entrySet()){
            if (Entry.getValue() == max) {
                leadingPlayers.add(Entry.getKey());
            }
        }
        return leadingPlayers;
    }

    private void declareWinner(String winner){
        System.out.println(winner + " won the game!");
        displayPoints();
    }

    private void displayPoints(){
        System.out.println("SCOREBOARD");
        for (Map.Entry<String, Integer> FinalPoints: players.entrySet()){
            System.out.print(FinalPoints.getKey() + ": " + FinalPoints.getValue() + " points" );
            System.out.println();
        }
    }


}
