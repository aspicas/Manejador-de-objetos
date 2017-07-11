/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Mob;
import model.Registro;
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
public class XMLManager {
    public static boolean addMobXml(Mob mob){
        Document doc;
        Element root,child, newChild;
        List <Element> rootChildrens;        
        SAXBuilder builder = new SAXBuilder();
        try
        {   
            doc = builder.build(Registro.localPath);
            root = doc.getRootElement();
            rootChildrens = root.getChildren();
            // Creamos una nueva etiqueta
            newChild = new Element("mob");            
            newChild.setAttribute("accion", mob.getAccion());       
            newChild.setAttribute("fechaCreacion", mob.getFechaCreacion().toString());
            newChild.setAttribute("id", String.valueOf(mob.getId()));
            newChild.setAttribute("nombre", mob.getNombre());
            root.addContent(newChild);
            try
             {
                Format format = Format.getPrettyFormat();
                XMLOutputter out = new XMLOutputter(format);
                FileOutputStream file = new FileOutputStream(Registro.localPath);
                out.output(doc,file);
                file.flush();
                file.close();
                return true;
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
        }
        catch(JDOMParseException e)
        {
            System.out.println();
            e.printStackTrace();
        }
        catch(JDOMException e)
        {
            System.out.println();
            e.printStackTrace();
        }
        catch(IOException e)
        {
            System.out.println();
            e.printStackTrace();
        }
        return false;
    }
    
    public static Mob getMob(int id){
        try {
            Document doc;
            Element root;
            List <Element> rootChildrens;
            SAXBuilder builder = new SAXBuilder();
            Mob mob;
            doc = builder.build(Registro.localPath);
            root = doc.getRootElement();
            rootChildrens = root.getChildren();            
            for (Element child : rootChildrens) {                
                if (child.getAttributeValue("id").equals(String.valueOf(id))){
                    Date d = new Date();
                    mob = new Mob(id, d, child.getAttributeValue("nombre"), child.getAttributeValue("accion"));
                    return mob;
                }
            }            
        } catch (JDOMException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
