package Entities.Ships;

import Entities.Cities.CityList;
import Utils.Pathfinding;
import World.World;

import java.util.ArrayList;
import java.util.List;

public class EscortShip extends FleetShip{
    private FleetShip target = null;
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
        durability+=0.01;
        if(target!=null){
            if(!cords.isEmpty()){
                move(cords.getFirst()[0]-this.getCoordinates()[0],cords.getFirst()[1]-this.getCoordinates()[1]);
                cords.removeFirst();
                if(cords.isEmpty()){
                    target.setRepairing();
                }
                return;
            }
            if(target.durability<1.0){
                target.repair();
                return;
            }
            if (target.durability>=1.0){
                target.setRepairing();
                target.setReported();
                target=null;
                return;
            }

        }
        if(target == null && !DamageList.damageList.isEmpty()){
            target = DamageList.damageList.getFirst();
            DamageList.damageList.removeFirst();
            cords = Pathfinding.AStarAlgorithm.aStar(World.getInstance().getMap(), this.getCoordinates(), target.getCoordinates());
            return;
        }
        if(cords.isEmpty()) {
            cords = Pathfinding.AStarAlgorithm.aStar(World.getInstance().getMap(), this.getCoordinates(), CityList.getList().get(0).getCoordinates());
            return;
        }
        move(cords.getFirst()[0]-this.getCoordinates()[0],cords.getFirst()[1]-this.getCoordinates()[1]);
        cords.removeFirst();
    }
}
