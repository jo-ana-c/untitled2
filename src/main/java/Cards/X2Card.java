package Cards;

import TurnResults.TurnResult;
import Turns.X2Turn;

public final class X2Card extends AbstractCard{
    public final String name = "x2 Card";

    @Override
    public TurnResult initTurn(TurnResult tr) {
        X2Turn xt = new X2Turn(tr);
        return xt.templateTurn();
    }
}
