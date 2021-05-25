/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
/**
 *
 * @author Kristen
 */
public class HW2 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int length = 4;
        int width = 2;
        JButton[] arrButtons = new JButton[length*width];
        colorPicker cp = new colorPicker();
        ButtonHandler bh = new ButtonHandler(arrButtons, cp);
        
        //create a frame
        JFrame frame = new JFrame("HW 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        
        //create a panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(length,width));
        frame.add(panel);
        
        //create buttons and add to panel
        for (int i=0; i<arrButtons.length; i++){
            arrButtons[i] = new JButton();
            arrButtons[i].setActionCommand(""+i);
            arrButtons[i].setBackground(cp.select());
            panel.add(arrButtons[i]);
            arrButtons[i].addActionListener(bh);
        }
        
        //set visible
        frame.setVisible(true);
    }
}

class colorPicker {        
    Random rand = new Random();
    Color col;
    public Color select(){
        col = new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
        return col;
    }
}

class ButtonHandler implements ActionListener{
    JButton[] arrButtons;
    colorPicker cp;
    ButtonHandler(JButton[] newArr, colorPicker newCol){
        arrButtons = newArr;
        cp = newCol;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        for (JButton jb : arrButtons){
            if (jb.getActionCommand() != e.getActionCommand()){
                jb.setBackground(cp.select());
            }
        }
    }
}
