/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Rados
 */
public class ClientRequest implements Serializable{
    private int operation;
    private Object parameter;

    public ClientRequest() {
    }

    public ClientRequest(int operation, Object parameter) {
        this.operation = operation;
        this.parameter = parameter;
    }

    /**
     * @return the operation
     */
    public int getOperation() {
        return operation;
    }

    /**
     * @param operation the operation to set
     */
    public void setOperation(int operation) {
        this.operation = operation;
    }

    /**
     * @return the parameter
     */
    public Object getParameter() {
        return parameter;
    }

    /**
     * @param parameter the parameter to set
     */
    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    
}
