package Turns;

import TurnResults.TurnResult;

import java.util.Scanner;

public final class X2Turn extends AbstractTurn{

    public X2Turn (TurnResult tr) {
        super(tr);
    }

    @Override
    void tuttoPoints(TurnResult tr) {
        tr.setPoints(tempPoints*2 + tr.getPoints());
    }

    @Override
    protected boolean rollAgain() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to roll the dice again?\nEnter R to roll again, E to end the turn:");
        String input = sc.next();
        while (!(input.equals("R") || input.equals("E"))){
            System.out.println("Invalid input! Enter R to roll again or E to end the turn:");
            input = sc.next();
        }
        return (input.equals("R"));
    }
}
