package Turns;

import TurnResults.TurnResult;

public final class CloverleafTurn extends AbstractTurn{


    public CloverleafTurn(TurnResult tr) {
        super(tr);
    }

    @Override
    void tuttoPoints(TurnResult tr) {
        tr.setCloverleaf(tr.getCloverleaf() + 1);
    }

    @Override
    protected boolean rollAgain() {
        return true;
    }
}
