/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.order.model.item;

import domain.OrderItem;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rados
 */
public class TableModelOrderItem extends AbstractTableModel{

    private ArrayList<OrderItem> orderItems;
    private String[] columnNames = new String[]{"Redni broj","Proizvod","Količina","Iznos"};

    public TableModelOrderItem(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    
    @Override
    public int getRowCount() {
        return orderItems.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OrderItem oi = orderItems.get(rowIndex);
        switch(columnIndex){
            case 0:
                return oi.getSerialNumber();
            case 1:
                return oi.getProduct();
            case 2:
                return oi.getQuantity();
            case 3:
                return oi.getPrice();
            default: return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
        fireTableDataChanged();
    }
    
    public OrderItem getOrderItem(int rowIndex){
        return orderItems.get(rowIndex);
    }
    
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        fireTableDataChanged();
    }
    
    public void removeOrderItems(){
        orderItems.clear();
        fireTableDataChanged();
    }
    
    public void removeOrderItem(int rowIndex){
        orderItems.remove(rowIndex);
        fireTableDataChanged();
    }

    public void add(OrderItem oi) {
        orderItems.add(oi);
        fixSerialNumbers();
        fireTableDataChanged();
    }

    private void fixSerialNumbers() {
        int counter = 0;
        for (OrderItem oi : orderItems) {
            counter++;
            oi.setSerialNumber(counter);
        }
    }
    
    
    
}
