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
public class Type implements GeneralDomainObject {

    private int typeID;
    private String name;

    public Type() {
    }

    public Type(int typeID, String name) {
        this.typeID = typeID;
        this.name = name;
    }
    
    /**
     * @return the typeID
     */
    public int getTypeID() {
        return typeID;
    }

    /**
     * @param typeID the typeID to set
     */
    public void setTypeID(int typeID) {
        this.typeID = typeID;
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
        final Type other = (Type) obj;
        if (this.getTypeID() != other.getTypeID()) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "type";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "typeID, name";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return ("'" + typeID + "', '" + name + "'");
    }

    @Override
    public String getPrimaryKeyClause() {
        return "typeID = '" + typeID + "'";
    }

    @Override
    public String getUpdateValues() {
        //typeID can't be changed
        return "name = '" + name + "'";
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
                Type type = new Type(rs.getInt("typeID"), rs.getString("name"));
                list.add(type);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Type.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Greska kod vrste");
        }
        return list;
    }

    @Override
    public GeneralDomainObject load(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
