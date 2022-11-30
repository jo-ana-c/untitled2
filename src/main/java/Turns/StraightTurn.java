package Turns;
import java.util.ArrayList;
import java.util.Scanner;
import DiePackage.Die;
import TurnResults.TurnResult;

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
        return true;
    }

    @Override
    void tuttoPoints(TurnResult tr) {
        tr.setPoints(tr.getPoints() + 2000);
    }

    @Override
    void selectDice() {
        ArrayList<Integer> selectedDice = new ArrayList<>();

        boolean anyDiceSelected = false;

        Scanner sc = new Scanner(System.in);

        while (!anyDiceSelected) {

            for (Die d : dice) {
                if (d.isSelected()) {
                    selectedDice.add(d.getValue());
                }
                else if (!selectedDice.contains(d.getValue())) {
                    System.out.println("Do you want to select the die with value " + d.getValue() + "?\nEnter Y for yes or N for no:");
                    String input = sc.next();
                    while (!(input.equals("Y") || input.equals("N"))) {
                        System.out.println("Invalid input! Enter Y for yes or N for no: \n");
                        input = sc.next();
                    }
                    if (input.equals("Y")){
                        d.select();
                        selectedDice.add(d.getValue());
                        anyDiceSelected = true;
                    }
                }
            }
        }
    }
}
