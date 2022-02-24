/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import constants.Operation;
import domain.City;
import domain.Client;
import domain.Manufacturer;
import domain.Order;
import domain.Product;
import domain.Seller;
import domain.Type;
import java.util.ArrayList;
import logic.Controler;
import transfer.ServerResponse;

/**
 *
 * @author Rados
 */
public class HandleServerResponse extends Thread {

    boolean active = true;
    private String status;

    void stopHandler() {
        active = false;
    }

    @Override
    public void run() {
        while (active) {
            ServerResponse sr = Communication.getInstance().readResponse();

            switch (sr.getOperation()) {
                case Operation.LOGIN:
                    if (sr.isSuccessful()) {
                        Controler.getInstance().loginSeller((Seller) sr.getData());
                    } else {
                        Controler.getInstance().loginError(sr.getError());
                    }
                    break;
                case Operation.LOGOUT:
                    Controler.getInstance().logoutSeller(sr.isSuccessful());
                    active = false;
                    break;

                case Operation.ADD_PRODUCT:
                    if (sr.isSuccessful()) {
                        status = "Proizvod je uspesno dodat!";
                        Controler.getInstance().productAdded(status);
                    } else {
                        status = sr.getError().getMessage();
                        Controler.getInstance().productAddedError(status);
                    }
                    break;

                case Operation.GET_ALL_PRODUCTS:
                    if (sr.isSuccessful()) {
                        ArrayList<Product> products = (ArrayList<Product>) sr.getData();
                        Controler.getInstance().getAllProducts(products);
                    } else {
                        Controler.getInstance().setTblProductsError(sr.getError().getMessage());
                    }
                    break;

                case Operation.SEARCH_PRODUCTS:
                    if (sr.isSuccessful()) {
                        ArrayList<Product> products = (ArrayList<Product>) sr.getData();
                        Controler.getInstance().getAllProducts(products);
                    } else {
                        Controler.getInstance().setTblProductsError(sr.getError().getMessage());
                    }
                    break;

                case Operation.UPDATE_PRODUCT:
                    if (sr.isSuccessful()) {
                        status = "Podaci o proizvodu su izmenjeni.";
                    } else {
                        status = sr.getError().getMessage();
                    }
                    Controler.getInstance().productUpdated(status);
                    break;
                case Operation.DELETE_PRODUCT:
                    if (sr.isSuccessful()) {
                        status = "Proizvod je uspešno obrisan!";
                    } else {
                        status = sr.getError().getMessage();
                    }
                    Controler.getInstance().productDeleted(status);
                    break;

                case Operation.GET_ALL_MANUFACTURERS:
                    if (sr.isSuccessful()) {
                        ArrayList<Manufacturer> manufacturers = (ArrayList<Manufacturer>) sr.getData();
                        Controler.getInstance().getAllManufacturers(manufacturers);
                    } else {
                        Controler.getInstance().getAllManufacturersError(sr.getError());
                    }
                    break;
                case Operation.GET_ALL_TYPES:
                    if (sr.isSuccessful()) {
                        ArrayList<Type> types = (ArrayList<Type>) sr.getData();
                        Controler.getInstance().getAllTypes(types);
                    } else {
                        Controler.getInstance().getAllTypesError(sr.getError());
                    }
                    break;

                case Operation.ADD_CLIENT:
                    if (sr.isSuccessful()) {
                        String status = "Klijent je uspesno dodat!";
                        Controler.getInstance().clientAdded(status);
                    } else {
                        status = sr.getError().getMessage();
                        Controler.getInstance().clientAddedError(status);
                    }
                    break;

                case Operation.GET_ALL_CLIENTS:
                    if (sr.isSuccessful()) {
                        ArrayList<Client> clients = (ArrayList<Client>) sr.getData();
                        Controler.getInstance().getAllClients(clients);
                    } else {
                        Controler.getInstance().setTblClientsError(sr.getError().getMessage());
                    }
                    break;

                case Operation.SEARCH_CLIENTS:
                    if (sr.isSuccessful()) {
                        ArrayList<Client> clients = (ArrayList<Client>) sr.getData();
                        Controler.getInstance().getAllClients(clients);
                        System.out.println(clients);
                    } else {
                        Controler.getInstance().setTblClientsError(sr.getError().getMessage());
                    }
                    break;

                case Operation.UPDATE_CLIENT:
                    if (sr.isSuccessful()) {
                        status = "Podaci o klijentu su izmenjeni.";
                    } else {
                        status = sr.getError().getMessage();
                    }
                    Controler.getInstance().clientUpdated(status);
                    break;

                case Operation.GET_ALL_CITIES:
                    if (sr.isSuccessful()) {
                        ArrayList<City> cities = (ArrayList<City>) sr.getData();
                        Controler.getInstance().getAllCities(cities);
                    } else {
                        Controler.getInstance().getAllCitiesError(sr.getError());
                    }
                    break;

                case Operation.ADD_ORDER:
                    if (sr.isSuccessful()) {
                        Controler.getInstance().orderAdded("Narudžba je uspešno sačuvana!");
                    } else {
                        Controler.getInstance().orderAddedError(sr.getError().getMessage());
                    }
                    break;

                case Operation.GET_ALL_ORDERS:
                    if (sr.isSuccessful()) {
                        ArrayList<Order> orders = (ArrayList<Order>) sr.getData();
                        Controler.getInstance().getAllOrders(orders);
                    } else {
                        Controler.getInstance().getAllOrdersError(sr.getError().getMessage());
                    }
                    break;
                    
                case Operation.SEARCH_ORDERS:
                    if (sr.isSuccessful()) {
                        ArrayList<Order> orders = (ArrayList<Order>) sr.getData();
                        Controler.getInstance().refreshTblSearchOrders(orders);
                    } else {
                        Controler.getInstance().setTblOrdersError(sr.getError().getMessage());
                    }
                    break;

                case Operation.DELETE_ORDER:
                    if (sr.isSuccessful()) {
                        status = "Narudžba je uspešno obrisana!";
                    } else {
                        status = sr.getError().getMessage();
                    }
                    Controler.getInstance().orderDeleted(status);
                    break;

            }
        }
    }

}
