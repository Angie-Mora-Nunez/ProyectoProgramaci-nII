


import edu.ucr.rp.Interfaces.Logic.manteinFile;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.stage.Window;
import javax.swing.JOptionPane;

public class Server {
    ServerSocket serverSocket;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
         
            System.out.println("Esperando Conexión");
                Socket socket = serverSocket.accept();//esperando a que llegue una conexión
               
                
            JOptionPane.showMessageDialog(null,"Conexión recibida");
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                String catalog = (String) in.readObject();
                JOptionPane.showMessageDialog(null,"Mensaje recibido");
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

           manteinFile s = new manteinFile();
           File fileCatalogueshow = new File("catalogo.txt");
           s.addOnFile(fileCatalogueshow, catalog);
           out.writeObject("Guardado");
                
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();//Puede lanzar una excepción puesto que el puerto puede estar ocupado
        }

    }
     
}
