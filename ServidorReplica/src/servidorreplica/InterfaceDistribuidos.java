/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorreplica;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Rafael
 */
public interface InterfaceDistribuidos extends Remote {
    
    String replicar(String accion) throws RemoteException;
    String restaurar(String DocumentoXML) throws RemoteException;
    
}
