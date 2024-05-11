package Entities;

import Entities.Assets.City;
import World.World;

import java.util.ArrayList;

public class CityList {
    private static ArrayList<City> list = new ArrayList<City>();

    public static ArrayList<City> getList() {
        return list;
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
}
