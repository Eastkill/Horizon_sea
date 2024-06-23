package Entities.Ships;

import Entities.Cities.CityList;
import Utils.Pathfinding;
import World.World;

import java.util.ArrayList;
import java.util.List;

public class EscortShip extends FleetShip{
    private TransportShip target = null;
    public EscortShip(int x, int y) {
        super(x, y,"supp.png",2);
        this.speed=2;
    }

    @Override
    /**
     * This method does one of few things:
     * moves to random city
     * moves to first damaged ship in DamageList
     */
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
                if (target.job != null) {
                    cords = Pathfinding.AStarAlgorithm.aStar(World.getInstance().getMap(), this.getCoordinates(), target.job.recipient.getCoordinates());
                }
                else {
                    cords = Pathfinding.AStarAlgorithm.aStar(World.getInstance().getMap(), this.getCoordinates(), CityList.getList().getFirst().getCoordinates());
                }
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

            return;
        }
        move(cords.getFirst()[0]-this.getCoordinates()[0],cords.getFirst()[1]-this.getCoordinates()[1]);
        cords.removeFirst();
    }
}
