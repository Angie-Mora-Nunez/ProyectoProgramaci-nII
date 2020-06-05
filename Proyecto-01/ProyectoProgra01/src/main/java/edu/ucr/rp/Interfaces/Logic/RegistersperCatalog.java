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
public class RegistersperCatalog {
    String nameSchema;
    ArrayList listRegisters;

    public RegistersperCatalog(String nameSchema, ArrayList listRegisters) {
        this.nameSchema = nameSchema;
        this.listRegisters = listRegisters;
    }//constructor

    //Getters and Setters
    public String getNameSchema() {
        return nameSchema;
    }//getNameSchema

    public void setNameSchema(String nameSchema) {
        this.nameSchema = nameSchema;
    }//setNameSchema

    public ArrayList getListRegisters() {
        return listRegisters;
    }//getListRegisters

    public void setListRegisters(ArrayList listRegisters) {
        this.listRegisters = listRegisters;
    }//setListRegisters

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RegistersperCatalog{nameSchema=").append(nameSchema);
        sb.append(", listRegisters=").append(listRegisters);
        sb.append('}');
        return sb.toString();
    }//String
    
}//RegistersCatalogEnd
