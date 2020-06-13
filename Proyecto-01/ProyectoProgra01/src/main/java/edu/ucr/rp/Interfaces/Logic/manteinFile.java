/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces.Logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Equipo
 */
public class manteinFile {
    
    public void addOnFile(File f , String documentSave){
      
        try {
           FileOutputStream fos = new FileOutputStream(f,true);
           PrintStream ps = new PrintStream(fos);
           ps.println(documentSave);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(manteinFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ArrayList getRegistersFileCatalog(File f) throws IOException{
     ArrayList temp = new ArrayList();
   
    int indexArray=0;
    try{
     FileInputStream fis= new FileInputStream(f);
     InputStreamReader isr= new InputStreamReader(fis);
     BufferedReader br = new BufferedReader(isr);
     
     String actualRegister = br.readLine();
     
     while(actualRegister!=null){
         String nameCatalog="" , properties="";
         int controlToken=1;
         StringTokenizer sT = new StringTokenizer(actualRegister,"|");
         
         while(sT.hasMoreTokens()){
             if(controlToken==1)
                 nameCatalog= sT.nextToken();
             else if(controlToken==2)
                 properties= sT.nextToken();
         controlToken++;
         }// End while
         
     Catalog catalog = new Catalog(nameCatalog, properties);
      temp.add(catalog);
      indexArray++;// incremento del índice del arreglo 
      
      actualRegister= br.readLine();
     }// end while 
     
    }// end try
    catch(FileNotFoundException fnfe ){
      JOptionPane.showMessageDialog(null,"Error"+fnfe);
    }// end catch 
    catch(IOException io){
      JOptionPane.showMessageDialog(null,"Error"+ io); 
        
    }// end catch
    
    return temp;
    
    }
    
    
    
}




