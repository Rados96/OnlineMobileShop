/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

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
public class Seller implements GeneralDomainObject{
    private int sellerID;
    private String name;
    private String surname;
    private String username;
    private String password;

    public Seller() {
    }

    public Seller(int sellerID, String name, String surname, String username, String password) {
        this.sellerID = sellerID;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }
    
        /**
     * @return the sellerID
     */
    public int getSellerID() {
        return sellerID;
    }

    /**
     * @param sellerID the sellerID to set
     */
    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
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

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return name + " " + surname;
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
        final Seller other = (Seller) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String getTableName() {
        return "seller";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "sellerID, name, surname, username, password";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return("'" + sellerID + "', " + name + "', '" + surname + "', '" + username + "', '" + password + "'");
    }

    @Override
    public String getPrimaryKeyClause() {
        return "sellerID = '" + sellerID + "'";
    }

    @Override
    public String getUpdateValues() {
        //sellerID can't be changed
        return "name = '" + name + "',"
                + " surname = '" + surname +  "',"
                + " username = '" + username + "',"
                + " password = '" + password + "'";
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
                Seller s = new Seller(rs.getInt("sellerID"), rs.getString("name"), rs.getString("surname"), rs.getString("username"), rs.getString("password"));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Seller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("greska kod seller");
        }
        return list;
    }

    @Override
    public GeneralDomainObject load(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
