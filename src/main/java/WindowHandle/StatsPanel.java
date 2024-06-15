package WindowHandle;

import Entities.Cities.City;
import Entities.Cities.CityList;
import Entities.Cities.JobList;
import Entities.Ships.ShipList;
import Utils.Stats;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.List;

public class StatsPanel extends JPanel implements Runnable {
    Thread simulationThread;
    public void startSimulationThread() {
        simulationThread = new Thread(this);
        simulationThread.start();
    }
    public JLabel l1 = new JLabel("$$$: "+String.valueOf(Stats.value));
    JLabel l2 = new JLabel("Przegrana: "+String.valueOf(Stats.loseCondition*100)+"%");
    JLabel l3 = new JLabel("Wygrana: "+String.valueOf(Stats.winCondition*100)+"%");
    JList<String> list;

    public StatsPanel(){
        this.add(l1);
        this.add(l2);
        this.add(l3);
        DefaultListModel<String> lista = new DefaultListModel<>();
        for(City c: CityList.getList()){
        lista.addElement(c.getName());
        }
        list = new JList<>(lista);
        this.add(list);
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
        l1.setText("$$$:"+Stats.value);
    }
}
