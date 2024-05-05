import Utils.OpenSimplex2S;
import Utils.RandomSingleton;
import World.World;

public class Main {
    public static void main(String[] args) {
        RandomSingleton.getInstance((int)(Math.random()*1000));
        World.getInstance(Integer.parseInt(args[0]),Integer.parseInt(args[1])).show();
        new java.util.Scanner(System.in).nextLine();
    }
}

