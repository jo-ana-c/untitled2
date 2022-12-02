package Cards;

import TurnResults.TurnResult;
import Turns.StraightTurn;

public final class StraightCard extends AbstractCard{

    @Override
    public TurnResult initTurn(TurnResult tr) {
        StraightTurn st = new StraightTurn(tr);
        return st.templateTurn();
    }
}
