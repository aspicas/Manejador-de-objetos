/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restfull;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
 * @author Manuel
 */

public class XMLManager {
    
    private static String pathXML = Registro.localPath;
    
    
     public static void desdecero(){
        try {
            
            Document doc= new Document();
            doc.setRootElement(new Element("Cliente"));
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            //output xml to console for debugging
            //xmlOutputter.output(doc, System.out);
            xmlOutputter.output(doc, new FileOutputStream(pathXML));
           
        } catch (IOException ex) {
            
        }
    }
     
     public static boolean saveDataXML(Mob mob) {
         Document        doc;
        Element         root,child, newChild;
        List <Element>  rootChildrens;               
        int pos=0;
        SAXBuilder      builder = new SAXBuilder();

        try
        {
            
            doc = builder.build(pathXML);
            root = doc.getRootElement();
            rootChildrens = root.getChildren();
            // Creamos una nueva etiqueta
            newChild = new Element("mob");
            newChild.setAttribute("accion",mob.getAccion());
            newChild.setAttribute("fechaCreacion",mob.getFechaCreacion().toString());
            newChild.setAttribute("nombre",mob.getNombre());
            newChild.setAttribute("id", String.valueOf(mob.getId()));
               
               root.addContent(newChild);

               try
                {
                    Format format = Format.getPrettyFormat();
                    XMLOutputter out = new XMLOutputter(format);
                    FileOutputStream file = new FileOutputStream(pathXML);
                    out.output(doc,file);
                    file.flush();
                    file.close();

                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
               try
                {
                    Format format = Format.getPrettyFormat();
                    XMLOutputter out = new XMLOutputter(format);
                    FileOutputStream file = new FileOutputStream(pathXML);
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
        return true;
            
    }
      

    
}
