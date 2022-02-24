/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rados
 */
public class SettingsConnection {

    private static SettingsConnection instance;
    private Properties property;

    public static SettingsConnection getInstance() {
        if (instance == null) {
            instance = new SettingsConnection();
        }
        return instance;
    }

    private SettingsConnection() {
        try {
            property = new Properties();
            property.load(new FileInputStream("../ProjectLibrary/settings_connection.properties"));
        } catch (IOException ex) {
            Logger.getLogger(SettingsConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getProperty(String key) {
        return property.getProperty(key, "n/a");
    }

    public void setAdress(String adress){
        property.setProperty("adress", adress);
        try {
            property.store(new FileOutputStream(new File("../ProjectLibrary/settings_connection.properties")), null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SettingsConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SettingsConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setPort(String port){
        property.setProperty("port", port);
        try {
            property.store(new FileOutputStream(new File("../ProjectLibrary/settings_connection.properties")), null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SettingsConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SettingsConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
