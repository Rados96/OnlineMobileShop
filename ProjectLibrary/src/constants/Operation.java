/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constants;

import java.io.Serializable;

/**
 *
 * @author Rados
 */
public class Operation implements Serializable {

    public static final int LOGIN = 1;
    public static final int LOGOUT = 2;

    public static final int GET_ALL_MANUFACTURERS = 3;
    public static final int GET_ALL_TYPES = 4;
    
    public static final int GET_ALL_PRODUCTS = 5;
    public static final int ADD_PRODUCT = 6;
    public static final int SEARCH_PRODUCTS = 7;
    public static final int UPDATE_PRODUCT = 8;
    public static final int DELETE_PRODUCT = 9;

    public static final int GET_ALL_CITIES = 10;
    public static final int GET_ALL_CLIENTS = 11;
    public static final int ADD_CLIENT = 12;
    public static final int SEARCH_CLIENTS = 13;
    public static final int UPDATE_CLIENT = 14;
    
    public static final int GET_ALL_ORDERS = 15;
    public static final int ADD_ORDER = 16;
    public static final int SEARCH_ORDERS = 17;
    public static final int DELETE_ORDER = 18;

}
