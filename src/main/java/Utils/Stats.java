package Utils;

import Entities.Ships.ShipList;

public class Stats {
    public static int value= 100000;
    public static int valuewin= 0;
    public static int valuelose= 0;
    public static int loseCondition=100000;
    public static int winCondition=100000;
    public static boolean win=false;
    public static boolean lost=false;
    public static int stage = 0;

    /**
     * Checks if win condition is met, to end simulation
     */
    public static void checkWin(){
        if(valuewin>=winCondition) {
            win = true;
        }
    }
    /**
     * Checks if lose condition is met, to end simulation
     */
    public static void checkLose(){
        if(valuelose>=loseCondition || (ShipList.giveShipInfo()[1]==0&&value<25000)) {
            lost = true;
        }
    }
}
