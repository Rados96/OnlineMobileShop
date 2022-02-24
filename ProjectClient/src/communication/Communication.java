/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.ClientRequest;
import transfer.ServerResponse;
import settings.SettingsConnection;

/**
 *
 * @author Rados
 */
public class Communication {
    private static Communication instance;
    Socket socket;
    HandleServerResponse hsr;

    public static Communication getInstance() {
        if(instance == null){
            instance = new Communication();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    private Communication() {
    
    }
    
    public void connect() throws IOException {
        int port = Integer.parseInt(SettingsConnection.getInstance().getProperty("port"));
        socket = new Socket("localhost", port);
        System.out.println("[C] connected on server");
        hsr = new HandleServerResponse();
        hsr.start();
    }

    public void sendRequest(ClientRequest cr) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(cr);
        } catch (IOException ex) {
            System.out.println("Interrupted IO! [C:sending request]");
            hsr.stopHandler();
        }
    }

    public ServerResponse readResponse() {       
        ServerResponse sr = new ServerResponse();
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            sr = (ServerResponse) ois.readObject();
        } catch (IOException ex) {
            System.out.println("Interrupted IO! [S:receiving response]");
            hsr.stopHandler();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sr;
    }
    
    
}
