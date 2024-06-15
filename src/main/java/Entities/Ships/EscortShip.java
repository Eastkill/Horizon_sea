package Entities.Ships;

public class EscortShip extends FleetShip{
    public EscortShip(int x, int y) {
        super(x, y,"supp.png",2);
        this.speed=2;
    }

    @Override
    public Object clone() {
        return null;
    }

    @Override
    public void doSomething() {

    }
}
