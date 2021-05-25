/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4.client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.net.*;

/**
 *
 * @author Kristen
 */
public class HW4Client {
    
    protected static volatile Socket client;
    protected static JTextArea textArea;
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chat Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        DrawWindow.connectWindow(frame);
        
        try{
            while (client == null){}
            Scanner sin = new Scanner(client.getInputStream());
            String message = "";
            while (!message.equalsIgnoreCase("EXIT")){
                message = sin.nextLine();
                textArea.setText(textArea.getText()+message+"\n");
            }
        } catch (IOException e){}
    }    
}


class DrawWindow{
    public static void connectWindow(JFrame frame){
        //create a panel
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 50));
        frame.add(panel);
        
        //create a second panel
        JPanel newPanel = new JPanel(new GridLayout(4, 1, 20, 20));
        panel.add(newPanel, BorderLayout.CENTER);
        
        //add text fields
        JTextField hostTF = new JTextField("Enter Host Name");
        JTextField portTF = new JTextField("Enter Port Number");
        newPanel.add(hostTF);
        newPanel.add(portTF);
        
        //add button
        JButton conBut = new JButton("Connect");
        conBut.addActionListener(new ConnectBH(frame, hostTF, portTF));
        newPanel.add(conBut);

        //set visible
        frame.setVisible(true);
    }
    
    public static void chatWindow(JFrame frame){
        //clear frame
        frame.getContentPane().removeAll();
        
        //create a panel
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(panel);
        
        //create second panel
        JPanel bPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        panel.add(bPanel, BorderLayout.PAGE_END);
        
        //add text area
        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        HW4Client.textArea = ta;
        panel.add(ta, BorderLayout.CENTER);
        
        //add type bar and send button
        JTextField textField = new JTextField("Enter your username first", 30);
        bPanel.add(textField);
        JButton sendButton = new JButton("send");
        sendButton.addActionListener(new SendBH(textField));
        bPanel.add(sendButton);

        //set visible
        frame.setVisible(true);
    }   
}


class ConnectBH implements ActionListener {
    private final JFrame frame;
    private final JTextField hostTF;
    private final JTextField portTF;
    
    ConnectBH(JFrame newFrame, JTextField newHost, JTextField newPort){
        frame = newFrame;
        hostTF = newHost;
        portTF = newPort;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        try {
            Socket s = new Socket(hostTF.getText(), Integer.parseInt(portTF.getText()));
            HW4Client.client = s;
            DrawWindow.chatWindow(frame);
        }
        catch (IOException ex) {}
    }
}


class SendBH implements ActionListener {
    private final JTextField textField;

    SendBH(JTextField newTextField) {
        textField = newTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        try {
            PrintStream ps = new PrintStream(HW4Client.client.getOutputStream());
            ps.print(textField.getText() + "\r\n");
            textField.setText("");
        } catch (IOException ex) {}
    }
}
