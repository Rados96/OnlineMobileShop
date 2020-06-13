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
public class UpdateClientSO extends AbstractGenericOperation{

    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if(!(gdo instanceof Client)){
            throw new Exception("Neispravan parametar za klijenta");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        Client c = (Client) gdo;
        Client client = new Client(c.getClientID(), c.getClientCode(), c.getTelephone(), c.getEmail(), c.getAdress(), c.getCity(), c.getClientType());
        
        dBBroker.update(gdo);
        dBBroker.update(client);
    }
    
}
