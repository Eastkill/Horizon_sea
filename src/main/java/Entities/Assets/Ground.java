package Entities.Assets;

import Entities.Entity;

public class Ground extends Entity {
    public Ground(){
        super("\u001b[32mT\u001b[0m", false,1);
    }
    public Ground(int x, int y) {
        super(x, y, "T", false);
    }

    @Override
    public Object clone() {
        return new Ground();
    }
}
