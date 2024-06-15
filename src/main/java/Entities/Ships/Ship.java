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
    public void move(int vertical, int horizontal){
        this.setCoordinates(this.getCoordinates()[0]+vertical,this.getCoordinates()[1]+horizontal);
    };
    public int getType(){
        return this.type;
    }
    public abstract void doSomething();
}
