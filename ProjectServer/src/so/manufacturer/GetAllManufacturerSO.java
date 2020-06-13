/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.manufacturer;

import domain.GeneralDomainObject;
import domain.Manufacturer;
import java.util.ArrayList;
import so.AbstractGenericOperation;

/**
 *
 * @author Rados
 */
public class GetAllManufacturerSO extends AbstractGenericOperation{
    private ArrayList<Manufacturer> list;

    public GetAllManufacturerSO() {
        list = new ArrayList<>();
    }

    @Override
    protected void validate(GeneralDomainObject gdo) throws Exception {
        if(!(gdo instanceof Manufacturer)){
            throw new Exception("Neispravan parametar za proizvodjaca!");
        }
    }

    @Override
    protected void execute(GeneralDomainObject gdo) throws Exception {
        ArrayList<GeneralDomainObject> gdoList = dBBroker.getall((Manufacturer)gdo);
        for (GeneralDomainObject generalDomainObject : gdoList) {
            Manufacturer m = (Manufacturer) generalDomainObject;
            list.add(m);
        }
    }

    public ArrayList<Manufacturer> getList() {
        System.out.println("Vracena lista proizvodjaca");
        return list;
    }
}
