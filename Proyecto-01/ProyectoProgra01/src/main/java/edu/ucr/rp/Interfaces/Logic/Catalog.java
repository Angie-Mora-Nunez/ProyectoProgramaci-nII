/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces.Logic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 * Marian Murillo Bonilla
 * Angie Mora Núñez
 */
public class Catalog {
    private String nameCatalog;
    private String properties;

    public Catalog(String nameCatalogue, String properties) {
        this.nameCatalog = nameCatalogue;
        this.properties = properties;
    }//constructor

    public String getNameCatalog() {
        return nameCatalog;
    }//getname

    public void setNameCatalog(String nameCatalog) {
        this.nameCatalog = nameCatalog;
    }//setname

    public String getProperties() {
        return properties;
    }//getPropiedades

    public void setProperties(String properties) {
        this.properties = properties;
    }//setPropiedades

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Catálogo{Nombre de Catálogo=").append(nameCatalog);
        sb.append(", Propiedades=").append(properties);
        sb.append('}'+"\n");
        return sb.toString();
    }

  
    
   
    
    
}//constructor
