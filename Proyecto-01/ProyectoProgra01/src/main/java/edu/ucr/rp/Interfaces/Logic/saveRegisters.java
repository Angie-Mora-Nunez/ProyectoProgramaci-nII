/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces.Logic;

import java.util.ArrayList;

/**
 *
 * @author 
 * Marian Murillo Bonilla
 * Angie Mora Núñez
 * 
 */
public class saveRegisters {
    ArrayList<RegistersperCatalog> catalogRegister;

    public saveRegisters(ArrayList catalogRegister) {
        this.catalogRegister = catalogRegister;
    }//constructor
    
    //getters and setters
    public ArrayList getCatalogRegister() {
        return catalogRegister;
    }//getcatalogue

    public void setCatalogRegister(ArrayList catalogRegister) {
        this.catalogRegister = catalogRegister;
    }//setCatalogue

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("saveRegisters{catalogRegister=").append(catalogRegister);
        sb.append('}');
        return sb.toString();
    }//String
    
    
    
    
    
    
    
}//saveRegistersEnd
