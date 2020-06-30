/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.ServerSearch;

import edu.ucr.rp.Interfaces.Logic.Registers;
import edu.ucr.rp.Interfaces.Logic.manteinFile;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Equipo
 */
public class ServerSearch {
    ServerSocket serverSocket;

    public ServerSearch(int port) {
        try {
            serverSocket = new ServerSocket(port);
         while(true){
            System.out.println("Esperando Conexión");
                Socket socket = serverSocket.accept();//esperando a que llegue una conexión
               
                
                JOptionPane.showMessageDialog(null,"Conexión recibida");
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                String Register = (String) in.readObject();
                JOptionPane.showMessageDialog(null,"Mensaje recibido");
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                manteinFile mantein = new manteinFile();
                ArrayList registersAll = mantein.getRegistersFileRegister();
                ArrayList registerData = mantein.getInformation(Register);
                Registers registerSearch = mantein.GetposRegister(registersAll,registerData.get(0).toString(),registerData.get(1).toString());
                        
              if (registerSearch==null) {
                 out.writeObject("Registro no encontrado");
             }
              out.writeObject(registerSearch.toString());
         }   
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();//Puede lanzar una excepción puesto que el puerto puede estar ocupado
        }

    }
}




