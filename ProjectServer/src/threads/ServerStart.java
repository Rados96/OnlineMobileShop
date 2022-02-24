/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import domain.Seller;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import settings.SettingsConnection;

/**
 *
 * @author Rados
 */
public class ServerStart extends Thread{
    private ServerSocket serverSocket;
    private boolean active;
    LinkedList<HandleClientRequest> activeSellers;
    LinkedList<Seller> loggedSellers;
    
    public ServerStart() {
        activeSellers = new LinkedList<>();
        loggedSellers = new LinkedList<>();
    }

    public LinkedList<HandleClientRequest> getActiveSellers() {
        return activeSellers;
    }

    public LinkedList<Seller> getLoggedSellers() {
        return loggedSellers;
    }

    public void setLoggedSellers(LinkedList<Seller> loggedSellers) {
        this.loggedSellers = loggedSellers;
    }

    public void setActiveSellers(LinkedList<HandleClientRequest> activeSellers) {
        this.activeSellers = activeSellers;
    }
    
    public boolean isActive() {
        return active;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    @Override
    public void run() {
        try {
            int port = Integer.valueOf(SettingsConnection.getInstance().getProperty("port"));
            serverSocket = new ServerSocket(port);
            System.out.println("Server started.");
            active = true;
            try{
                while (active) {                
                    Socket client = serverSocket.accept();
                    System.out.println("Client is connected.");
                    HandleClientRequest hcr = new HandleClientRequest(client);
                    activeSellers.add(hcr);
                    hcr.start();
                }
            }catch(IOException ex){
                System.out.println("Server is off. No connecting new clients!");
            }
        } catch (IOException ex) {
            System.out.println("The server cannot start");
        }
    }

    public void stopServer() {
        active = false;
        try {
            serverSocket.close();
            if(activeSellers!=null)activeSellers.clear();
        } catch (IOException ex) {
            System.out.println("The server cannot be stopped");
        }
    }
    
    
}
