package Cards;

import TurnResults.TurnResult;
import Turns.FireworkTurn;

public final class FireworkCard extends AbstractCard{

    @Override
    public TurnResult initTurn(TurnResult tr) {
        FireworkTurn ft = new FireworkTurn(tr);
        return ft.templateTurn();
    }
}
