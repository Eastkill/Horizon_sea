package WindowHandle;

import Entities.Cities.City;
import Entities.Cities.CityList;
import Entities.Cities.JobList;
import Entities.Ships.ShipList;
import Utils.Stats;
import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.List;

public class StatsPanel extends JPanel implements Runnable, ChangeListener {
    Thread simulationThread;
    public void startSimulationThread() {
        simulationThread = new Thread(this);
        simulationThread.start();
    }
    public JLabel l1 = new JLabel("Money: "+String.valueOf(Stats.value));
    JLabel l2 = new JLabel("Lose Condition: "+String.valueOf(Stats.loseCondition*100)+"%");
    JLabel l3 = new JLabel("Win Condition: "+String.valueOf(Stats.winCondition*100)+"%");

    JPanel p1 = new JPanel();
    JLabel o1 = new JLabel("Speed:");
    JLabel l5 = new JLabel(String.valueOf(Stats.stage));
    JSlider s4 = new JSlider(1,4,1);
    JLabel l4 = new JLabel("x"+s4.getValue());
    JList<String> list;

    public StatsPanel(){
        s4.setSize(10,10);
        this.setPreferredSize(new Dimension(270, 400));
        this.setLayout(new FlowLayout());
        p1.add(o1);
        p1.add(s4);
        this.add(l1);
        this.add(l2);
        this.add(l3);
        p1.add(l4);
        s4.addChangeListener(this);
        this.add(p1);
        this.add(l5);
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / 10;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (simulationThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }
    public void update() {
        l1.setText("Money:"+Stats.value);
        l2.setText("Lose Condition: "+String.format("%.2f",(double)Stats.valuelose/Stats.loseCondition*100)+"%");
        l3.setText("Win Condition: "+String.format("%.2f",(double)Stats.valuewin/Stats.winCondition*100)+"%");
        l5.setText("Stage:"+Stats.stage);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        SimulationPanel.fps=30*s4.getValue();
        l4.setText("x"+s4.getValue());
    }
}
