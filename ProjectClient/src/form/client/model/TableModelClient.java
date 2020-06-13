/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.client.model;

import domain.Client;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rados
 */
public class TableModelClient extends AbstractTableModel {

    public ArrayList<Client> clients;
    private final String[] columnNames = new String[]{"Šifra klijenta", "Telefon", "Email", "Adresa"};

    public TableModelClient(ArrayList<Client> clients) {
        this.clients = clients;
    }

    @Override
    public int getRowCount() {
        if (clients == null) {
            return 0;
        }
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Client c = clients.get(row);
        switch (column) {
            case 0:
                return c.getClientCode();
            case 1:
                return c.getTelephone();
            case 2:
                return c.getEmail();
            case 3:
                return c.getAdress();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        if (column > columnNames.length) {
            return "n/a";
        }
        return columnNames[column];
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
        fireTableDataChanged();
    }
    
    public Client getClient(int rowIndex){
        return clients.get(rowIndex);
    }

    public void addClient(Client c){
        clients.add(c);
        fireTableDataChanged();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Client c = clients.get(rowIndex);
        switch (columnIndex) {
            case 0:
                c.setClientCode(aValue.toString());
                break;
            case 1:
                c.setTelephone(aValue.toString());
                break;
            case 2:
                c.setEmail(aValue.toString());
                break;
            case 3:
                c.setAdress(aValue.toString());
                break;
        }
    }
    
    public void updateClient(Client c){
        for (Client client : clients) {
            if(client.equals(c)){
                //can't update clientID
                client.setTelephone(c.getTelephone());
                client.setEmail(c.getEmail());
                client.setAdress(c.getAdress());
                client.setCity(c.getCity());
                fireTableDataChanged();
            }
        }
    }
    
    public void removeClients(){
        clients.clear();
        fireTableDataChanged();
    }

}
