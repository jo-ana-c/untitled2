package Cards;

import TurnResults.TurnResult;
import Turns.PlusMinusTurn;

public final class PlusMinusCard extends AbstractCard{
    public final String name = "Plus-Minus Card";

    @Override
    public TurnResult initTurn(TurnResult tr) {
        PlusMinusTurn pt = new PlusMinusTurn(tr);
       return pt.templateTurn();
    }
}
