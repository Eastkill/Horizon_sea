package World;

import Entities.Cities.CityList;
import Entities.Cities.JobList;
import Entities.Ships.ShipList;
import Utils.RandomSingleton;
import Utils.Stats;
import WindowHandle.SimulationPanel;

import java.util.ArrayList;
import java.util.List;

public class World {
    private static ArrayList<ArrayList<Integer>> map;
    /*
   ---------------------------------------------------
    Inicjalizacja singletona
     */
    private static World instance;
    private World(int size){

        map = WorldGenerator.getMap(size,-0.2,0.4,0.5,0.6);
    }
    public static World getInstance(int size){
        if(instance == null){
            instance = new World(size);
        }
        return instance;
    }
    public static World getInstance(){
        if(instance == null){
            throw new IllegalStateException("Nie utworzono świata.");
        }
        return instance;
    }
    public static void setInstance(double p1,double p2,double p3,double p4){
        int seed = (int)(Math.random()*1000);
        System.out.println(seed);
        Stats.value = 1000000;
        RandomSingleton.changeSeed(seed);
        CityList.resetList();
        ShipList.resetList();
        JobList.resetList();
        SimulationPanel.stage=0;



        map = WorldGenerator.getMap(map.size(),p1,p2,p3,p4);
        //CityList.ShowList();
    }
    /*
   ---------------------------------------------------
     */

    public ArrayList<ArrayList<Integer>> getMap() {
        return map;
    }


}
