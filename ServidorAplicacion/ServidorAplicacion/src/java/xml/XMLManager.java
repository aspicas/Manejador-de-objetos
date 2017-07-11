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
 * @author david
 */
public class XMLManager {
    public static boolean saveMobXml(Mob mob){
        Document doc;
        Element root,child, newChild;
        List <Element> rootChildrens;               
        int pos = 0;
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
}
