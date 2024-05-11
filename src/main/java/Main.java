import Entities.Assets.City;
import Entities.CityList;
import Utils.OpenSimplex2S;
import Utils.RandomSingleton;
import WindowHandle.ParametersPanel;
import WindowHandle.SimulationPanel;
import World.World;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        RandomSingleton.getInstance((int)(Math.random()*1000));
        World.getInstance(Integer.parseInt(args[0]));

      //  World.getInstance().show();
     //   new Scanner(System.in).next();



        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Sea Horizon");

        SimulationPanel simulationPanel = new SimulationPanel();
        ParametersPanel parametersPanel = new ParametersPanel();
        window.add(parametersPanel, BorderLayout.EAST);
        window.add(simulationPanel,BorderLayout.WEST);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        simulationPanel.startSimulationThread();
        CityList.ShowList();
    }
}

