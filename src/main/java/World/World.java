package World;

import Entities.EntitiesCache;
import Entities.Entity;

import java.util.ArrayList;
import java.util.List;

public class World {
    private ArrayList<ArrayList<Entity>> map;
    private int chunkSize;

    /*
   ---------------------------------------------------
    Inicjalizacja singletona
     */
    private static World instance;
    private World(int size,int chunkSize){
        this.map = WorldGenerator.getMap(size, chunkSize);
    }
    public static World getInstance(int size, int chunkSize){
        if(instance == null){
            instance = new World(size,chunkSize);
        }
        return instance;
    }
    /*
   ---------------------------------------------------
     */


    public void show(){
        for(List<Entity> row: map){
            for(int i=0; i<row.size();i++){
                System.out.printf(row.get(i).consoleRepresentation + " ");
            }
            System.out.println();
        }
    }
}
