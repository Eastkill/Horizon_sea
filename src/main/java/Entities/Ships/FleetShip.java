package Entities.Ships;

abstract public class FleetShip extends Ship {
    private double durability;
    public FleetShip(int x, int y,String img,int type) {
        super(x, y,img,1,type);
        this.durability = 1;
    }
    private void reportDamage(){

    }
    public abstract void doSomething();

}
