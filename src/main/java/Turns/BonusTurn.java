package Turns;


import TurnResults.TurnResult;

public final class BonusTurn extends AbstractTurn{
    private final int bonus;
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
        System.out.println("Would you like to roll the dice again? Enter R to roll again, E to end the turn:");
        String input = inputObject.inputValidation_ER(inputObject.askStringInput());
        tr.setNewCard(input.equals("R"));
        return (input.equals("R"));
    }

}

