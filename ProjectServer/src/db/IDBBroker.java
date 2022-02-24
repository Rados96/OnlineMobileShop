/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domain.GeneralDomainObject;
import java.util.ArrayList;

/**
 *
 * @author rados
 */
public interface IDBBroker {
    public GeneralDomainObject find(GeneralDomainObject gdo) throws Exception;
    public ArrayList<GeneralDomainObject> getall(GeneralDomainObject gdo) throws  Exception;
    public int save(GeneralDomainObject gdo) throws  Exception;
    public void update(GeneralDomainObject gdo) throws  Exception;
    public ArrayList<GeneralDomainObject> search(GeneralDomainObject gdo,String criterium) throws  Exception;
    public void delete(GeneralDomainObject gdo) throws  Exception;
}
