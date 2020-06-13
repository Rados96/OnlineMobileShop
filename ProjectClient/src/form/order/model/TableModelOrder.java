/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.order.model;

import domain.Order;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import util.DateFormatter;

/**
 *
 * @author Rados
 */
public class TableModelOrder extends AbstractTableModel {

    private ArrayList<Order> orders;
    private String[] columnNames = new String[]{"NarudžbaID", "Datum", "Naručilac", "Ukupan iznos", "Prodavac"};

    public TableModelOrder(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @Override
    public int getRowCount() {
        return orders.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Order o = orders.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return o.getOrderID();
            case 1:
                return DateFormatter.dateToStringApp(o.getDate());
            case 2:
                return o.getClient();
            case 3:
                return o.getTotalPrice();
            case 4:
                return o.getSeller();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Order getOrder(int rowIndex) {
        return orders.get(rowIndex);
    }

    public void addOrder(Order order) {
        orders.add(order);
        fireTableDataChanged();
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
        fireTableDataChanged();
    }

    public void removeOrder(Order o) {
        orders.remove(o);
        fireTableDataChanged();
    }

}
