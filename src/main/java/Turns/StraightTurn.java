package Turns;

import DiePackage.Die;
import TurnResults.TurnResult;

import java.util.ArrayList;

public final class StraightTurn extends AbstractTurn {

    public StraightTurn(TurnResult tr) {
        super(tr);
    }

    @Override
    protected boolean nullThrow() {
        ArrayList<Integer> selectedDice = new ArrayList<>();
        ArrayList<Integer> thrownDice = new ArrayList<>();

        for (Die d : dice) {
            if (d.isSelected()) {
                selectedDice.add(d.getValue());
            } else {
                thrownDice.add(d.getValue());
            }
        }
        for (int value : thrownDice) {
            if (!selectedDice.contains(value)) {
                return false;
            }
        }
        System.out.println("NULL!\n");
        return true;
    }

    @Override
    protected void tuttoPoints(TurnResult tr) {
        tr.setPoints(tr.getPoints() + 2000);
        tr.setNewCard(true);
    }

    @Override
    protected void selectDice() {
        ArrayList<Integer> selectedDice = new ArrayList<>();

        boolean anyDiceSelected = false;

        while (!anyDiceSelected) {

            for (Die d : dice) {
                if (d.isSelected()) {
                    selectedDice.add(d.getValue());
                }
            }
            for (Die d : dice) {
                if (!selectedDice.contains(d.getValue())) {
                    System.out.println("Do you want to select the die with value " + d.getValue() + "?\nEnter Y for yes or N for no:");
                    String input = inputObject.inputValidation_YN(inputObject.askStringInput());
                    if (input.equals("Y")) {
                        d.select();
                        selectedDice.add(d.getValue());
                        anyDiceSelected = true;
                    }
                }
            }
        }
    }
}
