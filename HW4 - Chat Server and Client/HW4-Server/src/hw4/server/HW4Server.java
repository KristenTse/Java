/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4.server;
import java.net.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Kristen
 */
public class HW4Server {
    static int portNum = 5190;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(portNum);
            while(true){
                Socket clientSock = ss.accept();
                ProcessConnection pc = new ProcessConnection(clientSock);
                pc.start();
            }
        } catch (IOException ex) {}
    }
}


class ProcessConnection extends Thread{
    private static ArrayList<ProcessConnection> clients = new ArrayList<ProcessConnection>();
    private final Socket clientSock;
    private String clientName;
    
    ProcessConnection(Socket newSock){
        clientSock = newSock;
        clients.add(this);
    }
    
    @Override
    public void run(){  
        try{
            Scanner sin = new Scanner(clientSock.getInputStream());
            clientName = sin.nextLine();

            String message = "";
            while (!message.equalsIgnoreCase("EXIT")){
                message = sin.nextLine();
                for (ProcessConnection client : clients){
                    if (client.clientName != null){
                        PrintStream newPS = new PrintStream(client.clientSock.getOutputStream());
                        newPS.println(" "+clientName+": "+message);
                    }
                }
            }
            clients.remove(this);
            clientSock.close();
        } catch(IOException ex) {}
    }
}