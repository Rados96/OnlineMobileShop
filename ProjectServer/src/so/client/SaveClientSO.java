/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.client;

import domain.Client;
import domain.GeneralDomainObject;
import so.AbstractGenericOperation;

/**
 *
 * @author Rados
 */
public class SaveClientSO extends AbstractGenericOperation{

    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if (!(gdo instanceof Client)) {
            throw new Exception("Neispravan parametar za klijenta");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        Client c = (Client) gdo;
        Client client = new Client(c.getClientID(), c.getClientCode(), c.getTelephone(), c.getEmail(), c.getAdress(), c.getCity(), c.getClientType());
        GeneralDomainObject gdoClient = dBBroker.find(client);
        Client cl = (Client) gdoClient;
        if (client.equals(cl)) {
            throw new Exception("Klijent već postoji u bazi!");
        }
        System.out.println("ddd");
        int id = dBBroker.save(client);
        System.out.println("id:"+id);
        c.setClientID(id);;
        dBBroker.save(c);
        
    }
    
}
