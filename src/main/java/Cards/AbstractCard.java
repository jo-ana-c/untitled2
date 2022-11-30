package Cards;

import TurnResults.TurnResult;

public abstract class AbstractCard {
    public String name;

    public abstract TurnResult initTurn(TurnResult tr);
}
