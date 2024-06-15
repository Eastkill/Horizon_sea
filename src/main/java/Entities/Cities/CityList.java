package Entities.Cities;

import Entities.Ships.ShipList;
import Utils.RandomSingleton;
import Utils.Stats;
import WindowHandle.SimulationPanel;

import java.awt.*;
import java.util.ArrayList;

public class CityList {
    private static ArrayList<City> list = new ArrayList<City>();
    public static void resetList(){
        list = new ArrayList<>();
    }

    public static void drawList(Graphics2D g2){
        for(City city:list){
            g2.drawImage(city.image,city.getCoordinates()[0]* SimulationPanel.scale,city.getCoordinates()[1]*SimulationPanel.scale,SimulationPanel.scale,SimulationPanel.scale,null);
        }
    }

    public static void addList(City city) {
        list.add(city);
    }
    public static void ShowList(){
        System.out.printf("\u001b[H");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Nazwa miasta: "+String.format("%-8s",list.get(i).getName())+" | x: "+String.format("%3d",list.get(i).getCoordinates()[0]) + " | y: "+String.format("%3d",list.get(i).getCoordinates()[1]));
        }
    }
    public static ArrayList<City> getList(){
        return list;
    }
    public static void generateContract(){
        for (City c:list){
            if(RandomSingleton.getInstance().nextDouble()>0.993&&JobList.getList().size()<5){
                c.generateJob();
                continue;
            }
            if((RandomSingleton.getInstance().nextDouble()>0.9 || ShipList.giveShipInfo()[1] <= 1)&& Stats.value >= 50000){

            }
        }
    }
}
