package Games;
import Referees.Referee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public final class Game {
    final private int numPlayers;
    final private ArrayList<String> names;
    final private int maxPoints;
    private Referee referee;

    public Game() {
        System.out.println("LET'S PLAY A GAME OF TUTTO!\n");
        this.numPlayers = setNumPlayers();
        this.names = new ArrayList<>(this.numPlayers);
        this.maxPoints = setMaxScore();
        setPlayers();
        this.referee = new Referee(this.maxPoints, this.names);
    }

    private static boolean validation(String c, ArrayList<String> array) {
        for (String x : array) {
            if (x.equals(c)) {
                return true;
            }
        }
        return false;
    }

    private int setNumPlayers() {
        ArrayList<String> validInput = new ArrayList<>(Arrays.asList("2", "3", "4"));
        Scanner scannerNumPlayers = new Scanner(System.in);
        System.out.println("How many Players (2-4) are playing? ");
        String numPlayersInput = scannerNumPlayers.nextLine();
        if(validation(numPlayersInput, validInput)) {
            return Integer.parseInt(numPlayersInput);
        }
        else {
            System.out.println("Input must be a single digit between 2 and 4.");
            return setNumPlayers();
        }
    }

    private int setMaxScore() {
        Scanner scannerScore = new Scanner(System.in);
        System.out.println("What is your maximum Score?");
        int maxScoreInput;
        do {
            System.out.println("Maximum score must be between 1000 and 10000.");
            while (!scannerScore.hasNextInt()) {
                System.out.println("Invalid Input!");
                scannerScore.next();
            }
            maxScoreInput = scannerScore.nextInt();
        } while (maxScoreInput < 1000 || maxScoreInput > 10000);
        return maxScoreInput;
    }

    private void setPlayers() {
        Scanner scannerPlayers = new Scanner(System.in);
        for (int i = 0; i < this.numPlayers; i++) {
            System.out.println("Please enter username of player " + (i+1) + ":");
            String nameInput = scannerPlayers.next();

            while (validation(nameInput, this.names)) {

                System.out.println("Username taken! Please choose another one.");
                nameInput = scannerPlayers.next();
            }
            this.names.add(nameInput);
        }

    }
}
