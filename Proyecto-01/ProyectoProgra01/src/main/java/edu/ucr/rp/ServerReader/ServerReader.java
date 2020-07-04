/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.ServerReader;

import edu.ucr.rp.Interfaces.Logic.Registers;
import edu.ucr.rp.Interfaces.Logic.manteinFile;
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
public class ServerReader {
     ServerSocket serverSocket;
     manteinFile mantein = new manteinFile();
    public ServerReader(int port) {
        try {
            serverSocket = new ServerSocket(port);
         while(true){
            System.out.println("Esperando Conexión");
            Socket socket = serverSocket.accept();//esperando a que llegue una conexión
            JOptionPane.showMessageDialog(null,"Conexión recibida");
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            String data = (String) in.readObject();
           ArrayList datas = mantein.getInformationData(data);
           ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
           
             switch(datas.get(0).toString()){
                 
                 case"listC":
                  ArrayList arrayListCatalogs = mantein.getRegistersFileCatalog();
                  out.writeObject(arrayListCatalogs.toString());
                  break;
                   case"searchR":
                ArrayList registersAll = mantein.getRegistersFileRegister();
                ArrayList registerData = mantein.getInformation(datas.get(1).toString());
                Registers registerSearch = mantein.GetposRegister(registersAll,registerData.get(0).toString(),registerData.get(1).toString());
                        
               if (registerSearch!=null) {
                 out.writeObject(registerSearch.toString());
              }else{
                out.writeObject("Registro no encontrado");
               }
                     
                 break;
                 case"listR":
                  ArrayList arrayListRegisters = mantein.getRegistersFileRegister();
                  String Output ="";
                     for (int i = 0; i < arrayListRegisters.size(); i++) {
                      Output+=arrayListRegisters.get(i).toString()+"\n";
                     }
                  out.writeObject(Output);
                  break;
             }
           
          
           
         }   
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();//Puede lanzar una excepción puesto que el puerto puede estar ocupado
        }

    }
     
}


