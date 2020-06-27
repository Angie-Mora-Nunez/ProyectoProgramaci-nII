/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Servers;

import java.net.*;
import java.io.*;




/**
 *
 * @author 
 * Marian Murillo Bonilla
 * Angie Mora Núñez
 */

    
   public class serverSaveCatalog {
       ServerSocket server;
       Socket socket;
       int puerto=9000;
       DataOutputStream salida;
       BufferedReader entrada;
       public void iniciar(){
       try{
            server = new ServerSocket(puerto);
            socket = new Socket();
            socket=server.accept();
            entrada=new BufferedReader(new InputStreamReader(socket.getInputStream()));//se obtiene canal de entrada
            String mes =entrada.readLine();
            System.out.println("mes");
            salida=new DataOutputStream(socket.getOutputStream());//envia la informacion el canl de salida
            salida.writeBytes("se ha terminado la conexion");
            socket.close();//cerramos la conexión
            
        
           
           
           
       }catch(Exception e){}
       
       
       }//inicia
       
       
    
}//Supermarket


    

