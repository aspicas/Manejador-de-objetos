/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorreplica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Registro;
import static model.Registro.localPath;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.JDOMParseException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author david
 */
public class ServidorReplica extends UnicastRemoteObject {

    public static String _ip = "192.168.43.174";
    public static Servidor server;
    public static Thread t;
    
    public ServidorReplica() throws RemoteException {
    }    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
           
        }catch(Exception ex){
            Logger.getLogger(ServidorReplica.class.getName()).log(Level.SEVERE, "Error main", ex);
        }
    }
  
}
