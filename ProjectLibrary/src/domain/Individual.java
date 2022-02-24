/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.domainEnum.ClientType;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Rados
 */
public class Individual extends Client implements Serializable{
    private String name;
    private String surname;
    private int idNumber;

    public Individual() {
    }

    public Individual(int clientID, String clientCode, String telephone, String email, String adress, City city, ClientType clientType, String name, String surname, int idNumber) {
        super(clientID, clientCode, telephone, email, adress, city, clientType);
        this.name = name;
        this.surname = surname;
        this.idNumber = idNumber;
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
     * @return the idNumber
     */
    public int getIdNumber() {
        return idNumber;
    }

    /**
     * @param idNumber the idNumber to set
     */
    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    @Override
    public String getTableName() {
        return "individual";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "clientID, name, surname, idNumber";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return("" + getClientID()
                + ", '" + name
                + "', '" + surname
                + "', " + idNumber);
    }

    @Override
    public String getPrimaryKeyClause() {
        return "clientID = " + getClientID();
    }

    @Override
    public String getUpdateValues() {
        //clientID  can't be changed
        //idNumber can't be changed
        return " name = '" + name + "', "
                + "surname = '" + surname + "'";
    }

    @Override
    public String getJoinClause() {
        return " i join client c on i.clientID = c.clientID join city city on c.cityID = city.cityID";
    }

    @Override
    public String getOrderByClause() {
        return "i.name";
    }

    @Override
    public ArrayList<GeneralDomainObject> getListFromResultSet(ResultSet rs) {
        ArrayList<GeneralDomainObject> list = new ArrayList<>();
        try {
            while (rs.next()) {
                City cit = new City(rs.getInt("cityID"),rs.getInt("zipcode"), rs.getString("name"));
                Individual individual = new Individual(rs.getInt("clientID"), rs.getString("clientCode"), rs.getString("telephone"), rs.getString("email"), rs.getString("adress"), cit, ClientType.valueOf(rs.getString("clientType")), rs.getString("name"), rs.getString("surname"), rs.getInt("idNumber"));
                list.add(individual);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("sql greska kod individual-getListFromResultSet");
        }
        return list;
    }
    
    
}
