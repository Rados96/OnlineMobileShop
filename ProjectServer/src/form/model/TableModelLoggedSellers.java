/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.model;

import domain.Seller;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.DateFormatter;

/**
 *
 * @author Rados
 */
public class TableModelLoggedSellers extends AbstractTableModel{
    
    private ArrayList<Seller> loggedSellers;
    String[] columnNames = {"Ime i prezime", "Korisnicko ime", "Vreme prijave"};
    
    public TableModelLoggedSellers() {
        loggedSellers = new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        return loggedSellers.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Seller s = loggedSellers.get(rowIndex);
        switch(columnIndex){
            case 0: return s.getName() + " " + s.getSurname();
            case 1: return s.getUsername();
            case 2: return util.DateFormatter.timeToStringApp(new Date());
            default: return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public ArrayList<Seller> getLoggedSellers() {
        return loggedSellers;
    }

    public void setLoggedSellers(ArrayList<Seller> loggedSellers) {
        this.loggedSellers = loggedSellers;
    }
    
    public void add(Seller s){
        loggedSellers.add(s);
        fireTableDataChanged();
    }
    
    public void remove(Seller s){
        loggedSellers.remove(s);
        fireTableDataChanged();
    }
    
    public void logout(Seller s){
        loggedSellers.remove(s);
        fireTableDataChanged();
    }
    
    public void removeAll(){
        this.loggedSellers = new ArrayList<>();
    }
    
}
