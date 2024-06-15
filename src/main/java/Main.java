import Entities.Cities.CityList;
import Utils.Audio;
import Utils.RandomSingleton;
import WindowHandle.ParametersPanel;
import WindowHandle.SimulationPanel;
import WindowHandle.StatsPanel;
import World.World;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
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

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        Audio.Playmusic();
        simulationPanel.startSimulationThread();
        statsPanel.startSimulationThread();
        CityList.ShowList();
    }
}

