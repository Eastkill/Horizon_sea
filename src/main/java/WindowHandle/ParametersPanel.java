package WindowHandle;

import Utils.RandomSingleton;
import World.World;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ParametersPanel extends JPanel implements ChangeListener {
   JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    JPanel p5 = new JPanel();
    JPanel p6 = new JPanel();

    JLabel o1 = new JLabel("Map generation params");
    JSlider s1 = new JSlider(-50,10,0);
    JLabel l1 = new JLabel(String.valueOf(s1.getValue()/100));
    JSlider s2 = new JSlider(0,75,65);
    JLabel l2 = new JLabel(String.valueOf(s2.getValue()/100));
    JSlider s3 = new JSlider(30,100,75);
    JLabel l3 = new JLabel(String.valueOf(s3.getValue()/100));
    JSlider s4 = new JSlider(50,100,85);
    JLabel l4 = new JLabel(String.valueOf(s4.getValue()/100));
    JLabel o2 = new JLabel("Base budget");
    JSlider s5 = new JSlider(1000,1000000,100000);
    JLabel l5 = new JLabel(String.valueOf(s5.getValue()));
    JLabel o3 = new JLabel("Number of pirates");
    JSlider s6 = new JSlider(0,3,1);
    JLabel l6 = new JLabel(String.valueOf(s6.getValue()));
    JButton b = new JButton("Generate");

    public ParametersPanel(){
        s1.addChangeListener(this);
        s2.addChangeListener(this);
        s3.addChangeListener(this);
        s4.addChangeListener(this);
        s5.addChangeListener(this);
        s6.addChangeListener(this);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                World.setInstance((double) s1.getValue() /100, (double) s2.getValue() /100, (double) s3.getValue() /100, (double) s4.getValue() /100,s5.getValue(),s6.getValue(), (int) (Math.random()*1000));

            }
        });
        this.setPreferredSize(new Dimension(300, SimulationPanel.HEIGHT));
        this.setLayout(new FlowLayout());
        p1.add(s1);
        p1.add(l1);
        p2.add(s2);
        p2.add(l2);
        p3.add(s3);
        p3.add(l3);
        p4.add(s4);
        p4.add(l4);
        p5.add(s5);
        p5.add(l5);
        p6.add(s6);
        p6.add(l6);
        this.add(o1);
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(o2);
        this.add(p5);
        this.add(o3);
        this.add(p6);
        this.add(b);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
    l1.setText(String.valueOf((double) s1.getValue()/100));
        l2.setText(String.valueOf((double) s2.getValue()/100));
        l3.setText(String.valueOf((double) s3.getValue()/100));
        l4.setText(String.valueOf((double) s4.getValue()/100));
        l5.setText(String.valueOf( s5.getValue()));
        l6.setText(String.valueOf( s6.getValue()));


    }
}
