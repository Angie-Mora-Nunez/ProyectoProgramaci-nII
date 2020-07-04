


import edu.ucr.rp.Interfaces.Logic.Registers;
import edu.ucr.rp.Interfaces.Logic.manteinFile;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.stage.Window;
import javax.swing.JOptionPane;


/**
 *
 * @author 
 * Marian Murillo Bonilla
 * Angie Mora Núñez
 */



public class Server {
    ServerSocket serverSocket;
     manteinFile mantein = new manteinFile();
    public Server(int port) {
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
                 case"createC":
                     File fileCatalogueshow = new File("catalogo.txt");
                     mantein.addOnFile(fileCatalogueshow,datas.get(1).toString());
                     out.writeObject("Guardado");
                 break;
                 case"listC":
                  ArrayList arrayListCatalogs = mantein.getRegistersFileCatalog();
                  out.writeObject(arrayListCatalogs.toString());
                  break;
                  
                 case"createR":
                     File fileRegister = new File("FileRegister.txt");
                     mantein.addOnFile(fileRegister,datas.get(1).toString());
                     out.writeObject("Registro Guardado");  
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
                 
                 case"modifyR":
                     File fi = new File("FileRegister.txt");
                     mantein.addOnFile(fi,datas.get(1).toString());
                     out.writeObject("Modificado");
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
