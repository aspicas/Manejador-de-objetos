/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidorreplica;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Manuel
 * 
 */
public class Servidor extends Thread{
    public ServerSocket serv ;
    public Integer puerto;
    
    public Servidor(int puerto)
    {
        this.puerto=puerto; 
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
                
                ThreadManager hilo=new ThreadManager(cliente,this);
                hilo.start();
            }
        } 
        catch (IOException ex) {System.out.println("Error en Servidor Central "+ex.getMessage());}
    }
    
}
