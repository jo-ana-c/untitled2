package Games;
import Decks.Deck;
import Inputs.Input;
import Referees.Referee;

import java.util.ArrayList;


public final class Game {
    private final int numPlayers;
    private final ArrayList<String> names;
    private final int maxPoints;
    private final Referee referee;

    public Input inputObject = new Input();

    private final Deck deck = new Deck();

    public Game() {
        System.out.println("LET'S PLAY A GAME OF TUTTO!\n");
        System.out.println("How many Players (2-4) are playing? ");
        this.numPlayers = inputObject.inputValidation_NumPlayer(inputObject.askIntegerInput());
        this.names = new ArrayList<>(this.numPlayers);
        System.out.println("What is your maximum Score?");
        this.maxPoints = inputObject.inputValidation_MaxScore(inputObject.askIntegerInput());
        for (int i = 0; i < this.numPlayers; i++) {
            System.out.println("Please enter username of player " + (i+1) + ":");
            String nameInput = inputObject.inputValidation_Players(inputObject.askStringInput(), this.names);
            this.names.add(nameInput);}
        this.referee = new Referee(this.maxPoints, this.names);
        }
}
