/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import domain.Seller;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Rados
 */
public class Session {
    private static Session instance;
    
    private Seller loggedSeller;
    private final Map<String,Object> useCaseParams;
    private String currentUseCase;

    public static Session getInstance() {
        if(instance == null){
            instance = new Session();
        }
        return instance;
    }

    public Session() {
        useCaseParams = new HashMap<>();
    }

    public Seller getLoggedSeller() {
        return loggedSeller;
    }

    public void setLoggedSeller(Seller loggedSeller) {
        this.loggedSeller = loggedSeller;
    }

    public Map<String, Object> getUseCaseParams() {
        return useCaseParams;
    }

    public String getCurrentUseCase() {
        return currentUseCase;
    }

    public void setCurrentUseCase(String currentUseCase) {
        this.currentUseCase = currentUseCase;
    }
    
    
}
