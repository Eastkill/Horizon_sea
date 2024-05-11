package Entities.Assets;

import Entities.Entity;

public class City extends Entity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City(){
        super("\u001b[33mC\u001b[0m", false,2,"N.png");
    }
    public City(int x, int y) {
        super(x, y, "C", false);
    }

    @Override
    public Object clone() {
        return new Entities.Assets.City();
    }
}
