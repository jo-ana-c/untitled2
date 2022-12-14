package Cards;

import TurnResults.TurnResult;
import Turns.CloverleafTurn;

public final class CloverleafCard extends AbstractCard{

    @Override
    public TurnResult initTurn(TurnResult tr) {
        CloverleafTurn ct = new CloverleafTurn(tr);
        return ct.templateTurn();
    }
}
