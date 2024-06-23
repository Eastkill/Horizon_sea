package Entities.Ships;

import Entities.Cities.JobList;
import Utils.EntityUtils;
import Utils.Pathfinding;
import Utils.RandomSingleton;
import Utils.Stats;
import WindowHandle.SimulationPanel;
import World.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PirateShip extends Ship{
    List<int[]> cords =new ArrayList<>();
    public PirateShip(int x, int y) {
        super(x, y,"piraci.png",1,0);
    }


    @Override
    public void doSomething() {
        for(Ship s: ShipList.getList()){
            if(s.getClass()== TransportShip.class) {
                if (EntityUtils.isNeigbours(s.getCoordinates(), this.getCoordinates())) {
                    board((TransportShip) s);
                    return;
                }
            }
        }

            while(cords.isEmpty()){
                int x = RandomSingleton.getInstance().nextInt(World.getInstance().getMap().size()-1);
                int y = RandomSingleton.getInstance().nextInt(World.getInstance().getMap().size()-1);
                if(Pathfinding.isConnectedCity(World.getInstance().getMap(),x,y,new boolean[World.getInstance().getMap().size()][World.getInstance().getMap().size()])){
                    if(Pathfinding.nearCity(new int[]{x,y})) {
                        cords = Pathfinding.AStarAlgorithm.aStar(World.getInstance().getMap(), this.getCoordinates(), new int[]{x, y});
                        return;
                    }
                    return;
                }
            };

        if(World.getInstance().getMap().get(cords.getFirst()[1]).get(cords.getFirst()[0])!=1) {
            move(cords.getFirst()[0] - this.getCoordinates()[0], cords.getFirst()[1] - this.getCoordinates()[1]);
            cords.removeFirst();
        }
        else cords.clear();
    }

    /**
     * This method deletes boarded transport ship and takes value of its to Job to LoseValue
     * @param ship transport ship to be boarded
     */
    private void board(TransportShip ship){
    ShipList.removeShip(ship);
    if(ship.getJob()!=null){
        Stats.valuelose+=ship.job.value;
    }
    }
}
