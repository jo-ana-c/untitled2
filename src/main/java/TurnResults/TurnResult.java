package TurnResults;

public final class TurnResult {
    private int points = 0;
    private int plusMinus = 0;
    private int cloverleaf = 0;
    private boolean firework = false;
    private boolean newCard = false;

    public int getPoints(){
        return this.points;
    }
    public void setPoints(int points){
        this.points = points;
    }
    public int getPlusMinus(){
        return this.plusMinus;
    }
    public void setPlusMinus(int times){
        this.plusMinus = times;
    }
    public int getCloverleaf(){
        return this.cloverleaf;
    }
    public void setCloverleaf(int times){
        this.cloverleaf = times;
    }
    public void setFirework(boolean bool){
        this.firework = bool;
    }
    public boolean getFirework(){
        return this.firework;
    }
    public void setNewCard(boolean bool){
        this.newCard = bool;
    }
    public boolean getNewCard(){
        return this.newCard;
    }

    public boolean cloverleafWon() {
        return this.cloverleaf == 2;
    }
}