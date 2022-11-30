package Cards;

import TurnResults.TurnResult;
import Turns.FireworkTurn;

public final class FireworkCard extends AbstractCard{
    public final String name = "Firework Card";

    @Override
    public TurnResult initTurn(TurnResult tr) {
        FireworkTurn ft = new FireworkTurn(tr);
        return ft.templateTurn();
    }
}
