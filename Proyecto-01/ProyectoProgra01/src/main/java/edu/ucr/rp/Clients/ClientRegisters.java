/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Clients;

import edu.ucr.rp.Interfaces.Client;
import edu.ucr.rp.Interfaces.Logic.manteinFile;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author 
 * Marian Murillo Bonilla
 * Angie Mora Núñez
 */
public class ClientRegisters {
   
     Socket clientSocket;

    public ClientRegisters(String server , int port,String registers) {
        try {
            clientSocket = new Socket(server, port);//
            Thread.sleep(5000);
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
           
            out.writeObject(registers);
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            JOptionPane.showMessageDialog(null,in.readObject());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    } 
    
    
    
    
    
    
    
    
    
    
    
    
}//Runable

