import Entities.Cities.CityList;
import Entities.Cities.JobList;
import Entities.Ships.ShipList;
import Utils.Audio;
import Utils.RandomSingleton;
import Utils.Stats;
import WindowHandle.ParametersPanel;
import WindowHandle.SimulationPanel;
import WindowHandle.StatsPanel;
import World.World;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Tryb Pracy\n" +
                "1.Badania\n" +
                "2.Symulacja");
        switch (new Scanner(System.in).nextInt()){
            case 1:
                ArrayList<ResearchData> list = new ArrayList<>();
                System.out.printf("Podaj liczbe powtorzen:");
                int n = new Scanner(System.in).nextInt();
                System.out.printf("Podaj liczbe piratow:");
                int pirates = new Scanner(System.in).nextInt();
                System.out.printf("Podaj poczatkowa sume pieniedzy:");
                int value = new Scanner(System.in).nextInt();

                RandomSingleton.getInstance((int)(Math.random()*1000));
                World.getInstance(128);
                for (int i=0; i<n; i++){
                    System.out.println("Iteracja:" + i);
                    World.setInstance(-0.3,0.65,0.75,0.85,value,pirates,(int)(Math.random()*1000));
                    ResearchData test = new ResearchData();
                    while((!Stats.win && !Stats.lost)){
                        CityList.generateContract();
                        ShipList.doAction();
                        Stats.checkWin();
                        Stats.checkLose();
                        Stats.stage++;
                    }
                    test.loseCondition= (double) Stats.valuelose /Stats.loseCondition;
                    test.winCondition= (double) Stats.valuewin /Stats.winCondition;
                    test.stage = Stats.stage;
                    test.win = Stats.win;
                    list.add(test);
                }
                for (ResearchData r:list){
                    r.print();
                }
                break;
            case 2:
                int seed = (int)(Math.random()*1000);
                System.out.println(seed);
                RandomSingleton.getInstance((int)(seed));
                //World.getInstance(Integer.parseInt(args[0]));
                World.getInstance(128);



                JFrame window = new JFrame();
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setResizable(false);
                window.setTitle("Sea Horizon");

                SimulationPanel simulationPanel = new SimulationPanel();
                ParametersPanel parametersPanel = new ParametersPanel();
                StatsPanel statsPanel = new StatsPanel();
                window.add(parametersPanel, BorderLayout.EAST);
                window.add(simulationPanel,BorderLayout.WEST);
                window.add(statsPanel);
                window.pack();
                window.setResizable(true);
                window.setLocationRelativeTo(null);
                window.setVisible(true);
                Audio.Playmusic();
                simulationPanel.startSimulationThread();
                statsPanel.startSimulationThread();
                CityList.ShowList();
                break;
        }

    }
}

