package Entities.Cities;

import Entities.Ships.ShipList;
import Utils.RandomSingleton;
import Utils.Stats;
import WindowHandle.SimulationPanel;
import World.World;

import java.awt.*;
import java.util.ArrayList;

public class CityList {
    private static ArrayList<City> list = new ArrayList<City>();
    public static void resetList(){
        list = new ArrayList<>();
    }

    /**
     * this method draws all cities
     * @param g2 graphics of Simulation Panel
     */
    public static void drawList(Graphics2D g2){
        for(City city:list){
            g2.drawImage(city.image,city.getCoordinates()[0]* SimulationPanel.scale,city.getCoordinates()[1]*SimulationPanel.scale,SimulationPanel.scale,SimulationPanel.scale,null);
        }
    }

    /**
     * This method adds city to the list
     * @param city city to be added
     */
    public static void addList(City city) {
        list.add(city);
    }

    /**
     * to be removed
     */
    public static void ShowList(){
        System.out.printf("\u001b[H");
        for (int i = 0; i < list.size(); i++) {
            //System.out.println("Nazwa miasta: "+String.format("%-8s",list.get(i).getName())+" | x: "+String.format("%3d",list.get(i).getCoordinates()[0]) + " | y: "+String.format("%3d",list.get(i).getCoordinates()[1]));
        }
    }
    public static ArrayList<City> getList(){
        return list;
    }

    /**
     * !! to be split and renamed
     * This method generates new contract or new transport ship if condition are met
     */
    public static void generateContract(){
        for (City c:list){
            if(RandomSingleton.getInstance().nextDouble()>0.993&&JobList.getList().size()<5){
                c.generateJob();
                continue;
            }
            if((RandomSingleton.getInstance().nextDouble()>0.99 && ShipList.giveShipInfo()[1]<8)&& Stats.value >= 25000){
            Stats.value-=25000;
            int[][]directions = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
            for (int[] cords:directions){
                if(World.getInstance().getMap().get(c.getCoordinates()[1]+cords[1]).get(c.getCoordinates()[0]+cords[0])!=1){
                    ShipList.addShip(c.getCoordinates()[0]+cords[0],c.getCoordinates()[1]+cords[1],1);
                    return;
                }
            }
            }
        }
    }
}
