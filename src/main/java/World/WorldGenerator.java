package World;

import Entities.EntitiesCache;
import Entities.Entity;
import Utils.OpenSimplex2S;
import Utils.RandomSingleton;

import java.util.ArrayList;

public class WorldGenerator {
    public static ArrayList<ArrayList<Entity>> map;
    private static void init(int size){
        map = new ArrayList<>();
        for(int i=0; i<size; i++){
            ArrayList<Entity> temp = new ArrayList<>();
            for(int j=0; j<size; j++){
                temp.add(EntitiesCache.getEntity(0));
            }
            map.add(temp);
        }
    }
    private static void generateBorders(int chunkSize){
        int seed = RandomSingleton.getInstance().nextInt();
        int size = map.size();
        for(int y=0; y<size; y++){
            for(int x=0; x<size; x++){
                    if (OpenSimplex2S.noise2_ImproveX(seed, (double) x / 64, (double) y / 64) >= 0.5)
                        map.get(y).set(x, EntitiesCache.getEntity(1));
                    if( OpenSimplex2S.noise2_ImproveX(seed, (double) x /32, (double) y /32)>=0.5)
                        map.get(y).set(x,EntitiesCache.getEntity(1));
                if (OpenSimplex2S.noise2_ImproveX(seed, (double) x / 16, (double) y / 16) >= 0.5)
                    map.get(y).set(x, EntitiesCache.getEntity(1));
                if( OpenSimplex2S.noise2_ImproveX(seed, (double) x /8, (double) y /8)>=0.5)
                    map.get(y).set(x,EntitiesCache.getEntity(1));

            }
        }
    }
    private static void generateCities(){
        int count =10;
        while(count>=0){
            int i = RandomSingleton.getInstance().nextInt(map.size()-1);
            int j = RandomSingleton.getInstance().nextInt(map.size()-1);
                if(map.get(j).get(i).getId() == 1) {
                    if(checkNeighbours(i,j)){
                        map.get(j).set(i,EntitiesCache.getEntity(2));
                        count--;
                    }
                }
            }
    }
    private static boolean checkNeighbours(int x, int y){
        if(map.get(Math.max(y-1,0)).get(x).getId() == 0) return true;
        if(map.get(Math.min(y+1,map.size()-1)).get(x).getId() == 0) return true;
        if(map.get(y).get(Math.min(x+1,map.size())).getId() == 0) return true;
        if(map.get(y).get(Math.max(x-1,0)).getId() == 0) return true;
        return false;
    }
    public static ArrayList<ArrayList<Entity>> getMap(int size, int chunkSize){
        init(size*chunkSize);
        generateBorders(chunkSize);
        generateCities();
        return map;
    }
}
