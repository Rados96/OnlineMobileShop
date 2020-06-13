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
public class GetAllClientsSO extends AbstractGenericOperation{

    public ArrayList<Client> list;

    public GetAllClientsSO() {
        list = new ArrayList<>();
    }
    
    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if(!(gdo instanceof Client)){
            throw new Exception("Neispravan parametar za klijenta");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        ArrayList<GeneralDomainObject> gdoList = dBBroker.getall((Client)gdo);
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
