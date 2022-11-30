package Cards;

import TurnResults.TurnResult;

public final class StopCard extends AbstractCard{
    public final String name = "Stop Card";

    @Override
    public TurnResult initTurn(TurnResult tr) {
        tr.setNewCard(false);
        tr.setPoints(0);
        return tr;
    }
}
