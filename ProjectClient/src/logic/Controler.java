/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.City;
import domain.Client;
import domain.Manufacturer;
import domain.Order;
import domain.Product;
import domain.Seller;
import domain.Type;
import form.FormLogin;
import form.FormMain;
import form.client.PanelSearchClient;
import form.product.PanelSearchProduct;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import session.Session;

/**
 *
 * @author Rados
 */
public class Controler {

    private static Controler instance;

    private FormLogin formLogin;
    private FormMain formMain;
    private Window activeWindow;

    private Controler() {
    }

    public static Controler getInstance() {
        if (instance == null) {
            instance = new Controler();
        }
        return instance;
    }

    public FormLogin getFormLogin() {
        return formLogin;
    }

    public FormMain getFormMain() {
        return formMain;
    }

    public Window getActiveWindow() {
        return activeWindow;
    }

    public void setFormLogin(FormLogin formLogin) {
        this.formLogin = formLogin;
    }

    public void setActiveWindow(Window activeWindow) {
        this.activeWindow = activeWindow;
    }

    public void setFormMain(FormMain formMain) {
        this.formMain = formMain;
    }

    public void loginSeller(Seller seller) {
        Session.getInstance().setLoggedSeller(seller);
        formLogin.loginSuccessful();
        formMain = new FormMain();
        formMain.setSeller(seller);
        formMain.setVisible(true);
        formMain.setLocationRelativeTo(null);
        //formMain.setExtendedState(MAXIMIZED_BOTH);
        formLogin.setVisible(false);
    }

    public void loginError(Exception e) {
        formLogin.loginError(e.getMessage());
    }

    public void logoutSeller(boolean successful) {
        if (formMain != null) {
            formMain.logout(successful);
            formMain.dispose();
            if (activeWindow != null) {
                activeWindow.dispose();
            }
        }
        formLogin.setVisible(true);
        formLogin.setBtnLoginEnabled();
        System.out.println("[C] logout!");
        Session.getInstance().setLoggedSeller(null);
    }

    public void productAdded(String status) {
        formMain.requestProductsForTable();

        JOptionPane.showMessageDialog(activeWindow, status, "Status", JOptionPane.INFORMATION_MESSAGE);

        int result = JOptionPane.showConfirmDialog(activeWindow, "Nastaviti unos?", "Pitanje", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            formMain.getPanelProduct().resetForNewProduct();
        } else {
            activeWindow.dispose();
            Controler.getInstance().setActiveWindow(formMain);
            Controler.getInstance().getFormMain().setPanelProduct(null);
        }
    }

    public void productAddedError(String status) {
        JOptionPane.showMessageDialog(activeWindow, "Sistem ne moze da doda proizvod - " + status, "Gre??ka", JOptionPane.ERROR_MESSAGE);
    }

    public void getAllProducts(ArrayList<Product> products) {
        String request = (String) Session.getInstance().getUseCaseParams().get("request product");
        switch (request) {
            case "FormMain":
                formMain.getModelProduct().setProducts(products);
                break;
            case "PanelSearchProduct":
                refreshTblSearchProducts(products);
                break;
        }
    }

    public void setTblProductsError(String status) {
        JOptionPane.showMessageDialog(activeWindow, status);
    }

    public void refreshTblSearchProducts(ArrayList<Product> products) {
        formMain.getPanelSearchProduct().setListForModelSearchProduct(products);
    }

