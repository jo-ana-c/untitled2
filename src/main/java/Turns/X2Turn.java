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
}
