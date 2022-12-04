package DiePackage;
import java.util.Random;

public final class Die {
    private boolean selected = false;
    private int value;

    public void rollDie(){
        Random rand = new Random();
        this.value = rand.nextInt(1,7);
    }

    public int getValue() {
        return this.value;
    }

    public boolean isSelected(){
        return this.selected;
    }

    public void select() {
        this.selected = true;
    }


}
