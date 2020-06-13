/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.type;

import domain.GeneralDomainObject;
import domain.Type;
import java.util.ArrayList;
import so.AbstractGenericOperation;

/**
 *
 * @author Rados
 */
public class GetAllTypesSO extends AbstractGenericOperation{
    
    private ArrayList<Type> list;

    public GetAllTypesSO() {
        list = new ArrayList<>();
    }
    
    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if(!(gdo instanceof Type)){
            throw new Exception("Neispravan parametar za vrstu");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        ArrayList<GeneralDomainObject> gdoList = dBBroker.getall((Type)gdo);
        for (GeneralDomainObject generalDomainObject : gdoList) {
            Type t = (Type) generalDomainObject;
            list.add(t);
        }
    }

    public ArrayList<Type> getList() {
        System.out.println("Vracena lista vrsta");
        return list;
    }
    
    
}
