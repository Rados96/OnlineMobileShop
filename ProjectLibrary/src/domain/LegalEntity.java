/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.domainEnum.ClientType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Rados
 */
public class LegalEntity extends Client{
    private String companyRegistrationNumber;
    private String companyName;

    public LegalEntity() {
    }

    public LegalEntity(int clientID, String clientCode, String telephone, String email, String adress, City city, ClientType clientType,String companyRegistrationNumber, String companyName) {
        super(clientID, clientCode, telephone, email, adress, city, clientType);
        this.companyRegistrationNumber = companyRegistrationNumber;
        this.companyName = companyName;
    }


    /**
     * @return the companyRegistrationNumber
     */
    public String getCompanyRegistrationNumber() {
        return companyRegistrationNumber;
    }

    /**
     * @param companyRegistrationNumber the companyRegistrationNumber to set
     */
    public void setCompanyRegistrationNumber(String companyRegistrationNumber) {
        this.companyRegistrationNumber = companyRegistrationNumber;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return companyName;
    }
    
    @Override
    public String getTableName() {
        return "legalEntity";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "clientID, companyRegistrationNumber, companyName";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return("" + getClientID()
                + ", '" + companyRegistrationNumber
                + "', '" + companyName
                + "'");
                
    }

    @Override
    public String getPrimaryKeyClause() {
        return "clientID = " + getClientID();
    }

    @Override
    public String getUpdateValues() {
        //clientID  can't be changed
        // companyRegistrationNumber
        return  " companyName = '" + companyName + "'";
    }

    @Override
    public String getJoinClause() {
        return " le join client c on le.clientID = c.clientID join city city on c.cityID = city.cityID";
    }

    @Override
    public String getOrderByClause() {
        return "le.companyName";
    }

    @Override
    public ArrayList<GeneralDomainObject> getListFromResultSet(ResultSet rs) {
        ArrayList<GeneralDomainObject> list = new ArrayList<>();
        try {
            while (rs.next()) {
                City cit = new City(rs.getInt("cityID"),rs.getInt("zipcode"), rs.getString("name"));
                LegalEntity legalEntity = new LegalEntity(rs.getInt("clientID"),  rs.getString("clientCode"), rs.getString("telephone"), rs.getString("email"), rs.getString("adress"), cit, ClientType.valueOf(rs.getString("clientType")),rs.getString("companyRegistrationNumber"), rs.getString("companyName"));
                list.add(legalEntity);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("sql greska kod legalEntity-getListFromResultSet");
        }
        return list;
    }
    
}
