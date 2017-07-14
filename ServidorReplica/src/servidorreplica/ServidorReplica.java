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
import javax.lang.model.element.Element;
import javax.swing.text.Document;
import model.Registro;
import static model.Registro.localPath;

/**
 *
 * @author david
 */
public class ServidorReplica extends UnicastRemoteObject implements ServidorReplicaInterface{

    public ServidorReplica() throws RemoteException {
    }    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{            
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("rmi://"+Registro.servidorReplicas+"/ServidorReplicaInterface", new ServidorReplica());
        }catch(Exception ex){
            Logger.getLogger(ServidorReplica.class.getName()).log(Level.SEVERE, "Error main", ex);
        }
    }
    
    @Override
    public String replicar(String action) throws RemoteException {
        
       String response1=Enviar(Registro.coordinadorR,1122,action+"-1");        
        String response2=Enviar(Registro.cliente,1133,action+"-2");
        
        String respuesta = "";
        
        if (response1.equals("VOTE_COMMIT")&& response2.equals("VOTE_COMMIT")){
            
            
            String xmlstr = enviarXml();            
            //System.out.println("algo ahi aparte xml "+xmlstr);
            
             this.Enviar(Registro.coordinadorR,1122,"GLOBAL_REPLICA-"+xmlstr);
             
             this.Enviar(Registro.cliente,1133,"GLOBAL_REPLICA-"+xmlstr);
             respuesta = "COMMIT";
        } else if (response1.equals("VOTE_ABORT")&& response2.equals("VOTE_ABORT") ){
            
             respuesta = "ABORT";
        } else {
            
             respuesta = "DUAL";
        }
        System.out.println(respuesta);
        return respuesta;
        
    }

    @Override
    public String restaurar(String Tipo) throws RemoteException {
        
        if (Tipo.equals("1")){
            this.Enviar(Registro.coordinadorR,1122,"VOTE_RESTA-1");
        }else{
            this.Enviar(Registro.cliente,1133,"VOTE_RESTA-2");
        }
        return "OK";
    }

    
    private static String ct = "";
    public static String enviarXml(){
        try {
            String retorno="";
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(localPath);
            Element rootNode = document.getRootElement();
           if ("ServidorCentral".equals(rootNode.getName())) {
                List<Element> le= rootNode.getChildren("event");
                for(int i=0;i<le.size();i++){
                    
                    Attribute codigo = le.get(i).getAttribute("Codigo");
                    Attribute accion = le.get(i).getAttribute("Accion");
                    Attribute ingrediente = le.get(i).getAttribute("Ingrediente");
                    Attribute cantidad = le.get(i).getAttribute("Cantidad");
                    Attribute hora = le.get(i).getAttribute("Hora");
                    ct = codigo.getValue()+"#"+accion.getValue()+"#"+ingrediente.getValue()+"#"+cantidad.getValue()+"#"+hora.getValue()+"%"+ct;
                    
                }
                System.out.println(ct);
                return ct;
        }
        } catch (JDOMException ex) {
            //Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
           // Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String Enviar(String ip,int puerto,String mensaje){
        try{
            Socket nuevo = new Socket(ip,puerto);
            DataOutputStream salida = new DataOutputStream(nuevo.getOutputStream());
            salida.writeUTF(mensaje);
            String respuesta;
            DataInputStream entrada = new DataInputStream(nuevo.getInputStream());
            respuesta = entrada.readUTF();
            nuevo.close();
            return respuesta;

        }
        catch(Exception e){ }
        return null;
        
    }
}
