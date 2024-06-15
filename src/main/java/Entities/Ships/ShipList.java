package Entities.Ships;

import Entities.Ships.PirateShip;
import Entities.Ships.Ship;
import WindowHandle.SimulationPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShipList {
    private static ArrayList<Ship> list=new ArrayList<>();

    public static void addShip(int x,int y, int type){
        switch (type){
            case 0:
                list.add(new PirateShip(x,y));
                break;
            case 1:
                list.add(new TransportShip(x,y));
                break;
            case 2:
                list.add(new EscortShip(x,y));
                break;
            case 3:
                list.add(new ScoutShip(x,y));
                break;
        }
    }
    public static void resetList(){
        list = new ArrayList<>();
    }
    public static void doAction(){
        for(int i=0 ; i< list.size(); i++){
            list.get(i).doSomething();
        }
    }
    public static void drawShips(Graphics g, int scale){
    for (Ship ship : list){
        switch (ship.getType()){
            case 0:
                g.setColor(Color.RED);
                break;
            case 1:
                g.setColor(Color.MAGENTA);
                break;
            case 2:
                g.setColor(Color.CYAN);
                break;
            case 3:
                g.setColor(Color.YELLOW);
                break;
            case 4:
                g.setColor(Color.PINK);
        }

        g.fillOval((int)ship.getCoordinates()[0]*scale,(int)ship.getCoordinates()[1]*scale, scale,scale);
    }
    }
    public static ArrayList<Ship> getList(){
        return list;
    }
    public static void removeShip(Ship s){
        list.remove(s);
    }
    public static int[] giveShipInfo(){
        int[] num = new int[4];
        for (Ship s: list){
            if (s instanceof PirateShip)num[0]++;
            if (s instanceof TransportShip)num[1]++;
            if (s instanceof EscortShip)num[2]++;
            if (s instanceof ScoutShip)num[3]++;
        }
        return num;
    }
}
