/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rados
 */
public class DateFormatter {
    private static final SimpleDateFormat sdfApp = new SimpleDateFormat("dd.MM.yyyy");
    private static final SimpleDateFormat sdfDB = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdfTimeApp = new SimpleDateFormat("HH:mm");
    
    public static Date stringToDateApp(String dateString) throws Exception{
        try {
            Date date = sdfApp.parse(dateString);
            return date;
        } catch (ParseException ex) {
            throw new Exception("Neispravan format datuma!");
        }
    }
    
    public static String dateToStringApp(Date date){
        String dateString = sdfApp.format(date);
        return dateString;
    }
    
    public static String dateToStringDB(Date date){
        String dateString = sdfDB.format(date);
        return dateString;
    }
    
    public static Date stringToDateDB(String dateString) throws Exception{
        try {
            Date date = sdfDB.parse(dateString);
            return date;
        } catch (ParseException ex) {
            throw new Exception("Neispravan format datuma!");
        }
    }
    
    public static String timeToStringApp(Date date){
        String timeString = sdfTimeApp.format(date);
        return timeString;
    }
    
    public static Date stringToTimeApp(String timeString) throws Exception{
        try {
            Date time = sdfTimeApp.parse(timeString);
            return time;
        } catch (ParseException ex) {
            throw new Exception("Neispravan format vremena!");
        }
    }
    
    
    
}
