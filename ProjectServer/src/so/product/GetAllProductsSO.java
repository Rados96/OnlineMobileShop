/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.product;

import domain.GeneralDomainObject;
import domain.Product;
import java.util.ArrayList;
import so.AbstractGenericOperation;

/**
 *
 * @author Rados
 */
public class GetAllProductsSO extends AbstractGenericOperation{
    
    private ArrayList<Product> list;

    public GetAllProductsSO() {
        list = new ArrayList<>();
    }
    
    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if(!(gdo instanceof Product)){
            throw new Exception("Neispravan parametar za proizvod");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        ArrayList<GeneralDomainObject> gdoList = dBBroker.getall((Product)gdo);
        for (GeneralDomainObject generalDomainObject : gdoList) {
            Product p = (Product) generalDomainObject;
            list.add(p);
        }
    }
    
    public ArrayList<Product> getList() {
        System.out.println("Returned product list");
        return list;
    }
    
}
