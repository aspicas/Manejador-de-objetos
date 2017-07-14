/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restfull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import javax.swing.JFrame;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.JDOMParseException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author David
 */
public class ThreadManager extends Thread{
    
    private Socket socket;
    public String sf= new String();
    private JFrame view;
    Servidor serv;
    
    public ThreadManager(Socket socket,Servidor serv, JFrame view) {
        this.socket = socket;
        this.serv=serv;
        this.view = view;
    }
    public void Responder(Socket socket,String mensaje){
        try{
        DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
        salida.writeUTF(mensaje);
        }
        catch(Exception e){    
        }
    }
    
    public void run()
    { 
        try 
        {
            System.out.println(socket.getInetAddress().getHostAddress()+" Conexion recibida");
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            String mensaje = entrada.readUTF();
            String[] comando = mensaje.split("-");
            
            if (comando[0].equals("VOTE_REQUEST")){
                
                
                // AQUIII Preguntar SI EL USUARIO QUIERE ACEPTAR EL REPLICAR
                   this.Responder(socket,"VOTE_COMMIT");
                   //this.Responder(socket, "VOTE_ABORT");
                   
            }else if (comando[0].equals("GLOBAL_REPLICA")){
                //System.out.println(comando[1]);
                replicarXML(comando[1]);
                System.out.println("despues");
                
                this.Responder(socket,"DALE");
            }else if (comando[0].equals("VOTE_RESTA")){
            
                if(comando[1].equals("1")){
                    RestauracionXML("Fumador.xml");
                    this.Responder(socket,"OK");
                }else if (comando[1].equals("2")){
                    
                    RestauracionXML("Vendedor.xml");
                    this.Responder(socket,"OK");
                }
                
            
            }     
        }         
        catch(Exception e)
        {
        }
    }
    
    private void replicarXML(String data){
        int ingrediente = 0;
        XMLManager.desdecero();
        // & $
        String[] hijos=data.split("%");
        //System.out.println(data);
        //System.out.println(hijos.length);
        for (int i=0;i<hijos.length;i++)
        {            
            String[] hijo = hijos[i].split("#");
            System.out.println(i+": "+hijo.length);
                if (hijo[2] != null) {

                switch (hijo[2]) {
                    case "Tabaco":
                        ingrediente = 1;
                        break;
                    case "Papel":
                        ingrediente = 2;
                        break;
                    default: 
                        ingrediente = 3;
                        break;
                }

                }
                XMLManager.saveDataXML(hijo[0],ingrediente,hijo[1]);
            }
            
//ManejadorXmlCliente.saveFumadorDataBase(hijo[0],Integer.parseInt(hijo[2]),hijo[1]);
        }
    public static boolean RestauracionXML (String DocumentoXML){
         
        Document        doc;
         Element         root,child,childDelete;
         SAXBuilder  builder = new SAXBuilder();
         String         nickname, addFriend, rivals;
         List <Element>  rootChildrens;
         int             pos = 0;
         boolean op =false;
           try
        {
            doc = builder.build("src/Distribuidos/"+DocumentoXML);
            root = doc.getRootElement();            
            rootChildrens = root.getChildren();
            rootChildrens.removeAll(rootChildrens);            
            try
            {
                Format format = Format.getPrettyFormat();
                XMLOutputter out = new XMLOutputter(format);                
                try (FileOutputStream file = new FileOutputStream("src/Distribuidos/"+DocumentoXML)) {
                    out.output(doc,file);
                    file.flush();
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        catch(JDOMParseException e)
        {
           // System.out.println(Util.ERROR_XML_EMPTY_FILE);
            e.printStackTrace();
        }
        catch(JDOMException e)
        {
            //System.out.println(Util.ERROR_XML_PROCESSING_FILE);
            e.printStackTrace();
        }
        catch(IOException e)
        {
           // System.out.println(Util.ERROR_XML_PROCESSING_FILE);
            e.printStackTrace();
        }
           return true;
     }
    
    
}
