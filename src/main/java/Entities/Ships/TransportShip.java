package Entities.Ships;

import Entities.Cities.City;
import Entities.Cities.CityList;
import Entities.Cities.Job;
import Entities.Cities.JobList;
import Utils.EntityUtils;
import Utils.Pathfinding;
import Utils.RandomSingleton;
import Utils.Stats;
import WindowHandle.StatsPanel;
import World.World;
import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.List;

public class TransportShip extends FleetShip{
    public Job job;
    private City towardThis = null;
    public TransportShip(int x, int y) {
        super(x, y,"transport.png",1);
    }

    /**
     * Takes new job
     * @param job job to be taken
     */
    private void takeJob(Job job){
    this.job = job;
    }

    /**
     * Ends job and adds bounty to global Stats
     */
    private void endJob(){
        Stats.value += job.value;
        Stats.valuewin +=job.value;
        //System.out.println(Stats.valuewin);
        towardThis = null;
        job = null;
    }

    public void doSomething(){
        takeDamage();
        if(this.reported){
            return;
        }
        if(towardThis == null){
            if(!JobList.getList().isEmpty()){
                //System.out.println("najblizszy");
            towardThis=JobList.getList().getFirst().mainCity;
            return;
            }
            towardThis=CityList.getList().get(RandomSingleton.getInstance().nextInt(CityList.getList().size()));

        }
        if (EntityUtils.isNeigbours(this.getCoordinates(), towardThis.getCoordinates())){
            for(Job j: JobList.getList()){
                if (j.mainCity == towardThis) takeJob(j);
                towardThis = j.recipient;
                cords.clear();
                JobList.removeJob(j);
                return;
            }
        }
    if(job !=null) {
        if (EntityUtils.isNeigbours(this.getCoordinates(), job.recipient.getCoordinates())) {
            towardThis = null;

            endJob();
            return;
        }
    }
    if (cords.isEmpty()) {
        cords = Pathfinding.AStarAlgorithm.aStar(World.getInstance().getMap(), this.getCoordinates(), towardThis.getCoordinates());
        return;
    }
    goToCity();
    if (cords.isEmpty())towardThis=null;


    }

    /**
     * This method moves by one tile in direction of specific city, based on cords, based on A* algorithm
     */
    private void goToCity(){
        if(cords.isEmpty())return;
      move(cords.getFirst()[0]-this.getCoordinates()[0],cords.getFirst()[1]-this.getCoordinates()[1]);
      cords.removeFirst();
    }

    public Job getJob() {
        return job;
    }

    /**
     * This method randomly switches state of ship to be damaged
     */
    public void takeDamage(){
        if(RandomSingleton.getInstance().nextDouble()>=0.997&&!reported){
            this.setReported();
            DamageList.damageList.add(this);
        }
    }
}
