package Entities.Ships;

public class ScoutShip extends FleetShip{
    public ScoutShip(int x, int y) {
        super(x, y,"scout.png",3);
    }


    private int[] reportPirate(){
       return new int[]{0, 0};
    }

    @Override
    public void doSomething() {

    }
}
