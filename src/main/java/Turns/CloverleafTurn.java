package Turns;

import TurnResults.TurnResult;

public final class CloverleafTurn extends AbstractTurn{


    public CloverleafTurn(TurnResult tr) {
        super(tr);
    }

    @Override
    protected void tuttoPoints(TurnResult tr) {
        tr.setCloverleaf(tr.getCloverleaf() + 1);
    }
}
