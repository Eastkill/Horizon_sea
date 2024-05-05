package Entities;

abstract public class Entity implements Cloneable {
    int x,y;
    int id;
    public String consoleRepresentation;
    boolean ghost;
    public Entity(String consoleRepresentation, boolean ghost,int id){
        this.consoleRepresentation = consoleRepresentation;
        this.ghost = ghost;
        this.id = id;
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

    @Override
    abstract public Object clone();
}
