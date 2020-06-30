/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.serverListingCatalog;

import edu.ucr.rp.Interfaces.Logic.Registers;
import edu.ucr.rp.Interfaces.Logic.manteinFile;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.crypto.Mac;
import javax.swing.JOptionPane;

/**
 *
 * @authores 
 * Marian Murillo Bonilla
 * Angie Mora Núñez
 * 
 */
public class ServerListingCatalogs {
    
    ServerSocket serverSocket;
     
    public ServerListingCatalogs(int port) throws IOException {
      
            serverSocket = new ServerSocket(port);
         while(true){
            System.out.println("Esperando Conexión");
//            JOptionPane.showMessageDialog(null, "Esperando conexión");
//                
                Socket socket = serverSocket.accept();//esperando a que llegue una conexión
                JOptionPane.showMessageDialog(null,"Conexión recibida");
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                JOptionPane.showMessageDialog(null,"Catalogos encontrados");
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                manteinFile mainfile = new manteinFile();
                ArrayList arrayListCatalogs = mainfile.getRegistersFileCatalog();
                out.writeObject(arrayListCatalogs.toString());
                
                
                
                
                
                
         }//whileTrue
            
     

    }//server
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}//ServerListingcatalogs
