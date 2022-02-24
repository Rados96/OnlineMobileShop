/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import constants.Operation;
import domain.Accessories;
import domain.City;
import domain.Client;
import domain.Individual;
import domain.LegalEntity;
import domain.Manufacturer;
import domain.Mobile;
import domain.Order;
import domain.Product;
import domain.Seller;
import domain.Type;
import domain.domainEnum.ProductType;
import form.FormServer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractGenericOperation;
import so.city.GetAllCitiesSO;
import so.client.GetAllClientsSO;
import so.client.SaveClientSO;
import so.client.SearchClientSO;
import so.client.UpdateClientSO;
import so.manufacturer.GetAllManufacturerSO;
import so.order.DeleteOrderSO;
import so.order.GetAllOrdersSO;
import so.order.SaveOrderSO;
import so.order.SearchOrderSO;
import so.product.DeleteProductSO;
import so.product.GetAllProductsSO;
import so.product.SaveProductSO;
import so.product.SearchProductSO;
import so.product.UpdateProductSO;
import so.seller.GetAllSellersSO;
import so.type.GetAllTypesSO;
import threads.ServerStart;
import threads.HandleClientRequest;
import transfer.ServerResponse;

/**
 *
 * @author Rados
 */
public class Controler {

    private static Controler instance;
    private FormServer formServer;
    ServerStart serverStart;

    private Controler() {
    }

    public static Controler getInstance() {
        if (instance == null) {
            instance = new Controler();
        }
        return instance;
    }

    public ServerStart getServerStart() {
        return serverStart;
    }

    public void setServerStart(ServerStart serverStart) {
        this.serverStart = serverStart;
    }

    public void stopServer() {
        ServerResponse sr = new ServerResponse();
        sr.setOperation(Operation.LOGOUT);
        sr.setSuccessful(false);//server nasilno prekida komunikaciju

        for (HandleClientRequest hcr : serverStart.getActiveSellers()) {
            try {
                hcr.sendResponse(sr);
                hcr.getSocket().close();
            } catch (IOException ex) {
                Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        serverStart.getLoggedSellers().clear();
        serverStart.stopServer();
        formServer.setTable();
    }

    public Seller login(Seller requestedSeller) throws Exception {
        if (serverStart.getLoggedSellers().contains(requestedSeller)) {
            throw new Exception("Prodavac je vec prijavljen!");
        }
        AbstractGenericOperation ago = new GetAllSellersSO();
        ago.templateExecute(requestedSeller);
        ArrayList<Seller> sellers = ((GetAllSellersSO) ago).getList();
        for (Seller s : sellers) {
            if (s.getUsername().equals(requestedSeller.getUsername())) {
                if (s.getPassword().equals(requestedSeller.getPassword())) {
                    serverStart.getLoggedSellers().add(s);
                    formServer.addLoggedSeller(s);
                    return s;
                } else {
                    throw new Exception("Pogresna lozinka");
                }
            }
        }
        throw new Exception("Korisnik sa unetim korisnickim imenom ne postoji");
    }

    public void logout(Seller s) {
        System.out.println(s + " is logged out");
        serverStart.getLoggedSellers().remove(s);
        formServer.logout(s);
    }

    public void setFormServer(FormServer formServer) {
        this.formServer = formServer;
    }

    public FormServer getFormServer() {
        return formServer;
    }

    public void addProduct(Product product) throws Exception {
        AbstractGenericOperation so = new SaveProductSO();
        so.templateExecute(product);
    }

    public ArrayList<Product> getAllProducts() throws Exception {
        AbstractGenericOperation so = new GetAllProductsSO();
        so.templateExecute(new Mobile());
        so.templateExecute(new Accessories());
        return ((GetAllProductsSO) so).getList();
    }

    public void updateProduct(Product product) throws Exception {
        AbstractGenericOperation so = new UpdateProductSO();
        so.templateExecute(product);
    }

    public void deleteProduct(Product product) throws Exception {
        AbstractGenericOperation so = new DeleteProductSO();
        so.templateExecute(product);
    }

    //search by criterion
    public ArrayList<Product> searchProducts(String criterium) throws Exception {
        String productType = criterium.substring(0, 2);
        AbstractGenericOperation so = new SearchProductSO(criterium);
        if (!productType.equals("m.") && !productType.equals("a.")) {
            so.templateExecute(new Product());
        } else {
            if (productType.equals("m.")) {
                so.templateExecute(new Mobile());
            }
            if (productType.equals("a.")) {
                so.templateExecute(new Accessories());
            }
        }
        return ((SearchProductSO) so).getList();
    }

    public ArrayList<Manufacturer> getAllManufacturers() throws Exception {
        AbstractGenericOperation so = new GetAllManufacturerSO();
        so.templateExecute(new Manufacturer());
        return ((GetAllManufacturerSO) so).getList();
    }

    public ArrayList<Type> getAllTypes() throws Exception {
        AbstractGenericOperation so = new GetAllTypesSO();
        so.templateExecute(new Type());
        return ((GetAllTypesSO) so).getList();
    }

    public void addClient(Client client) throws Exception {
        AbstractGenericOperation so = new SaveClientSO();
        so.templateExecute(client);
    }

    public ArrayList<Client> getAllClients() throws Exception {
        AbstractGenericOperation so = new GetAllClientsSO();
        so.templateExecute(new Individual());
        so.templateExecute(new LegalEntity());
        return ((GetAllClientsSO) so).getList();
    }

    public void updateClient(Client client) throws Exception {
        AbstractGenericOperation so = new UpdateClientSO();
        so.templateExecute(client);
    }

    public ArrayList<Client> searchClients(String criterium) throws Exception {
        String clientType = criterium.substring(0, 2);
        AbstractGenericOperation so = new SearchClientSO(criterium);
        if (!clientType.equals("i.") && !clientType.equals("le")) {
            so.templateExecute(new Client());
        } else {
            if (clientType.equals("i.")) {
                so.templateExecute(new Individual());
            }
            if (clientType.equals("le")) {
                so.templateExecute(new LegalEntity());
            }
        }
        return ((SearchClientSO) so).getList();
    }

    public ArrayList<City> getAllCities() throws Exception {
        AbstractGenericOperation so = new GetAllCitiesSO();
        so.templateExecute(new City());
        return ((GetAllCitiesSO) so).getList();
    }

    public void addOrder(Order order) throws Exception {
        AbstractGenericOperation so = new SaveOrderSO();
        so.templateExecute(order);
    }

    public ArrayList<Order> searchOrders(String criterium) throws Exception {
        AbstractGenericOperation so = new SearchOrderSO(criterium);
        so.templateExecute(new Order());
        return ((SearchOrderSO) so).getList();
    }

    public ArrayList<Order> getAllOrders() throws Exception {
        AbstractGenericOperation so = new GetAllOrdersSO();
        so.templateExecute(new Order());
        return ((GetAllOrdersSO) so).getList();
    }

    public void deleteOrder(Order order) throws Exception {
        AbstractGenericOperation so = new DeleteOrderSO();
        so.templateExecute(order);
    }

}
