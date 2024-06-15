package World;

import Entities.Cities.City;
import Entities.Cities.CityList;
import Entities.Ships.ShipList;
import Utils.OpenSimplex2S;
import Utils.Pathfinding;
import Utils.RandomSingleton;

import java.util.ArrayList;
import java.util.Objects;

public class WorldGenerator {
    public static ArrayList<ArrayList<Integer>> map;

    private static void init(int size) {
        map = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                temp.add(1);
            }
            map.add(temp);
        }
    }

    private static void generateBorders(double p1,double p2,double p3,double p4) {
        int seed = RandomSingleton.getInstance().nextInt();
        int size = map.size();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (OpenSimplex2S.noise2_ImproveX(seed, (double) x / 64, (double) y / 64) >=  p1)
                    map.get(y).set(x, 0);
                if (OpenSimplex2S.noise2_ImproveX(seed, (double) x / 32, (double) y / 32) >= p2)
                    map.get(y).set(x, 1);
                if (OpenSimplex2S.noise2_ImproveX(seed, (double) x / 16, (double) y / 16) >= p3)
                    map.get(y).set(x, 1);
                if (OpenSimplex2S.noise2_ImproveX(seed, (double) x / 8, (double) y / 8) >= p4)
                    map.get(y).set(x, 1);


            }
        }
        generateCities();
    }

    private static void generateCities() {
        int count = 5;
        int failed = 0;
        while (count > 0 && failed<map.size()*map.size()) {
            int x = RandomSingleton.getInstance().nextInt(map.size() - 1);
            int y = RandomSingleton.getInstance().nextInt(map.size() - 1);
            if (map.get(y).get(x) == 1) {
                if (checkNeighbours(x, y) && Pathfinding.isConnectedCity(map,x,y,new boolean[map.size()][map.size()])) {
//                    map.get(j).set(i, 2);
                    City c = new City(x,y);
                    c.setName(City.CitiesNames.values()[RandomSingleton.getInstance().nextInt(City.CitiesNames.values().length)].toString());
                    CityList.addList(c);
                    count--;
                    failed=0;
                    continue;
                }
            }
           failed++;
        }
    }

    private static boolean checkNeighbours(int x, int y) {
        if (map.get(Math.max(y - 1, 0)).get(x) == 0) return true;
        if (map.get(Math.min(y + 1, map.size() - 1)).get(x) == 0) return true;
        if (map.get(y).get(Math.min(x + 1, map.size()-1)) == 0) return true;
        if (map.get(y).get(Math.max(x - 1, 0)) == 0) return true;
        return false;
    }

    private static void generateShips(int n) {
        int count = n;
        int failed = 0;
        while (count > 0 && failed<map.size()) {
            int i = RandomSingleton.getInstance().nextInt(map.size() - 1);
            int j = RandomSingleton.getInstance().nextInt(map.size() - 1);
            if (map.get(j).get(i) == 0 && Pathfinding.isConnectedCity(map,i,j,new boolean[map.size()][map.size()])) {
                ShipList.addShip(i,j,(int)(RandomSingleton.getInstance().nextInt(2)));
                count--;
            }
            failed++;
        }
    }

    public static ArrayList<ArrayList<Integer>> getMap(int size,double p1,double p2,double p3,double p4) {
        init(size);
        generateBorders(p1,p2,p3,p4);
        generateShips(5);
        return map;
    }
}
