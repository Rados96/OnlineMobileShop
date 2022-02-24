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
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Rados
 */
public class Mobile extends Product{
    private int memory;
    private String specification;
    private String color;

    public Mobile() {
    }

    public Mobile(int productID, String productCode, String model, double price, Manufacturer manufacturer, ProductType productType,int memory, String specification, String color) {
        super(productID, productCode, model, price, manufacturer, productType);
        this.memory = memory;
        this.specification = specification;
        this.color = color;
    }

    /**
     * @return the memory
     */
    public int getMemory() {
        return memory;
    }

    /**
     * @param memory the memory to set
     */
    public void setMemory(int memory) {
        this.memory = memory;
    }

    /**
     * @return the specification
     */
    public String getSpecification() {
        return specification;
    }

    /**
     * @param specification the specification to set
     */
    public void setSpecification(String specification) {
        this.specification = specification;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getTableName() {
        return "mobile";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "productID, memory, specification, color";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return("" + getProductID()
                + ", " + memory
                + ", '" + specification
                + "', '" + color + "'");
    }

    @Override
    public String getPrimaryKeyClause() {
        return "productID = " + getProductID();
    }

    @Override
    public String getUpdateValues() {
        //productID can't be changed
        return " memory = " + memory + ","
             + " specification = '" + specification + "',"
             + " color = '" + color + "'";
    }

    @Override
    public String getJoinClause() {
        return " m join product p on m.productID = p.productID join manufacturer mf on p.manufacturerID = mf.manufacturerID ";
    }

    @Override
    public String getOrderByClause() {
        return "p.productID";
    }

    @Override
    public ArrayList<GeneralDomainObject> getListFromResultSet(ResultSet rs) {
        ArrayList<GeneralDomainObject> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Manufacturer mn = new Manufacturer(rs.getInt("manufacturerID"),rs.getString("name"));
                Mobile mobile = new Mobile(rs.getInt("productID"), rs.getString("productCode") ,rs.getString("model"), rs.getDouble("price"), mn, ProductType.valueOf(rs.getString("productType")), rs.getInt("memory"), rs.getString("specification"), rs.getString("color"));
                list.add(mobile);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("sql greska kod mobile-getListFromResultSet");
        }
        return list;
    }
   
}
