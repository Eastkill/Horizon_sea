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

    private void takeJob(Job job){
    this.job = job;
    }
    private void endJob(){
        Stats.value += job.value;
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
                System.out.println("najblizszy");
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
    private void goToCity(){
        if(cords.isEmpty())return;
      move(cords.getFirst()[0]-this.getCoordinates()[0],cords.getFirst()[1]-this.getCoordinates()[1]);
      cords.removeFirst();
    }

    public Job getJob() {
        return job;
    }
}
