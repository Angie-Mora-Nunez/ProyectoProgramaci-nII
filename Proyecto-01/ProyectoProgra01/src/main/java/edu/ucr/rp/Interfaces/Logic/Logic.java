/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces.Logic;

import edu.ucr.rp.Interfaces.InterfaceCreate;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 * Marian Murillo Bonilla
 * Angie Mora Núñez
 */
public class Logic {
    public  File insert= new File("catalogue.txt");
    
    
      public void AgregarPuestos(String nameCatalogue, String properties, boolean editable) throws FileNotFoundException{
   PrintStream ps = getPrintStream("catalogue.txt",editable);
   ps.println(nameCatalogue+"|"+properties+"*");
   }//agregar
   
   
   public Catalogue[] leeArchivos(){
    Catalogue arrayCatalogue[]=new Catalogue[countlinesArchive("catalogue.txt")];
        int indice =0;
         BufferedReader br = getBufferReader("catalogue.txt");
        try {
            String puestosActuales = br.readLine();
       
            while(puestosActuales != null){
                
            StringTokenizer st = new StringTokenizer(puestosActuales,"|*");
            String puestosNombres ="",nameCatalogue="",propiertes="";
            int controlaTokens = 1;
            
            while(st.hasMoreTokens()){
                if (controlaTokens==1)
                puestosNombres=st.nextToken();
                else if (controlaTokens==2)
                nameCatalogue=st.nextToken();
                else if (controlaTokens==3)
                propiertes=st.nextToken();
                
               controlaTokens++; 
            }//while
     
            Catalogue c = new Catalogue(nameCatalogue, propiertes);
            arrayCatalogue[indice]=c;
            indice++;
            puestosActuales=br.readLine();
        
        }//while
       
        }//try 
        catch (IOException ex) {
            Logger.getLogger(InterfaceCreate.class.getName()).log(Level.SEVERE, null, ex);
        }//catch
       
    return arrayCatalogue;
    }//job
   
    public boolean searchName(String nameCatalogue){
        Catalogue c [] = leeArchivos();
        for(int i=0;i<c.length;i++){
        if(c[i].getNameCatalogue().equalsIgnoreCase(nameCatalogue));
        return false;
        }//End for
        return false;
    }//End searchName
    
    
    //Retorna un printstream 
    public PrintStream getPrintStream(String nombreArchivo, boolean editable) {
     
        File archivo = new File(nombreArchivo);
        PrintStream ps = null;
        try{
            FileOutputStream fos = new FileOutputStream(archivo,editable);
            ps = new PrintStream(fos);
        }
        catch(FileNotFoundException fnfe){
            System.out.println("Problemas con el archivo");
        }
    return ps;
    }//End getPrintStream
   
    
    private int countlinesArchive(String cataloguetxt) {
          int contador = 0;
        BufferedReader br = getBufferedReader(cataloguetxt);
        try{    
            String registroActual = br.readLine();
            
            while(registroActual != null){
                contador++;
                registroActual = br.readLine();
                
            }//End while 
        }
        catch(IOException ioe){
            JOptionPane.showMessageDialog(null, "Problemas con el archivo");
        }
    return contador;
    }

    private BufferedReader getBufferReader(String cataloguetxt) {
           File archivo = new File(cataloguetxt);
        BufferedReader br = null;
        try{
            FileInputStream fis = new FileInputStream(archivo);
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
        }
        catch(FileNotFoundException fnfe){
            System.out.println("Problemas con el archivo.");
        }
    return br;
    }
  
     public String getNombreArchivo(String archivo, int lugarNombre){
        String nombreArchivo = "";
        int contandor = 0;
        for (int i = 0; i < archivo.length() && contandor != lugarNombre+1; i++) {
            if(archivo.charAt(i)=='|')
                contandor++;
            if(contandor==lugarNombre && archivo.charAt(i)!='|')
                nombreArchivo += archivo.charAt(i);
        }
    return nombreArchivo;
    }//Fin getNombreArchivo

    private BufferedReader getBufferedReader(String cataloguetxt) {
        File archivo = new File(cataloguetxt);
        BufferedReader br = null;
        try{
            FileInputStream fis = new FileInputStream(archivo);
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
        }
        catch(FileNotFoundException fnfe){
            System.out.println("Problemas con el archivo.");
        }
    return br;
    }
     
     
  
    
    
    
}//end
