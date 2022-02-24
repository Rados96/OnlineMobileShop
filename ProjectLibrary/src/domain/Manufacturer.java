/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Rados
 */
public class Manufacturer implements GeneralDomainObject{
    private int manufacturerID;
    private String name;

    public Manufacturer() {
    }

    public Manufacturer(int manufacturerID, String name) {
        this.manufacturerID = manufacturerID;
        this.name = name;
    }

    /**
     * @return the manufacturerID
     */
    public int getManufacturerID() {
        return manufacturerID;
    }

    /**
     * @param manufacturerID the manufacturerID to set
     */
    public void setManufacturerID(int manufacturerID) {
        this.manufacturerID = manufacturerID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
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
        final Manufacturer other = (Manufacturer) obj;
        if (this.manufacturerID != other.manufacturerID) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "manufacturer";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "manufacturerID, name";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return ("'" + manufacturerID + "', " + name + "'");
    }

    @Override
    public String getPrimaryKeyClause() {
        return "manufacturerID = '" + manufacturerID + "'";
    }

    @Override
    public String getUpdateValues() {
        //manufacturerID can't be changed
        return "name = " + name + "'";
    }

    @Override
    public String getJoinClause() {
        return "";
    }

    @Override
    public String getOrderByClause() {
        return "name";
    }

    @Override
    public ArrayList<GeneralDomainObject> getListFromResultSet(ResultSet rs) {
        ArrayList<GeneralDomainObject> list = new ArrayList<>();
        try {
            while(rs.next()){
                Manufacturer manufacturer = new Manufacturer(rs.getInt("manufacturerID"),rs.getString("name"));
                list.add(manufacturer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(City.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Greska kod grada");
        }
        return list;
    }

    @Override
    public GeneralDomainObject load(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
