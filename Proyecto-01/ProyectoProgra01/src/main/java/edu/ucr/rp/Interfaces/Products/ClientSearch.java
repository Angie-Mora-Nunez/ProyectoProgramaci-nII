/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces.Products;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Equipo
 */
public class ClientSearch {
    Socket clientSocket;
    private String register;

    public ClientSearch(String server , int port,String data) {
        try {
            clientSocket = new Socket(server, port);//
            Thread.sleep(5000);
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.writeObject(data);
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
             register=(String) in.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ClientSearch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientSearch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public String getRegister() {
        return register;
    }
            
        
}



