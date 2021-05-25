/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw5;
import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author Kristen
 */
public class HW5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //create frame
        JFrame frame = new JFrame("Analog Clock");
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //create panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.setOpaque(false);
        frame.add(panel);
        
        //create a label
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label, BorderLayout.SOUTH);
        
        //clock setup
        MyClock clock = new MyClock();
        clock.setOpaque(false);
        panel.add(clock, BorderLayout.CENTER);
        
        frame.getContentPane().setBackground(new Color(210, 230, 156));
        
        new Thread() {
            public void run() {
                while(true) {
                    clock.repaint();
                    label.setText(""+clock.getCal().getTime());
                    
                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {}
                }
            }
        }.start();
        
        //set to visible
        frame.setVisible(true);
    }
    
}


class MyClock extends JPanel{
    private int radius;
    private Calendar cal;
    
    MyClock(){
        super();
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g); 
        Graphics2D g2 = (Graphics2D) g; //only for stroke thickness adjustment
    
        int height = getSize().height;
        int width = getSize().width;
        if (height < width)
            radius = height/3;
        else
            radius = width/3;
        
        Point p;
        
        //face
        g.setColor(Color.WHITE);
        g.fillOval(width/2-radius, height/2-radius, radius*2, radius*2);
        
        //hour hand
        g.setColor(new Color(255, 123, 137)); //red
        g2.setStroke(new BasicStroke(4));
        p = getPoint("hour");
        g.drawLine(width/2, height/2, p.x + width/2, p.y + height/2);
        
        //minute hand
        g.setColor(new Color(133, 202, 210)); //blue
        g2.setStroke(new BasicStroke(3));
        p = getPoint("minute");
        g.drawLine(width/2, height/2, p.x + width/2, p.y + height/2);
        
        //second hand
        g.setColor(new Color(168, 133, 238)); //purple
        g2.setStroke(new BasicStroke(1));
        p = getPoint("second");
        g.drawLine(width/2, height/2, p.x + width/2, p.y + height/2);
        
        //clock center
        g.setColor(Color.black);
        g.fillOval(width/2 - 4, height/2 - 4, 8, 8);
    }
    
    Calendar getCal(){
        cal = Calendar.getInstance(); 
        return cal;
    }
    
    
    Point getPoint(String hand){
        getCal(); 
        int currHour = cal.get(Calendar.HOUR);
        int currMin = cal.get(Calendar.MINUTE);
        int currSec = cal.get(Calendar.SECOND);
        
        Point p = new Point();
        
        switch (hand) {
            case "hour":
                p.x = (int)((radius/1.5) * Math.cos(Math.toRadians((currHour * 30 - 90) + (currMin * 0.5))));
                p.y = (int)((radius/1.5) * Math.sin(Math.toRadians((currHour * 30 - 90) + (currMin * 0.5))));
                break;
            case "minute":
                p.x = (int)(radius * Math.cos(Math.toRadians(currMin * 6 - 90)));
                p.y = (int)(radius * Math.sin(Math.toRadians(currMin * 6 - 90)));
                break;
            case "second":
                p.x = (int)(radius * Math.cos(Math.toRadians(currSec * 6 - 90)));
                p.y = (int)(radius * Math.sin(Math.toRadians(currSec * 6 - 90)));
                break;
        }
        
        return p;
    }
}


class Point{
    int x, y;
    
    Point(int newx, int newy){
        x = newx;
        y = newy;
    }
    
    Point(){this(0, 0);}
}