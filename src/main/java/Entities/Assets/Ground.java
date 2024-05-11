package Entities.Assets;

import Entities.Entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Ground extends Entity {
    public Ground(){
        super("\u001b[32mT\u001b[0m", false,1,"NSEW.png");
    }
    public Ground(int x, int y) {
        super(x, y, "T", false);
    }

    @Override
    public Object clone() {
        return new Ground();
    }
}
