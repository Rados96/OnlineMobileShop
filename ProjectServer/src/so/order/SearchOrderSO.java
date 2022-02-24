/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.order;

import domain.GeneralDomainObject;
import domain.Order;
import domain.OrderItem;
import java.util.ArrayList;
import so.AbstractGenericOperation;

/**
 *
 * @author Rados
 */
public class SearchOrderSO extends AbstractGenericOperation{

    public ArrayList<Order> list;
    private String criterion;

    public SearchOrderSO(String criterion) {
        list = new ArrayList<>();
        this.criterion = criterion;
    }

    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if(!(gdo instanceof Order)){
            throw new Exception("Neispravan parametar za narudžbu.");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        ArrayList<GeneralDomainObject> gdoList = dBBroker.search(new Order(), criterion);
        for (GeneralDomainObject generalDomainObject : gdoList) {
            Order o = (Order) generalDomainObject;
            String crit = "o.orderID = " + o.getOrderID();
            ArrayList<GeneralDomainObject> gdoOrderItemList = dBBroker.search(new OrderItem(), crit);
            for (GeneralDomainObject gdoOrderItem : gdoOrderItemList) {
                OrderItem oi = (OrderItem) gdoOrderItem;
                o.getOrderItems().add(oi);
            }
            list.add(o);
        }
    }   

    public ArrayList<Order> getList() {
        System.out.println("Returned order list.");
        return list;
    }
    
    
}
