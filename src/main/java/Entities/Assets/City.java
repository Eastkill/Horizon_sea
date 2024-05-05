package Entities.Assets;

import Entities.Entity;

public class City extends Entity {
    public City(){
        super("\u001b[33mC\u001b[0m", false,2);
    }
    public City(int x, int y) {
        super(x, y, "C", false);
    }

    @Override
    public Object clone() {
        return new Entities.Assets.City();
    }
}
