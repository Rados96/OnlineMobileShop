/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.client;

import domain.Client;
import domain.GeneralDomainObject;
import java.util.ArrayList;
import so.AbstractGenericOperation;

/**
 *
 * @author Rados
 */
public class SearchClientSO extends AbstractGenericOperation{

    public ArrayList<Client> list;
    private String criterium;
    
    public SearchClientSO(String criterium) {
        list = new ArrayList<>();
        this.criterium = criterium;
    }

    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if(!(gdo instanceof Client)){
            throw new Exception("Neispravan parametar za klijenta");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        ArrayList<GeneralDomainObject> gdoList = dBBroker.search(gdo, criterium);
        System.out.println("execute: "+gdoList);
        for (GeneralDomainObject generalDomainObject : gdoList) {
            System.out.println(generalDomainObject);
        }
        for (GeneralDomainObject generalDomainObject : gdoList) {
            Client c = (Client) generalDomainObject;
            list.add(c);
        }
    }

    public ArrayList<Client> getList() {
        System.out.println("Returned client list");
        return list;
    }
    
}
