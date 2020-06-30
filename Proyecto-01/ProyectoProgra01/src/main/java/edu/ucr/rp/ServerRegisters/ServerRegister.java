/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.ServerRegisters;

import edu.ucr.rp.Interfaces.Logic.manteinFile;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 * Marian Murillo Bonilla
 * Angie Mora Núñez
 */
public class ServerRegister {
    ServerSocket serverSocket;

    public ServerRegister(int port) {
        try {
            serverSocket = new ServerSocket(port);
         
            while(true){
            System.out.println("Esperando Conexión");
                Socket socket = serverSocket.accept();//esperando a que llegue una conexión
               
                
            JOptionPane.showMessageDialog(null,"Conexión recibida");
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                String catalog = (String) in.readObject();
                JOptionPane.showMessageDialog(null,"Mensaje recibido");
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

           manteinFile s = new manteinFile();
           File Fregister = new File("FileRegister.txt");
           s.addOnFile(Fregister, catalog);
           out.writeObject("Registro Guardado");
                
            }//while
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();//Puede lanzar una excepción puesto que el puerto puede estar ocupado
        }

    }
    
    
    
    
    
    
}//ServerRegisters

