package Entities.Cities;

import java.util.ArrayList;

public class JobList {
    private static ArrayList<Job> list = new ArrayList<Job>();
    public static void addJob(Job j){
        list.add(j);
    }
    public static void printList(){
        for(Job j: list){
           System.out.println("od: "+j.mainCity.getName()+" za: "+j.value + " do: "+ j.recipient.getName());
        }
        System.out.println();
    }
    public static void resetList(){
        list = new ArrayList<Job>();
    }
    public static void removeJob(Job job){
        list.remove(job);
    }
    public static ArrayList<Job> getList(){
        return list;
    }
}
