/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import util.DateFormatter;

/**
 *
 * @author Rados
 */
public class ClockThread extends Thread{
    JLabel lblClock;
    
    public ClockThread(JLabel lblClock) {
        this.lblClock = lblClock;
    }

    @Override
    public void run() {
        while (true) {
            lblClock.setText(DateFormatter.timeToStringApp(new Date()));
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClockThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
