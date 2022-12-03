package Turns;

import TurnResults.TurnResult;

public final class X2Turn extends AbstractTurn{

    public X2Turn (TurnResult tr) {
        super(tr);
    }

    @Override
    protected void tuttoPoints(TurnResult tr) {
        tr.setPoints(tempPoints*2 + tr.getPoints());
        tr.setNewCard(true);
    }

    @Override
    protected boolean rollAgain() {
        System.out.println("Would you like to roll the dice again? Enter R to roll again, E to end the turn:");
        String input = inputObject.inputValidation_ER(inputObject.askStringInput());
        tr.setNewCard(input.equals("R"));
        return (input.equals("R"));
    }
}
