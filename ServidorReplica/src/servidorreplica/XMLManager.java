/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidorreplica;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    private static String localPath = "src\\Distribuidos\\Vendedor.xml";
      public static void desdecero(){
        try {
            
            Document doc= new Document();
            doc.setRootElement(new Element("VendedorReplica"));
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            //output xml to console for debugging
            //xmlOutputter.output(doc, System.out);
            xmlOutputter.output(doc, new FileOutputStream(localPath));
           
        } catch (IOException ex) {
            
        }
    }
     
     public static boolean saveDataXML(String CodFumador, int ingrediente, String accion) {
         Document        doc;
        Element         root,child, newChild;
        List <Element>  rootChildrens;               
        int pos=0;
        SAXBuilder      builder = new SAXBuilder();

        try
        {
            
            doc = builder.build(localPath);
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
                case 3:
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
                    FileOutputStream file = new FileOutputStream(localPath);
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
                    FileOutputStream file = new FileOutputStream(localPath);
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
