/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.ServerModificateRegisters;

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
 * @authores 
 * Marian Murillo Bonilla
 * Angie Mora Núñez
 * 
 */
public class ServerModificateRegisters {
   ServerSocket serverSocket;

    public ServerModificateRegisters(int port) {
        try {
            serverSocket = new ServerSocket(port);
         while(true){
            System.out.println("Esperando Conexión");
                Socket socket = serverSocket.accept();//esperando a que llegue una conexión
               
                
            JOptionPane.showMessageDialog(null,"Conexión recibida");
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                String registers = (String) in.readObject();
                JOptionPane.showMessageDialog(null,"Registro recibido");
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

           manteinFile s = new manteinFile();
           File fi = new File("FileRegister.txt");
           s.addOnFile(fi, registers);
           out.writeObject("Modificado");
         }//while   
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();//Puede lanzar una excepción puesto que el puerto puede estar ocupado
        }//try&catch

    }//ModificateRegisters4 
}//ServerModificateRegisters
