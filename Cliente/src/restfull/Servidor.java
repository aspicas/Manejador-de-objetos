/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restfull;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;

/**
 *
 * @author Carlos
 */
public class Servidor extends Thread{
    public ServerSocket serv ;
    public Integer puerto;
    private JFrame viewCliente;
    
    public Servidor(int puerto, JFrame viewCliente)
    {
        this.puerto=puerto; 
        this.viewCliente=viewCliente; 
        
    }
    
    
    public  void run()
    {
       try 
        {
            
            serv = new ServerSocket(this.puerto);
            Socket cliente;
            
       
            while (true)
            {
                cliente = serv.accept();
                
                ThreadManager hilo=new ThreadManager(cliente,this,viewCliente);
                hilo.start();
            }
        } 
        catch (IOException ex) {System.out.println("Error en Servidor "+ex.getMessage());}
    }
    
}
