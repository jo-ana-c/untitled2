package Turns;

import TurnResults.TurnResult;

public final class PlusMinusTurn extends AbstractTurn{

    public PlusMinusTurn (TurnResult tr) {
        super(tr);
    };

    @Override
    void tuttoPoints(TurnResult tr) {
        tr.setPlusMinus(tr.getPlusMinus() + 1);
    }
}
