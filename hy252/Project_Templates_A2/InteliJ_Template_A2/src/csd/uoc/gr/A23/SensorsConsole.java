package csd.uoc.gr.A23;
import csd.uoc.gr.A21.Sensor;
import csd.uoc.gr.A21.SensorLine;
import csd.uoc.gr.A22.*;
import org.jfugue.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory; import javax.swing.JButton; import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import csd.uoc.gr.A22.WrongPasswordException;
import csd.uoc.gr.A24.myThread;
/**
 * Takes as input an array of sensorlines and for each one of them
 * it shows each sensor as a button that toggles their violation status * @author tzitzik
 *
 */
class SensorsConsole extends JFrame {
    SensorLine[] sensorLines;
    void fill(){
        for(int i =0;i<sensorLines.length;i++){
            final SensorLine sl = sensorLines[i];
            JPanel sensorlinePanel = new JPanel(new GridLayout(0,4,5,5));
            sensorlinePanel.setBorder((BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),sl.getName())));
            for (final Sensor s: sl.getSensors()) { // gets the array of active sensors
                final JButton t = new JButton(s.getId()); // creation of button
                t.setToolTipText(s.toString());
                t.setOpaque(true);
                t.setBackground(s.getViolation()? Color.RED: Color.GREEN);
                t.addActionListener(
                        new ActionListener() {
                            public void actionPerformed
                                    (java.awt.event.ActionEvent e) {
                                s.setOn(true);
                                s.setViolation(!s.getViolation());
                                t.setLabel(s.getId());
                                t.setBackground(s.getViolation()? Color.RED:Color.GREEN);
                                t.setToolTipText(s.toString());
                            }
                        });
                sensorlinePanel.add(t);
                add(sensorlinePanel);
            }
        }
        setVisible(true);
    }
    SensorsConsole(String title, SensorLine[] sensorLines) {
        this.sensorLines = sensorLines;
        this.setTitle(title);
        setBounds(200, 100, 800, 600);
        setLayout(new GridLayout(0, 1));
        setVisible(true);
        fill();
    }
}

class AlarmConsole extends JFrame {
    HomeSecurity homeSecurityController;
    JLabel labelStatus;
    JLabel labelLog;
    JButton buttonArm;
    JButton buttonStay;
    JButton buttonDisarm;
    JButton buttonChangeCode;
    JTextField inputField;

    AlarmConsole(HomeSecurity homeSecurityController) {
        this.homeSecurityController = homeSecurityController;
        this.setTitle("ALARM");
        setBounds(44, 444, 444, 444); //x, y, width, height
        setLayout(new GridLayout(0, 1)); // rows, columns
        setVisible(true);
        labelStatus = new JLabel("Status: " + homeSecurityController.getMode());
        labelLog = new JLabel("Output messages: ");
        buttonArm = new JButton("ARM");
        buttonStay = new JButton("STAY");
        buttonDisarm = new JButton("DISARM");
        buttonChangeCode = new JButton("Change Code");
        inputField = new JTextField("Input");
        JPanel panel = new JPanel(new GridLayout(0, 1, 4, 4));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Alarm Console"));
        panel.add(labelStatus);
        panel.add(buttonArm);
        panel.add(buttonStay);
        panel.add(buttonDisarm);
        panel.add(buttonChangeCode);
        panel.add(inputField);
        panel.add(labelLog);
        add(panel);
        setVisible(true);
        setup();
    }
    void armbut(){
        buttonArm.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (homeSecurityController.getMode() == mode.disarmed) {
                            homeSecurityController.setArmed();
                            labelStatus.setText("Status:" + homeSecurityController.getMode());
                            labelLog.setText("Armed Successfully");

                        } else {
                            labelLog.setText("Log: Wrong mode");
                        }
                    }
                }

        );
    }
    void chPassbut(){
        buttonChangeCode.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        try{
                            if(homeSecurityController.getMode()==mode.disarmed){
                            homeSecurityController.changePass(Integer.parseInt(inputField.getText()));
                            labelStatus.setText("Status: " + homeSecurityController.getMode());
                            labelLog.setText("Changed code successfully");}
                            else{labelLog.setText("Cant change password");}
                        }catch (Exception ex){
                            labelLog.setText(""+ex);}
                    }
                }
        );
    }
    void staybut(){
        buttonStay.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (homeSecurityController.getMode() == mode.disarmed) {
                            try {
                                homeSecurityController.setStay();
                                labelStatus.setText("Status: Stay");
                                labelLog.setText("Stay Activated");
                            } catch (Exception ex) {
                                labelLog.setToolTipText("");
                            }
                        } else {
                            labelLog.setText("Log: Wrong mode");
                        }
                    }
                }
        );
    }
    public void disbut(){
        buttonDisarm.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try{
                            if(homeSecurityController.matchPassword(Integer.parseInt(inputField.getText()))){
                                homeSecurityController.setDisarmed(Integer.parseInt(inputField.getText()));
                                labelStatus.setText("Status: " + homeSecurityController.getMode());
                                labelLog.setText("Log: " + homeSecurityController.getMode());
                            }else{
                                labelLog.setText("Wrong pass");
                            }
                        }catch(Exception ex){
                            labelLog.setText("wrong pass");
                        }
                    }
                }
        );
    }
    void setup() {
        armbut();
        chPassbut();
        staybut();
        disbut();
    }
}

class SensorsGUIapp {
    public static void main(String[] args) {
        System.out.println("SENSORS GUI");
// A. CREATION OF SENSORS
        int K = 3; // number of sensor lines to be created
        int M = 4; // number of sensors per line
        SensorLine[] internallines = new SensorLine[K]; // internal sensor lines
        SensorLine[] externallines = new SensorLine[K]; // external sensor lines
        // creation of internal and sensor lines
        for(int i = 0;i<K;i++){
            internallines[i] = new SensorLine("int"+i);
            externallines[i] = new SensorLine("int"+i);
            for(int j=0;j<M;j++){
                Sensor s = new Sensor("S0" + i, true, false);
                internallines[i].add(s);
                externallines[i].add(s);
            }
        }
// B. PASSES THE ARRAY OF SENSORS TO TWO SensorConsoles
        SensorsConsole internalSensorsConsole = new SensorsConsole("Internal Sensor Lines", internallines);
        SensorsConsole externalSensorsConsole = new SensorsConsole("External Sensor Lines", externallines);
// C. CREATION OF HomeSecurityController (from Askisi 2)
        HomeSecurity homeSecurityController = new HomeSecurity(internallines, externallines);
        // D. PASSES HomeSecurityController TO AlarmConsole
        AlarmConsole alarmConsole = new AlarmConsole(homeSecurityController);
    }
}