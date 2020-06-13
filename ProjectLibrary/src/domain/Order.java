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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rados
 */
public class Order implements GeneralDomainObject {

    private int orderID;
    private Date date;
    private Client client;
    private double totalPrice;
    private Seller seller;
    private ArrayList<OrderItem> OrderItems;

    public Order() {
        OrderItems = new ArrayList<>();
    }

    public Order(int orderID, Date date, Client client, double totalPrice, Seller seller, ArrayList<OrderItem> listItems) {
        this.orderID = orderID;
        this.date = date;
        this.client = client;
        this.totalPrice = totalPrice;
        this.seller = seller;
        this.OrderItems = listItems;
    }

    /**
     * @return the orderID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return the totalPrice
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the seller
     */
    public Seller getSeller() {
        return seller;
    }

    /**
     * @param seller the seller to set
     */
    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    /**
     * @return the OrderItems
     */
    public ArrayList<OrderItem> getOrderItems() {
        return OrderItems;
    }

    /**
     * @param OrderItems the OrderItems to set
     */
    public void setOrderItems(ArrayList<OrderItem> OrderItems) {
        this.OrderItems = OrderItems;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
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
        final Order other = (Order) obj;
        if (this.orderID != other.orderID) {
            return false;
        }
        return true;
    }
    

    @Override
    public String getTableName() {
        return "`order`";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "orderID, date, clientID, totalPrice, sellerID";
    }

    @Override
    public String getAttributeValuesForInsert() {
        java.sql.Date dateDB = new java.sql.Date(date.getTime());
        return ("" + orderID + ", '" + dateDB
                + "', " + client.getClientID()
                + ", " + totalPrice
                + ", " + seller.getSellerID());
    }

    @Override
    public String getPrimaryKeyClause() {
        return "orderID = " + orderID;
    }

    @Override
    public String getUpdateValues() {
        //orderID can't be changed
        java.sql.Date dateDB = new java.sql.Date(date.getTime());
        return "date = '" + dateDB + "',"
                + " clientID = " + client.getClientID() + ","
                + " totalPrice = " + totalPrice + ","
                + " sellerID = " + seller.getSellerID();
    }

    @Override
    public String getJoinClause() {
        return " o join seller s on o.sellerID = s.sellerID"
                + " join client cl on o.clientID = cl.clientID"
                + " join city c on cl.cityID = c.cityID";
    }

    @Override
    public String getOrderByClause() {
        return "o.date, o.totalPrice";
    }

    @Override
    public String getCodeClause() {
        return "orderID = " + getOrderID();
    }

    @Override
    public ArrayList<GeneralDomainObject> getListFromResultSet(ResultSet rs) {
        ArrayList<GeneralDomainObject> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Seller sell = new Seller(rs.getInt("sellerID"), rs.getString("s.name"), rs.getString("surname"), rs.getString("username"), rs.getString("password"));
                City cit = new City(rs.getInt("cityID"), rs.getInt("zipcode"), rs.getString("name"));
                Client cl = new Client(rs.getInt("clientID"), rs.getString("clientCode"), rs.getString("telephone"), rs.getString("email"), rs.getString("adress"), cit, ClientType.valueOf(rs.getString("clientType")));
                Date d = new Date(rs.getDate("date").getTime());
                Order o = new Order(rs.getInt("orderID"), d, cl, rs.getDouble("totalPrice"), sell, new ArrayList<>());
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("sql greska kod order-getListFromResultSet");
        }
        return list;
    }

    @Override
    public GeneralDomainObject load(ResultSet rs) throws SQLException {
        Order order = null;
        if (rs.next()) {
            Seller sell = new Seller(rs.getInt("sellerID"), rs.getString("s.name"), rs.getString("surname"), rs.getString("username"), rs.getString("password"));
            City cit = new City(rs.getInt("cityID"), rs.getInt("zipcode"), rs.getString("name"));
            Client cl = new Client(rs.getInt("clientID"), rs.getString("clientCode"), rs.getString("telephone"), rs.getString("email"), rs.getString("adress"), cit, ClientType.valueOf(rs.getString("clientType")));
            Date d = new Date(rs.getDate("date").getTime());
            order = new Order(rs.getInt("orderID"), d, cl, rs.getDouble("totalPrice"), sell, new ArrayList<>());
        }
        return order;
    }

}
