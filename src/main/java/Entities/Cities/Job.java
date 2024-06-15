package Entities.Cities;

import Entities.Cities.City;

public class Job {
    public City mainCity;
    public int value;
    public City recipient;
    public Job(City c, int value, City recipient){
        this.mainCity = c;
        this.value=value;
        this.recipient=recipient;
    }
}
