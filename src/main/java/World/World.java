package World;

import Entities.EntitiesCache;
import Entities.Entity;

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
        map = WorldGenerator.getMap(size,0,0,0,0);
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
        map = WorldGenerator.getMap(map.size(),p1,p2,p3,p4);
    }
    /*
   ---------------------------------------------------
     */

    public ArrayList<ArrayList<Integer>> getMap() {
        return map;
    }

    public void show(){
        for(List<Integer> row: map){
            for(int i=0; i<row.size();i++){
                System.out.printf(EntitiesCache.getEntity(row.get(i)).consoleRepresentation + " ");
            }
            System.out.println();
        }
    }
}
