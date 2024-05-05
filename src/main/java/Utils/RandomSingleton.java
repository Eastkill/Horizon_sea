package Utils;

import java.util.Random;

public class RandomSingleton extends Random {
    private static RandomSingleton instance;
    private RandomSingleton(long seed){
        this.setSeed(seed);
    }

    public static RandomSingleton getInstance(int seed) {
        if(instance == null){
            instance = new RandomSingleton(seed);
        }
        return instance;
    }
    public static RandomSingleton getInstance() {
        if(instance == null){
            throw new IllegalCallerException("Nie został zainicjowany seed RNG");
        }
        return instance;
    }
}
