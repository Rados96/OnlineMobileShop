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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rados
 */
public class Client implements GeneralDomainObject{
    private int clientID;
    private String clientCode;
    private String telephone;
    private String email;
    private String adress;
    private City city;
    private ClientType clientType;

    public Client() {
    }

    public Client(int clientID, String clientCode, String telephone, String email, String adress, City city, ClientType clientType) {
        this.clientID = clientID;
        this.clientCode = clientCode;
        this.telephone = telephone;
        this.email = email;
        this.adress = adress;
        this.city = city;
        this.clientType = clientType;
    }

       /**
     * @return the clientID
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * @param clientID the clientID to set
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    /**
     * @return the clientCode
     */
    public String getClientCode() {
        return clientCode;
    }

    /**
     * @param clientCode the clientCode to set
     */
    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the adress
     */
    public String getAdress() {
        return adress;
    }

    /**
     * @param adress the adress to set
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * @return the city
     */
    public City getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * @return the clientType
     */
    public ClientType getClientType() {
        return clientType;
    }

    /**
     * @param clientType the clientType to set
     */
    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }
    
    @Override
    public String toString() {
        return email;
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
        final Client other = (Client) obj;
        if (!Objects.equals(this.clientCode, other.clientCode)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String getTableName() {
        return "client";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "clientID, clientCode, telephone, email, adress, cityID, clientType";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return ( clientID + ", '"
                + clientCode + "', '"
                + telephone + "', '"
                + email + "', '"
                + adress + "', "
                + city.getCityID() + ", '"
                + clientType.toString() + "'");
    }

    @Override
    public String getPrimaryKeyClause() {
        return "clientID = " + clientID;
    }

    @Override
    public String getUpdateValues() {
        //clientID can't be changed
        //clientCode can't be changed
        // clientType can't be changed
        return " telephone = '" + getTelephone() + "',"
                + " email = '" + getEmail() + "',"
                + " adress = '" + getAdress() + "',"
                + " cityID = " + getCity().getCityID();
    }

    @Override
    public String getJoinClause() {
        return " cl join city c on cl.cityID = c.cityID";
    }

    @Override
    public String getOrderByClause() {
        return " cl.clientID";
    }

    @Override
    public ArrayList<GeneralDomainObject> getListFromResultSet(ResultSet rs) {
        ArrayList<GeneralDomainObject> list = new ArrayList<>();
        try {
            while (rs.next()) {
                City cit = new City(rs.getInt("cityID"),rs.getInt("zipcode"), rs.getString("name"));
                Client client = new Client(rs.getInt("clientID"), rs.getString("clientCode"), rs.getString("telephone"), rs.getString("email"), rs.getString("adress"), cit, ClientType.valueOf(rs.getString("clientType")));
                list.add(client);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("sql greska kod client-getListFromResultSet");
        }
        return list;
    }
    
    @Override
    public String getCodeClause() {
        return "clientCode= '" + clientCode + "'";
    }

    @Override
    public GeneralDomainObject load(ResultSet rs) throws SQLException {
        Client client = null;
        if(rs.next()){
            City c = new City(rs.getInt("cityID"),rs.getInt("zipcode"), rs.getString("name"));
            client = new Client(rs.getInt("clientID"), rs.getString("clientCode"), rs.getString("telephone"), rs.getString("email"), rs.getString("adress"), c, ClientType.valueOf(rs.getString("clientType")));
        }
        return client;
    }


}
