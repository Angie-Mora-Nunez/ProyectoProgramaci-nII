/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Clients;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 * Marian Murillo Bonilla
 * Angie Mora Núñez
 */
public class Client {
    Socket clientSocket;

    public Client(String server , int port,String catalog) {
        try {
            clientSocket = new Socket(server, port);//
            Thread.sleep(5000);
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
           
            out.writeObject(catalog);
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
    
   
}












