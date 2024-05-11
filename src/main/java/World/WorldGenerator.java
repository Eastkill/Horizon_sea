package World;

import Entities.Assets.City;
import Entities.CitiesNames;
import Entities.CityList;
import Entities.EntitiesCache;
import Entities.Entity;
import Utils.OpenSimplex2S;
import Utils.RandomSingleton;

import java.util.ArrayList;

public class WorldGenerator {
    public static ArrayList<ArrayList<Integer>> map;

    private static void init(int size) {
        map = new ArrayList<>();
        for (int i = 0; i < size + 1; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < size + 1; j++) {
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
                if (OpenSimplex2S.noise2_ImproveX(seed, (double) x / 64, (double) y / 64) >=  0.5f) {
                    map.get(y).set(x,3);
                }
                if (OpenSimplex2S.noise2_ImproveX(seed, (double) x / 32, (double) y / 32) >=  0.5f) {
                    map.get(y).set(x,3);
                }
                if (OpenSimplex2S.noise2_ImproveX(seed, (double) x / 16, (double) y / 16) >=  0.5f) {
                    map.get(y).set(x,3);
                }
                if (OpenSimplex2S.noise2_ImproveX(seed, (double) x / 8, (double) y / 8) >=  0.5f) {
                    map.get(y).set(x,3);
                }

            }
        }
        generateCities();
    }

    private static void generateCities() {
        int count = 5;
        int failed = 0;
        while (count > 0 && failed<map.size()) {
            int i = RandomSingleton.getInstance().nextInt(map.size() - 1);
            int j = RandomSingleton.getInstance().nextInt(map.size() - 1);
            if (map.get(j).get(i) == 1) {
                if (checkNeighbours(i, j)) {
                    map.get(j).set(i, 2);
                    City c = new City(j, i);
                    c.setName(CitiesNames.values()[RandomSingleton.getInstance().nextInt(CitiesNames.values().length)].toString());
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
        if (map.get(y).get(Math.min(x + 1, map.size())) == 0) return true;
        if (map.get(y).get(Math.max(x - 1, 0)) == 0) return true;
        return false;
    }

    public static ArrayList<ArrayList<Integer>> getMap(int size,double p1,double p2,double p3,double p4) {
        init(size);
        generateBorders(p1,p2,p3,p4);
        return map;
    }
}
