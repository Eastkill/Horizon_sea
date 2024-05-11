package WindowHandle;

import World.World;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParametersPanel extends JPanel implements ChangeListener {
    JSlider s1 = new JSlider(-100,100);
    JLabel l1 = new JLabel(String.valueOf(s1.getValue()/100));
    JSlider s2 = new JSlider(-100,100);
    JLabel l2 = new JLabel(String.valueOf(s1.getValue()/100));
    JSlider s3 = new JSlider(-100,100);
    JLabel l3 = new JLabel(String.valueOf(s1.getValue()/100));
    JSlider s4 = new JSlider(-100,100);
    JLabel l4 = new JLabel(String.valueOf(s1.getValue()/100));
    JButton b = new JButton("Generate");

    public ParametersPanel(){
        s1.addChangeListener(this);
        s2.addChangeListener(this);
        s3.addChangeListener(this);
        s4.addChangeListener(this);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                World.setInstance((double) s1.getValue() /100, (double) s2.getValue() /100, (double) s3.getValue() /100, (double) s4.getValue() /100);
                System.out.println("Nowy Świat!!!");
            }
        });
        this.setPreferredSize(new Dimension(200, SimulationPanel.HEIGHT));
        this.add(s1);
        this.add(l1);
        this.add(s2);
        this.add(l2);
        this.add(s3);
        this.add(l3);
        this.add(s4);
        this.add(l4);
        this.add(b);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
    l1.setText(String.valueOf((double) s1.getValue()/100));
        l2.setText(String.valueOf((double) s2.getValue()/100));
        l3.setText(String.valueOf((double) s3.getValue()/100));
        l4.setText(String.valueOf((double) s4.getValue()/100));
    }
}
