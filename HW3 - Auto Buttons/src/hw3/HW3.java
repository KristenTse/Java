/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
/**
 *
 * @author Kristen
 */
public class HW3 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int length = 4;
        int width = 2;
        boolean[] arrIsPressed = new boolean[length*width];
        JButton[] arrButtons = new JButton[length*width];
        colorPicker cp = new colorPicker();
        ButtonHandler bh = new ButtonHandler(arrIsPressed, arrButtons, cp);
        
        //create a frame
        JFrame frame = new JFrame("HW 3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        
        //create a panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(length,width));
        frame.add(panel);
        
        //create buttons
        for (int i=0; i<arrButtons.length; i++){
            arrButtons[i] = new JButton();
            arrButtons[i].setActionCommand(""+i);
            arrButtons[i].setBackground(cp.select());
            panel.add(arrButtons[i]);
            arrButtons[i].addActionListener(bh);
        }
        
        //change button colors every second
        new Thread(){
            public void run(){
                while(true){
                    try {
                        sleep(1000);
                        for (int i=0; i<arrButtons.length; i++){
                            if (!arrIsPressed[i])
                                arrButtons[i].setBackground(cp.select());
                        }
                    } 
                    catch (InterruptedException ex) {}
                }
            }
        }.start();
        
        //set visible
        frame.setVisible(true);
    }
}


class colorPicker {        
    Random rand = new Random();
    
    public Color select(){
        return new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
    }
}


class ButtonHandler implements ActionListener{
    boolean[] arrIsPressed;
    JButton[] arrButtons;
    colorPicker cp;
   
    ButtonHandler(boolean[] newPressed, JButton[] newButtons, colorPicker newCol){
        arrIsPressed = newPressed;
        arrButtons = newButtons;
        cp = newCol;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        for (int i=0; i<arrButtons.length; i++){
            if (arrButtons[i].getActionCommand().equals(e.getActionCommand()))
                arrIsPressed[i] = !arrIsPressed[i];
        }
    }
}