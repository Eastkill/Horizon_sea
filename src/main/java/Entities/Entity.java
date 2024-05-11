package Entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

abstract public class Entity implements Cloneable {
    int x,y;
    int id;
    public String consoleRepresentation;
    public BufferedImage image;
    boolean ghost;
    public Entity(String consoleRepresentation, boolean ghost,int id, String image){
        this.consoleRepresentation = consoleRepresentation;
        this.ghost = ghost;
        this.id = id;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/"+image));
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
    public Entity(int x, int y, String consoleRepresentation, boolean ghost){
        this.x = x;
        this.y = y;
        this.consoleRepresentation = consoleRepresentation;
        this.ghost = ghost;
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

    @Override
    abstract public Object clone();
}
