package Entities.Cities;

import Entities.Entity;
import Utils.RandomSingleton;

public class City extends Entity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City(int x, int y) {
        super(x, y,"city.png");
    }

    public enum CitiesNames {
        Misato,Rei,Asuka,Shinji,Gendo,Gon,Kilua,Kurapika,Hisoka,Naruto,Sasuke,Kakashi,Rin,Pain,Horo,Kurumi,Tohka,Yoshino,Origmai,Kotori,Mana,Nia,Miku;
    }

    /**
     * Generates new job with random bounty between 1000-5000
     */
    public void generateJob(){
        int n = RandomSingleton.getInstance().nextInt(CityList.getList().size());
        JobList.addJob(new Job(this,(RandomSingleton.getInstance().nextInt(4000)+1000),CityList.getList().get(n)));
    }
}
