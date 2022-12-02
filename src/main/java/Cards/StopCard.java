package Cards;

import TurnResults.TurnResult;

public final class StopCard extends AbstractCard{

    @Override
    public TurnResult initTurn(TurnResult tr) {
        tr.setNewCard(false);
        tr.setPoints(0);
        return tr;
    }
}
