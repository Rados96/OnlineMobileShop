/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.product;

import domain.GeneralDomainObject;
import domain.Product;
import so.AbstractGenericOperation;

/**
 *
 * @author Rados
 */
public class UpdateProductSO extends AbstractGenericOperation{

    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if(!(gdo instanceof Product)){
            throw new Exception("Neispravan parametar za proizvod");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        Product p = (Product) gdo;
        Product product = new Product(p.getProductID(), p.getProductCode(), p.getModel(), p.getPrice(), p.getManufacturer(), p.getProductType());
        
        dBBroker.update(gdo);
        dBBroker.update(product);
    }
    
}
