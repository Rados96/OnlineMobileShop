/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.city;

import domain.City;
import domain.GeneralDomainObject;
import java.util.ArrayList;
import so.AbstractGenericOperation;

/**
 *
 * @author Rados
 */
public class GetAllCitiesSO extends AbstractGenericOperation{
    
    private ArrayList<City> list;

    public GetAllCitiesSO() {
        list = new ArrayList<>();
    }
    
    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
         if(!(gdo instanceof City)){
            throw new Exception("Neispravan parametar za grad!");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        ArrayList<GeneralDomainObject> gdoList = dBBroker.getall((City)gdo);
        for (GeneralDomainObject generalDomainObject : gdoList) {
            City c = (City) generalDomainObject;
            list.add(c);
        }
    }

    public ArrayList<City> getList() {
        System.out.println("Vracena lista gradova");
        return list;
    }
    
}
