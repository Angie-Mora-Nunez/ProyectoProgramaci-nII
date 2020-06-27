/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces.Logic;

import edu.ucr.rp.Interfaces.Products.InterfaceAddRegisters;
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
    
     public void addOnFileNew(File f){
      
        try {
           FileOutputStream fos = new FileOutputStream(f);
           PrintStream ps = new PrintStream(fos);
           ps.println();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(manteinFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ArrayList getRegistersFileCatalog() throws IOException{
       File f = new File("catalogo.txt");
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
    
    public ArrayList getRegistersName(){
      File f = new File("catalogo.txt");
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
                temp.add(sT.nextToken());
             else if(controlToken==2)
                 properties= sT.nextToken();
         controlToken++;
         }// End while
         
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
    
     }//getRegistersRegisters 
      
    public ArrayList getRegistersProperties(){
      File f = new File("catalogo.txt");
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
                nameCatalog=sT.nextToken();
             else if(controlToken==2)
                 temp.add(sT.nextToken());
                 
         controlToken++;
         }// End while
         
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
    
     }//getRegistersRegisters 
    
    public int GetposNameCatalog(ArrayList namesCatalog , String nameC){
        int pos = 0;
        for (int i = 0; i < namesCatalog.size(); i++) {
           String name =(String) namesCatalog.get(i);
            if (name.equals(nameC)) {
              pos=i;   
            }
        }
        
      return pos;
    }
    
    public ArrayList getPropertiespos(String properties){
    
        ArrayList temp = new ArrayList();
   
         int controlToken=1;
         StringTokenizer sT = new StringTokenizer(properties,",");
         
         while(sT.hasMoreTokens()){
              temp.add(sT.nextToken());
                 
         controlToken++;
         }// End while
         
    return temp;
        
    }
    
    public ArrayList getRegistersFileRegister() throws IOException{
       File f = new File("FileRegister.txt");
        ArrayList temp = new ArrayList();
   
    int indexArray=0;
    try{
     FileInputStream fis= new FileInputStream(f);
     InputStreamReader isr= new InputStreamReader(fis);
     BufferedReader br = new BufferedReader(isr);
     
     String actualRegister = br.readLine();
     
     while(actualRegister!=null){
         String nameCatalog="" ,nameProduct="", properties="" , Description="";
         int controlToken=1;
         StringTokenizer sT = new StringTokenizer(actualRegister,"|");
         
         while(sT.hasMoreTokens()){
             if(controlToken==1)
                 nameCatalog=sT.nextToken();
             else if(controlToken==2)
                 nameProduct=sT.nextToken();
             else if(controlToken==3)
                 properties=sT.nextToken();
             else if(controlToken==4)
                 Description=sT.nextToken();
         controlToken++;
         }// End while
         
      Registers r = new Registers(nameCatalog, nameProduct, properties, Description);
      temp.add(r);
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
    
    public Registers GetposRegister(ArrayList namesRegister , String nameC , String NameProduct){
        Registers r =null;
        for (int i = 0; i < namesRegister.size(); i++) {
            r = (Registers) namesRegister.get(i);
            if (r.getNameCatalogue().equals(nameC)&& r.getNameProduct().equalsIgnoreCase(NameProduct)) {
              return r;   
            }
        }
      return r;
    }
    
    public Registers GetposRegisterName(ArrayList namesRegister , String NameProduct){
        Registers r =null;
        for (int i = 0; i < namesRegister.size(); i++) {
            r = (Registers) namesRegister.get(i);
            if (r.getNameProduct().equalsIgnoreCase(NameProduct)) {
              return r;   
            }
        }
      return r;
    }
    
     public ArrayList GetposRegisterNames(ArrayList namesRegister ,String NameProduct){
         ArrayList temp = new ArrayList();
         Registers r =null;
        for (int i = 0; i < namesRegister.size(); i++) {
            r = (Registers) namesRegister.get(i);
            if (!r.getNameProduct().equalsIgnoreCase(NameProduct)) {
              temp.add(r);
            }
        }
      return temp;
    }
    
    public String getUseer(){
      File User = new File ("user.txt");
       String cross="";
   
    try{
     FileInputStream fis= new FileInputStream(User);
     InputStreamReader isr= new InputStreamReader(fis);
     BufferedReader br = new BufferedReader(isr);
     
     String actualRegister = br.readLine();
     
     while(actualRegister!=null){
         String nameCatalog="";
       
         cross = nameCatalog;
      
      
      actualRegister= br.readLine();
     }// end while 
     
    }// end try
    catch(FileNotFoundException fnfe ){
      JOptionPane.showMessageDialog(null,"Error"+fnfe);
    }// end catch 
    catch(IOException io){
      JOptionPane.showMessageDialog(null,"Error"+ io); 
        
    }// end catch
    
    return cross;
    
     }//getRegistersRegisters 
    
}























