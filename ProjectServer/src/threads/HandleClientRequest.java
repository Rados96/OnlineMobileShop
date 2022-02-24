/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import domain.Seller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Controler;
import transfer.ClientRequest;
import transfer.ServerResponse;
import constants.Operation;
import domain.City;
import domain.Client;
import domain.Manufacturer;
import domain.Order;
import domain.Product;
import domain.Type;
import java.util.ArrayList;

/**
 *
 * @author Rados
 */
public class HandleClientRequest extends Thread {

    private Socket socket;
    private boolean active;
    private String criterium;

    public HandleClientRequest(Socket socket) {
        this.socket = socket;
        active = true;
    }

    @Override
    public void run() {
        while (active) {
            ClientRequest cr = readRequest();
            ServerResponse sr = new ServerResponse();
            sr.setOperation(cr.getOperation());
            switch (cr.getOperation()) {

                case Operation.LOGIN:
                    try {
                        Seller s = Controler.getInstance().login((Seller) cr.getParameter());
                        sr.setData(s);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;
                case Operation.LOGOUT:
                    Seller s = (Seller) cr.getParameter();
                    Controler.getInstance().logout(s);
                    sr.setSuccessful(true);// na zahtev klijenta
                    break;
                case Operation.ADD_PRODUCT:
                    Product product = (Product) cr.getParameter();
                    try {
                        Controler.getInstance().addProduct(product);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        System.out.println("c");
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;
                case Operation.GET_ALL_PRODUCTS:
                    try {
                        ArrayList<Product> products = Controler.getInstance().getAllProducts();
                        sr.setData(products);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;
                case Operation.UPDATE_PRODUCT:
                    Product pr = (Product) cr.getParameter();
                    try {
                        Controler.getInstance().updateProduct(pr);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;
                case Operation.SEARCH_PRODUCTS:
                    criterium = (String) cr.getParameter();
                    try {
                        ArrayList<Product> products = Controler.getInstance().searchProducts(criterium);
                        sr.setData(products);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;

                case Operation.DELETE_PRODUCT:
                    Product prod = (Product) cr.getParameter();
                    try {
                        Controler.getInstance().deleteProduct(prod);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;
                case Operation.GET_ALL_MANUFACTURERS:
                    try {
                        ArrayList<Manufacturer> manufacturers = Controler.getInstance().getAllManufacturers();
                        sr.setData(manufacturers);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;

                case Operation.GET_ALL_TYPES:
                    try {
                        ArrayList<Type> types = Controler.getInstance().getAllTypes();
                        sr.setData(types);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;
                case Operation.ADD_CLIENT:
                    Client client = (Client) cr.getParameter();
                    try {
                        Controler.getInstance().addClient(client);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;
                case Operation.GET_ALL_CLIENTS:
                    try {
                        ArrayList<Client> clients = Controler.getInstance().getAllClients();
                        sr.setData(clients);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;
                case Operation.UPDATE_CLIENT:
                    Client cl = (Client) cr.getParameter();
                    try {
                        Controler.getInstance().updateClient(cl);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;
                case Operation.SEARCH_CLIENTS:
                    criterium = (String) cr.getParameter();
                    try {
                        ArrayList<Client> clients = Controler.getInstance().searchClients(criterium);
                        sr.setData(clients);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;
                case Operation.GET_ALL_CITIES:
                    try {
                        ArrayList<City> cities = Controler.getInstance().getAllCities();
                        sr.setData(cities);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;

                case Operation.ADD_ORDER:
                    Order order = (Order) cr.getParameter();
                    try {
                        Controler.getInstance().addOrder(order);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;

                case Operation.SEARCH_ORDERS:
                    criterium = (String) cr.getParameter();
                    try {
                        ArrayList<Order> orders = Controler.getInstance().searchOrders(criterium);
                        sr.setData(orders);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;    
                    
                case Operation.GET_ALL_ORDERS:
                    try {
                        ArrayList<Order> orders = Controler.getInstance().getAllOrders();
                        sr.setData(orders);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        System.out.println("bb");
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;

                case Operation.DELETE_ORDER:
                    Order o = (Order) cr.getParameter();
                    try {
                        Controler.getInstance().deleteOrder(o);
                        sr.setSuccessful(true);
                    } catch (Exception ex) {
                        sr.setError(ex);
                        sr.setSuccessful(false);
                    }
                    break;
            }
            sendResponse(sr);
        }
    }

    public ClientRequest readRequest() {
        ClientRequest cr = new ClientRequest();
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            cr = (ClientRequest) ois.readObject();
        } catch (IOException ex) {
            System.out.println("Interrupted IO! [S:receiving request]");
            active = false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HandleClientRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cr;
    }

    public void sendResponse(ServerResponse sr) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(sr);
            if (sr.getOperation() == Operation.LOGOUT) {
                active = false;
                socket.close();
                System.out.println("S:The client logged out!");
            }
        } catch (IOException ex) {
            System.out.println("Interrupted IO! [S:sending response]");
            active = false;
        }
    }

    public Socket getSocket() {
        return socket;
    }

}
