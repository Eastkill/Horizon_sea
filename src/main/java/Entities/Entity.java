package Entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

abstract public class Entity {

    private static int idCount;
    int id;

    int x,y;
    public BufferedImage image;
    public Entity(int x, int y, String imageSrc){
        this.x = x;
        this.y = y;
        this.id = getUniqueId();
        try {
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/" + imageSrc)));
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
    public int getId(){
        return this.id;
    }
    public void setCoordinates(int x, int y){
     this.x = x;
     this.y = y;
    }
    public int[] getCoordinates(){
        return new int[]{x, y};
    }
    public static int getUniqueId(){
        idCount++;
        return idCount;
    }

}
