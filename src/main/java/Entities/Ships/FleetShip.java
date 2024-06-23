package Entities.Ships;

import Utils.RandomSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract public class FleetShip extends Ship {
    protected double durability;
    public boolean reported;
    protected List<int[]> cords =new ArrayList<>();
    private boolean repairing=false;
    public FleetShip(int x, int y,String img,int type) {
        super(x, y,img,1,type);
        this.durability = 1;
    }

    public abstract void doSomething();

    /**
     * This method switch repair state of ship
     */
    public void setRepairing(){
        this.repairing = !this.repairing;
    }

    /**
     * !!to delete
     * This method repair ship
     */
    public void repair(){
        this.durability+=0.5;
    }

    /**
     * !!to delete
     * This method switch report state of ship
     */
    public void setReported(){
        this.reported = !this.reported;
    }

}
