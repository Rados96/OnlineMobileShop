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
public class City implements GeneralDomainObject {

    private int cityID;
    private int zipcode;
    private String name;

    public City() {
    }

    public City(int cityID, int zipcode, String name) {
        this.cityID = cityID;
        this.zipcode = zipcode;
        this.name = name;
    }

     /**
     * @return the cityID
     */
    public int getCityID() {
        return cityID;
    }

    /**
     * @param cityID the cityID to set
     */
    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    /**
     * @return the zipcode
     */
    public int getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
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
        final City other = (City) obj;
        if (this.cityID != other.cityID) {
            return false;
        }
        return true;
    }
    

    @Override
    public String getTableName() {
        return "city";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "cityID, zipcode, name";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return ("'" + cityID + "', '" + getZipcode() + "', '" + getName() + "'");
    }

    @Override
    public String getPrimaryKeyClause() {
        return "cityID = " + getCityID();
    }

    @Override
    public String getUpdateValues() {
        //cityID can't be changed
        //zipcode cant, be changed
        return " name = '" + getName() + "'";
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
                City city = new City(rs.getInt("cityID"),rs.getInt("zipcode"), rs.getString("name"));
                list.add(city);
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
