package Entities.Ships;

import Entities.Entity;

abstract public class Ship extends Entity {
    int speed;
    int type;
    public Ship(int x, int y,String img,int speed, int type){
        super(x,y,img);
        this.speed = speed;
        this.type = type;
    }

    /**
     * moves by some tiles
     * @param vertical vertical offset
     * @param horizontal horizontal offset
     */
    public void move(int vertical, int horizontal){
        this.setCoordinates(this.getCoordinates()[0]+vertical,this.getCoordinates()[1]+horizontal);
    };

    /**
     *
     * @return type of ship
     * 0-pirate
     * 1-transport
     * 2-escort
     */
    public int getType(){
        return this.type;
    }

    /**
     * Just like name suggests this method does something
     */
    public abstract void doSomething();
}
