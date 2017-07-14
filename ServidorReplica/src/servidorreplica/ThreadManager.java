/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidorreplica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Mob;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.JDOMParseException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Manuel
 */
public class ThreadManager extends Thread{
    
    private Socket socket;
    private Mob mob = new Mob();
    public String sf= new String();
    Servidor serv;
    
    public ThreadManager(Socket socket,Servidor serv) {
        this.socket = socket;
        this.serv=serv;
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
                Random random = new Random();             
                int aux = random.nextInt(2);                             
                    // AQUIII Preguntar SI EL USUARIO QUIERE ACEPTAR EL REPLICAR
                if (aux == 0){                    
                    this.Responder(socket,"VOTE_COMMIT");                    
                }else{
                    this.Responder(socket, "VOTE_ABORT");
                }
            }else if (comando[0].equals("GLOBAL_REPLICA")){
                //System.out.println(comando[1]);
                replicarXML(comando[1]);
                System.out.println("despues");
                
                this.Responder(socket,"DALE");
            }else if (comando[0].equals("VOTE_RESTA")){
            
                if(comando[1].equals("1")){
                    RestauracionXML("Cliente.xml");
                    this.Responder(socket,"OK");
                }else if (comando[1].equals("2")){
                    
                    RestauracionXML("ServidorReplica.xml");
                    this.Responder(socket,"OK");
                }
                
            
            }      
        }         
        catch(Exception e)
        {
        }
    }
    
   private void replicarXML(String data) throws ParseException{
       
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
              mob.setAccion(hijo[0]);
              DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                    Date d = format.parse(hijo[1]);      
              mob.setFechaCreacion(d);
              mob.setNombre(hijo[2]);
              mob.setId(Integer.parseInt(hijo[3]));
                XMLManager.saveDataXML(mob);
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
                FileOutputStream file = new FileOutputStream("src/Distribuidos/"+DocumentoXML);
                out.output(doc,file);
                file.flush();
                file.close();
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
