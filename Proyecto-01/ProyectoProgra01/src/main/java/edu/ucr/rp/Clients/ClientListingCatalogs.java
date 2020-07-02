/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Clients;

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
public class ClientListingCatalogs {
  Socket clientSocket;
     private String catalog;
     private String catalogue;
     private String concate="";
     public ClientListingCatalogs(String server , int port) {
        try {
            clientSocket = new Socket(server, port);//
            Thread.sleep(5000);
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            catalog=(String) in.readObject();
            JOptionPane.showMessageDialog(null, catalog);
            manteinFile mantein = new manteinFile();
            ArrayList cataloglist =mantein.getRegis(catalog);
            System.out.println(cataloglist);
           JOptionPane.showMessageDialog(null, cataloglist); 
            cataloglist=mantein.getRegistersFileCatalog();
           
            
            for (int i = 0; i < cataloglist.size(); i++) {
                catalogue=cataloglist.get(i).toString();
                concate+=catalogue+"\n";
                System.out.println(concate);  
//                JOptionPane.showMessageDialog(null, concate);
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
      
}//ClientListingCatalogs
