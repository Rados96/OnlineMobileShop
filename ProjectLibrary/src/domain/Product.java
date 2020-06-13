/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.domainEnum.ProductType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rados
 */
public class Product implements GeneralDomainObject {

    protected int productID;
    private String productCode;
    private String model;
    private double price;
    private Manufacturer manufacturer;
    private ProductType productType;

    public Product() {
    }

    public Product(int productID, String productCode, String model, double price, Manufacturer manufacturer, ProductType productType) {
        this.productID = productID;
        this.productCode = productCode;
        this.model = model;
        this.price = price;
        this.manufacturer = manufacturer;
        this.productType = productType;
    }

    /**
     * @return the productID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * @param productID the productID to set
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * @return the productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode the productCode to set
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the manufacturer
     */
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return the productType
     */
    public ProductType getProductType() {
        return productType;
    }

    /**
     * @param productType the productType to set
     */
    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return getModel();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.productCode, other.productCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "product";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "productID, productCode, model, price, manufacturerID, productType";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return (productID + ", '"
                + productCode + "', '"
                + model + "', "
                + price + ", "
                + manufacturer.getManufacturerID() + ", '"
                + productType.toString() + "'");
    }

    @Override
    public String getPrimaryKeyClause() {
        return "productID = " + productID;
    }

    @Override
    public String getUpdateValues() {
        //productID can't be changed
        // productCode can't be changed
        // productType can't be changed
        return  " model = '" + model + "',"
                + " price = " + price + ","
                + " manufacturerID = " + manufacturer.getManufacturerID();
    }

    @Override
    public String getJoinClause() {
        return " p join manufacturer m on p.manufacturerID = m.manufacturerID";
    }

    @Override
    public String getOrderByClause() {
        return "p.price desc";
    }

    @Override
    public ArrayList<GeneralDomainObject> getListFromResultSet(ResultSet rs) {
        ArrayList<GeneralDomainObject> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Manufacturer mn = new Manufacturer(rs.getInt("manufacturerID"), rs.getString("name"));
                Product pr = new Product(rs.getInt("productID"), rs.getString("productCode"), rs.getString("model"), rs.getDouble("price"), mn, ProductType.valueOf(rs.getString("productType")));
                list.add(pr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("sql greska kod product-getListFromResultSet");
        }
        return list;
    }

    @Override
    public String getCodeClause() {
        return "productCode = '" + getProductCode() + "'";
    }

    @Override
    public GeneralDomainObject load(ResultSet rs) throws Exception {
        Product product = null;
        if (rs.next()) {
            Manufacturer m = new Manufacturer(rs.getInt("manufacturerID"), rs.getString("name"));
            product = new Product(rs.getInt("productID"), rs.getString("productCode"), rs.getString("model"), rs.getDouble("price"), m, ProductType.valueOf(rs.getString("productType")));
        }
        return product;
    }

}
