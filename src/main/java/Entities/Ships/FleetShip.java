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
    protected void takeDamage(){
        if(RandomSingleton.getInstance().nextDouble()>=0.997&&!reported){
            this.setReported();
            DamageList.damageList.add(this);
        }
    }
    public abstract void doSomething();
    public void setRepairing(){
        this.repairing = !this.repairing;
    }
    public void repair(){
        this.durability+=0.5;
    }
    public void setReported(){
        this.reported = !this.reported;
    }

}
