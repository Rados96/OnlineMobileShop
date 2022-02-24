/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settings;

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
public class SettingsDatebase {
    private static SettingsDatebase instance;
    private Properties property;

    private SettingsDatebase() {
        try {
            property = new Properties();
            property.load(new FileInputStream("settings_database.properties"));
        } catch (IOException ex) {
            Logger.getLogger(SettingsDatebase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static SettingsDatebase getInstance() {
        if(instance == null){
            instance = new SettingsDatebase();
        }
        return instance;
    }
    
    public String getProperty(String key) {
        return property.getProperty(key, "n/a");
    }
    
    public void setUrl(String url){
        try {
            property.setProperty("url", url);
            property.store(new FileOutputStream("settings_database.properties"), null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SettingsDatebase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SettingsDatebase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setUsername(String user){
        try {
            property.setProperty("user", user);
            property.store(new FileOutputStream("settings_database.properties"), null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SettingsDatebase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SettingsDatebase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setPassword(String password){
        try {
            property.setProperty("password", password);
            property.store(new FileOutputStream("settings_database.properties"), null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SettingsDatebase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SettingsDatebase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
