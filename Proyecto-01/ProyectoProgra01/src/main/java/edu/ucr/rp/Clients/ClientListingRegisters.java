/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Clients;

import edu.ucr.rp.Interfaces.Logic.Registers;
import edu.ucr.rp.Interfaces.Logic.manteinFile;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @authores 
 * Marian Murillo Bonilla
 * Angie Mora Núñez
 * 
 */
public class ClientListingRegisters {
     Socket clientSocket;
     private String ar;
     private String cat;
     private String concate="";
     public ClientListingRegisters(String server , int port) {
        try {
            clientSocket = new Socket(server, port);//
            Thread.sleep(5000);
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ar=(String) in.readObject();
            manteinFile mantein = new manteinFile();
            ArrayList lolo =mantein.getRegis(ar);
//            System.out.println(lolo);
            
            lolo=mantein.getRegistersFileRegister();
           
            
            for (int i = 0; i < lolo.size(); i++) {
                cat=lolo.get(i).toString();
                concate+=cat+"\n";
                System.out.println(cat);  
            }//for
            
           
           
           
            
            

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }//try&catch
        
        
        
        
    }//CleintListingRegisters
     
      public String getRegister() {
        return concate;
    }//retorna el metodo
            
     
     
     
}//ClientListingRegisters
