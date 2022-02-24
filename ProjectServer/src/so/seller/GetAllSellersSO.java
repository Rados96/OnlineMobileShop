/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.seller;

import domain.GeneralDomainObject;
import domain.Seller;
import java.util.ArrayList;
import so.AbstractGenericOperation;

/**
 *
 * @author Rados
 */
public class GetAllSellersSO extends AbstractGenericOperation{
    private ArrayList<Seller> list;

    public GetAllSellersSO() {
        list = new ArrayList<>();
    }

    public ArrayList<Seller> getList() {
        return list;
    }
    
    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception{
        if(!(gdo instanceof Seller)){
            throw new Exception("Neispravan parametar za prodavca");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception{
        ArrayList<GeneralDomainObject> gdoList = dBBroker.getall((Seller)gdo);
        for (GeneralDomainObject generalDomainObject : gdoList) {
            Seller s = (Seller)generalDomainObject;
            list.add(s);
            System.out.println(s);
        }
    }
    
}
