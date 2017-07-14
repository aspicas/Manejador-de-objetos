/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Distribuidos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.JDOMParseException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import java.util.Date;

/**
 *
 * @author David
 */
public class XMLManager {    
    //private static String _path = "C:\\Users\\Rafael\\Documents\\NetBeansProjects\\WebService\\src\\java\\Distribuidos\\ServidorCentral.xml";
    public static boolean saveFumadorDataBase( String CodFumador, int ingrediente, String accion){
        Document        doc;
        Element         root,child, newChild;
        List <Element>  rootChildrens;               
        int pos=0;
        SAXBuilder      builder = new SAXBuilder();

        try
        {
            
            doc = builder.build(Manager.localPath);
            root = doc.getRootElement();
            rootChildrens = root.getChildren();
            String i = "";
            switch (ingrediente) {
                case 1:
                    i = "Tabaco";
                    break;
                case 2:
                    i = "Papel";
                    break;
                case 0:
                    i = "Fosforo";
                    break;
            }
            // Creamos una nueva etiqueta
            newChild = new Element("event");
            // Añadimos un atributo
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date now = Calendar.getInstance().getTime();
            String g = df.format(now);
            newChild.setAttribute("Codigo",CodFumador);
            newChild.setAttribute("Accion",accion );
            newChild.setAttribute("Ingrediente", i );
            newChild.setAttribute("Cantidad", Integer.toString(1));
            newChild.setAttribute("Hora", g);
               
               root.addContent(newChild);

               try
                {
                    Format format = Format.getPrettyFormat();
                    XMLOutputter out = new XMLOutputter(format);
                    FileOutputStream file = new FileOutputStream(Manager.localPath);
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
                    FileOutputStream file = new FileOutputStream(Manager.localPath);
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