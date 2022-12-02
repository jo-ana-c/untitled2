package Turns;


import TurnResults.TurnResult;

import java.util.Scanner;

public final class BonusTurn extends AbstractTurn{
    private int bonus;
    public BonusTurn (TurnResult tr, int bonus) {
        super(tr);
        this.bonus = bonus;
    }

    @Override
    protected void tuttoPoints(TurnResult tr) {
        tr.setPoints(tr.getPoints() + tempPoints + bonus);
        tr.setNewCard(true);
    }

    @Override
    protected boolean rollAgain() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to roll the dice again? Enter R to roll again, E to end the turn:");
        String input = sc.next();
        while (!(input.equals("R") || input.equals("E"))){
            System.out.println("Invalid input! Enter R to roll again or E to end the turn:");
            input = sc.next();
        }
        tr.setNewCard(input.equals("R"));
        return (input.equals("R"));
    }

}

