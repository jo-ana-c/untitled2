package Cards;

import TurnResults.TurnResult;
import Turns.BonusTurn;

public final class BonusCard extends AbstractCard{
    public final int bonus;

    public BonusCard (int bonus){
        this.bonus = bonus;
    }

    @Override
    public TurnResult initTurn(TurnResult tr) {
        BonusTurn bt = new BonusTurn(tr, this.bonus);
        return bt.templateTurn();
    }
}
