package WindowHandle;

import Entities.Cities.CityList;
import Entities.Cities.JobList;
import Entities.Ships.PirateShip;
import Entities.Ships.Ship;
import Entities.Ships.ShipList;
import Utils.Stats;
import World.World;
import com.sun.tools.javac.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class SimulationPanel extends JPanel implements Runnable {
    public static final int scale = 8;
    final int size = World.getInstance().getMap().size();
    final int screenSize = size * scale;
    public static int fps = 30;

    Thread simulationThread;

    public SimulationPanel() {
        this.setPreferredSize(new Dimension(screenSize, screenSize));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startSimulationThread() {
        simulationThread = new Thread(this);
        simulationThread.start();
    }

    @Override
    public void run() {

        double drawInterval;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while(simulationThread != null) {
            while ((!Stats.win && !Stats.lost)) {
                drawInterval = (double) 1000000000 / fps;
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                lastTime = currentTime;
                if (delta >= 1) {
                    update();
                    repaint();
                    delta--;
                }
            }
            if (Stats.win) {
                System.out.println("win");
            } else {
                System.out.println("lost");
            }
        }
    }

    public void update() {
        JobList.printList();
        CityList.generateContract();
        ShipList.doAction();
        JobList.printList();
        Stats.checkWin();
        Stats.checkLose();
        Stats.stage++;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ArrayList<ArrayList<Integer>> map = World.getInstance().getMap();
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                switch (map.get(i).get(j)) {
                    case 0:
                        g2.setColor(Color.blue);
                        break;
                    case 1:
                        g2.setColor(Color.green);
                        break;
                    case 2:
                        g2.setColor(Color.red);
                        break;
                    case 3:
                        g2.setColor(Color.orange);
                        break;
                }

                g2.fillRect((j) * scale, (i) * scale, scale, scale);

            }
            CityList.drawList(g2);
        }
        ShipList.drawShips(g2,scale);
    }
}
