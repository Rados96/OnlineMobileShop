/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.product.model;

import domain.City;
import domain.Product;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rados
 */
public class TableModelProduct extends AbstractTableModel {

    private ArrayList<Product> products;
    private final String[] columnNames = new String[]{"Šifra proizvoda", "Model", "Cena","Proizvođač"};

    public TableModelProduct(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public int getRowCount() {
        if (products == null) {
            return 0;
        }
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Product p = products.get(row);
        switch (column) {
            case 0:
                return p.getProductCode();
            case 1:
                return p.getModel();
            case 2:
                return p.getPrice();
            case 3:
                return p.getManufacturer();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        if(column > columnNames.length){
            return "n/a";
        }
        return columnNames[column];
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
        fireTableDataChanged();
    }
    
    public Product getProduct(int rowIndex){
        return products.get(rowIndex);
    }

    public void addProduct(Product p){
        products.add(p);
        fireTableDataChanged();
    }
    
    public void removeProducts(){
        products.clear();
        fireTableDataChanged();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Product p = products.get(rowIndex);
        switch(columnIndex){
            case 0:
                p.setProductCode(aValue.toString());
                break;
            case 1: 
                p.setModel(aValue.toString());
                break;
            case 2:
                p.setPrice(Double.valueOf(aValue.toString()));
                break;
        }
    }
    
    public void updateProduct(Product p){
        for (Product product : products) {
            if(product.equals(p)){
                //can't change productCode
                product.setModel(p.getModel());
                product.setPrice(p.getPrice());
                product.setManufacturer(p.getManufacturer());
                fireTableDataChanged();
            }
        }
    }

    public void removeProduct(Product p) {
        products.remove(p);
        fireTableDataChanged();
    }

    
    
}
