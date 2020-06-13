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
public class Accessories extends Product{
    private String description;
    private Type type;

    public Accessories() {
    }

    public Accessories(int productID, String productCode, String model, double price, Manufacturer manufacturer, ProductType productType, String description, Type type) {
        super(productID, productCode, model, price, manufacturer, productType);
        this.description = description;
        this.type = type;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

    
    @Override
    public String getTableName() {
        return "accessories";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "productID, description, typeID";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return ("" + getProductID() + ", '" 
                + description + "', " 
                + type.getTypeID());
    }

    @Override
    public String getPrimaryKeyClause() {
        return "productID = " + getProductID();
    }

    @Override
    public String getUpdateValues() {
        //productID can't be changed
        return " description = '" + description + "',"
                + " typeID = " + type.getTypeID();
    }

    @Override
    public String getJoinClause() {
        return " a join product p on a.productID = p.productID join manufacturer mf on p.manufacturerID = mf.manufacturerID"
        + " join type t on a.typeID = t.typeID";
    }

    @Override
    public String getOrderByClause() {
        return " a.typeID";
    }

    @Override
    public ArrayList<GeneralDomainObject> getListFromResultSet(ResultSet rs) {
        ArrayList<GeneralDomainObject> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Type t = new Type(rs.getInt("typeID"), rs.getString("name"));
                Manufacturer mn = new Manufacturer(rs.getInt("manufacturerID"),rs.getString("name"));
                Accessories accessories = new Accessories(rs.getInt("productID"), rs.getString("productCode"), rs.getString("model"), rs.getDouble("price"), mn, ProductType.valueOf(rs.getString("productType")), rs.getString("description"), t);
                list.add(accessories);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("sql greska kod accessories-getListFromResultSet");
        }
        return list;
    }
}
