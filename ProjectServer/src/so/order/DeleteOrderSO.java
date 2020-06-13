/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.order;

import domain.GeneralDomainObject;
import domain.Order;
import domain.OrderItem;
import so.AbstractGenericOperation;

/**
 *
 * @author Rados
 */
public class DeleteOrderSO extends AbstractGenericOperation{

    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if(!(gdo instanceof Order)){
            throw new Exception("Neispravan parametar za narudžbu");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        Order o = (Order) gdo;
        Order order = new Order(o.getOrderID(), o.getDate(), o.getClient(), o.getTotalPrice(), o.getSeller(), o.getOrderItems());
        GeneralDomainObject gdoOrder = dBBroker.find(order);
        Order or = (Order) gdoOrder;
        System.out.println("idOr:"+or.getOrderID());
        if (!order.equals(or)) {
            throw new Exception("Narudžba ne postoji u bazi ili je u međuvremenu obrisana!");
        }
        dBBroker.delete(gdo);
        for (OrderItem orderItem : or.getOrderItems()) {
            dBBroker.delete(orderItem);
        }
    }
    
}
