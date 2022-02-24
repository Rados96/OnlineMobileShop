/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.domainEnum.ClientType;
import domain.domainEnum.ProductType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rados
 */
public class OrderItem implements GeneralDomainObject {

    private Order order;
    private int serialNumber;
    private Product product;
    private int quantity;
    private double price;

    public OrderItem() {
    }

    public OrderItem(Order order, int serialNumber, Product product, int quantity, double price) {
        this.order = order;
        this.serialNumber = serialNumber;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * @return the serialNumber
     */
    public int getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber the serialNumber to set
     */
    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String getTableName() {
        return "orderItem";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "orderID, serialNumber, productID, quantity, price";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return ("" + order.getOrderID() + ", " + getSerialNumber()
                + ", " + product.getProductID()
                + ", " + quantity
                + ", " + price);
    }

    @Override
    public String getPrimaryKeyClause() {
        return "oi.orderID = " + order.getOrderID() + " AND oi.serialNumber = " + getSerialNumber();
    }

    @Override
    public String getUpdateValues() {
        //orderID and serialNumber can't be changed
        return " productID = " + getProduct().getProductID() + ","
                + " quantity = " + getQuantity() + ","
                + " price = " + getPrice();
    }

    @Override
    public String getJoinClause() {
        return " oi join product p on oi.productID = p.productID"
                + " join manufacturer m on p.manufacturerID = m.manufacturerID"
                + " join `order` o on oi.orderID = o.orderID"
                + " join seller s on o.sellerID = s.sellerID"
                + " join client cl on o.clientID = cl.clientID"
                + " join city c on cl.cityID = c.cityID";
        
    }

    @Override
    public String getOrderByClause() {
        return "oi.orderID desc, oi.serialNumber";
    }

    @Override
    public ArrayList<GeneralDomainObject> getListFromResultSet(ResultSet rs) {
        ArrayList<GeneralDomainObject> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Manufacturer manufacturer = new Manufacturer(rs.getInt("manufacturerID"), rs.getString("m.name"));
                Product pr = new Product(rs.getInt("productID"), rs.getString("productCode"), rs.getString("model"), rs.getDouble("price"), manufacturer, ProductType.valueOf(rs.getString("productType")));
                Seller sell = new Seller(rs.getInt("sellerID"), rs.getString("s.name"), rs.getString("surname"), rs.getString("username"), rs.getString("password"));
                City city = new City(rs.getInt("cityID"), rs.getInt("zipcode"), rs.getString("c.name"));
                Client cl = new Client(rs.getInt("clientID"), rs.getString("clientCode"), rs.getString("telephone"), rs.getString("email"), rs.getString("adress"), city, ClientType.valueOf(rs.getString("clientType")));
                Date d = new Date(rs.getDate("date").getTime());
                Order o = new Order(rs.getInt("orderID"), d, cl, rs.getDouble("totalPrice"), sell, new ArrayList<>());
                OrderItem orderItem = new OrderItem(o, rs.getInt("serialNumber"), pr, rs.getInt("quantity"), rs.getDouble("oi.price"));
                list.add(orderItem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("sql greska kod orderItem-getListFromResultSet");
        }
        return list;
    }

    @Override
    public GeneralDomainObject load(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
