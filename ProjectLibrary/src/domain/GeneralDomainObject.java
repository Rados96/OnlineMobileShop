/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Rados
 */
public interface GeneralDomainObject extends Serializable{
    public String getTableName();
    public String getAttributeNamesForInsert();
    public String getAttributeValuesForInsert();
    public String getPrimaryKeyClause();
    public String getUpdateValues();
    public String getJoinClause();
    public String getOrderByClause();
    public ArrayList<GeneralDomainObject> getListFromResultSet(ResultSet rs);
    default String getCodeClause() { return "";}
    default String select() { return "*";}
    default String getRequirementForMore() { return ""; };
    //public String getSearchCriterium(Object criterium);
    //public String getUpdateCriterium(Object criterium);
    //public String getIDWithCondition();

    public GeneralDomainObject load(ResultSet rs) throws Exception;



}
