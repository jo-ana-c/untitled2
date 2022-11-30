package Turns;


import DicePackage.Dice;
import DiePackage.*;
import TurnResults.TurnResult;
import java.util.HashMap;
import java.util.Scanner;

public final class BonusTurn extends AbstractTurn{
    int bonus;
    public BonusTurn (TurnResult tr, int bonus) {
        super(tr);
        this.bonus = bonus;
    }

    @Override
    void tuttoPoints(TurnResult tr) {
        tr.setPoints(tr.getPoints() + tempPoints + bonus);
    }

}