    public void productDeleted(String status) {
        if (status.equals("Proizvod je uspe??no obrisan!")) {
            Product p = (Product) Session.getInstance().getUseCaseParams().get("product");
            formMain.getPanelSearchProduct().getModelSearchProduct().removeProduct(p);
            formMain.requestProductsForTable();

            JOptionPane.showMessageDialog(activeWindow, status, "Status",
                    JOptionPane.INFORMATION_MESSAGE);
            formMain.getPanelProduct().cancel();
        } else {
            status = "Sistem ne mo??e da obri??e proizvod: " + status;
            JOptionPane.showMessageDialog(activeWindow, status, "Status",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void productUpdated(String status) {
        if (status.equals("Podaci o proizvodu su izmenjeni.")) {
            Product p = (Product) Session.getInstance().getUseCaseParams().get("product");

            PanelSearchProduct panel = formMain.getPanelSearchProduct();
            panel.getModelSearchProduct().updateProduct(p);
            formMain.requestProductsForTable();
            JOptionPane.showMessageDialog(activeWindow, status, "Status",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            status = "Sistem ne mo??e da izmeni podatke o proizvodu: " + status;
            JOptionPane.showMessageDialog(activeWindow, status, "Status",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void populateCityCMBError(Exception error) {
        JOptionPane.showMessageDialog(activeWindow, error.getMessage(), "Gre??ka!",
                JOptionPane.ERROR_MESSAGE);
    }

    public void getAllCities(ArrayList<City> cities) {
        String request = (String) Session.getInstance().getUseCaseParams().get("request city");
        switch (request) {
            case "PanelClient":
                formMain.getPanelClient().populateCMBCities(cities);
                break;
            case "PanelSearchClient":
                formMain.getPanelSearchClient().populateCMBCities(cities);
                break;
        }
    }

    public void getAllCitiesError(Exception error) {
        JOptionPane.showMessageDialog(activeWindow, error.getMessage(), "Gre??ka!",
                JOptionPane.ERROR_MESSAGE);
    }

    public void getAllManufacturers(ArrayList<Manufacturer> manufacturers) {
        String request = (String) Session.getInstance().getUseCaseParams().get("request manufacturer");
        switch (request) {
            case "PanelProduct":
                formMain.getPanelProduct().populateCMBManufacturers(manufacturers);
                break;
            case "PanelSearchProduct":
                formMain.getPanelSearchProduct().populateCMBManufacturers(manufacturers);
                break;
        }
    }

    public void getAllManufacturersError(Exception error) {
        JOptionPane.showMessageDialog(activeWindow, error.getMessage(), "Gre??ka!",
                JOptionPane.ERROR_MESSAGE);
    }

    public void getAllTypes(ArrayList<Type> types) {
        String request = (String) Session.getInstance().getUseCaseParams().get("request type");
        switch (request) {
            case "PanelProduct":
                formMain.getPanelProduct().populateCMBTypes(types);
                break;
            case "PanelSearchProduct":
                formMain.getPanelSearchProduct().populateCMBTypes(types);
                break;
        }
    }

    public void getAllTypesError(Exception error) {
        JOptionPane.showMessageDialog(activeWindow, error.getMessage(), "Gre??ka!",
                JOptionPane.ERROR_MESSAGE);
    }

    public void clientUpdated(String status) {
        if (status.equals("Podaci o klijentu su izmenjeni.")) {
            Client c = (Client) Session.getInstance().getUseCaseParams().get("client");

            PanelSearchClient panel = formMain.getPanelSearchClient();
            panel.getModelSearchClient().updateClient(c);
            //formMain.requestClientsForTable();
            JOptionPane.showMessageDialog(activeWindow, status, "Status",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            status = "Sistem ne mo??e da izmeni podatke o klijent " + status;
            JOptionPane.showMessageDialog(activeWindow, status, "Status",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refreshTblSearchClients(ArrayList<Client> clients) {
        formMain.getPanelSearchClient().setListForModelSearchClient(clients);
    }

    public void setTblClientsError(String status) {
        JOptionPane.showMessageDialog(activeWindow, status);
    }

    public void getAllClients(ArrayList<Client> clients) {
        String request = (String) Session.getInstance().getUseCaseParams().get("request client");
        switch (request) {
            case "PanelSearchClient":
                refreshTblSearchClients(clients);
                break;
        }
    }

    public void clientAdded(String status) {
        //formMain.requestClientsForTable();

        JOptionPane.showMessageDialog(activeWindow, status, "Status", JOptionPane.INFORMATION_MESSAGE);

        int result = JOptionPane.showConfirmDialog(activeWindow, "Nastaviti unos?", "Pitanje", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            formMain.getPanelClient().resetForNewClient();
        } else {
            activeWindow.dispose();
            Controler.getInstance().setActiveWindow(formMain);
            Controler.getInstance().getFormMain().setPanelProduct(null);
        }
    }

    public void clientAddedError(String status) {
        JOptionPane.showMessageDialog(activeWindow, "Sistem ne moze da doda klijenta - " + status, "Gre??ka", JOptionPane.ERROR_MESSAGE);
    }

    public void orderAdded(String status) {
        JOptionPane.showMessageDialog(activeWindow, status, "Status",
                JOptionPane.INFORMATION_MESSAGE);
        formMain.getPanelOrders().requestOrders();

        int result = JOptionPane.showConfirmDialog(activeWindow, "Nastaviti unos?", "Pitanje",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            formMain.getPanelNewOrder().resetForNewOrder();
        } else {
            formMain.getPanelNewOrder().cancel();
        }
    }

    public void orderAddedError(String message) {
        JOptionPane.showMessageDialog(activeWindow,
                "Sistem ne mo??e da zapamti narud??bu: \n" + message, "Gre??ka",
                JOptionPane.ERROR_MESSAGE);
    }

    public void getAllOrders(ArrayList<Order> orders) {
        formMain.getPanelOrders().getModelOrder().setOrders(orders);
    }

    public void getAllOrdersError(String message) {
        JOptionPane.showMessageDialog(activeWindow, message, "Gre??ka",
                JOptionPane.ERROR_MESSAGE);
    }

    public void orderDeleted(String status) {
        if (status.equals("Narud??ba je uspe??no obrisana!")) {
            Order o = (Order) Session.getInstance().getUseCaseParams().get("order");
            formMain.getPanelOrders().getModelOrder().removeOrder(o);
            formMain.getPanelOrders().getModelOrderItem().setOrderItems(new ArrayList<>());

            JOptionPane.showMessageDialog(activeWindow, status, "Status",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            status = "Sistem ne mo??e da obri??e narud??bu: " + status;
            JOptionPane.showMessageDialog(activeWindow, status, "Status",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refreshTblSearchOrders(ArrayList<Order> orders) {
        formMain.getPanelOrders().setListForModelOrders(orders);
    }

    public void setTblOrdersError(String status) {
        JOptionPane.showMessageDialog(activeWindow, status);
    }

}
