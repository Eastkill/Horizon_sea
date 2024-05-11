package Entities.Assets;

import Entities.Entity;

public class Water extends Entity {
    public Water(){
        super("\u001b[34m.\u001b[0m", true,0,"Water.png");
    }
    public Water(int x, int y) {
        super(x, y, ".", true);
    }
    @Override
    public Object clone() {
        return new Water();
    }
}
