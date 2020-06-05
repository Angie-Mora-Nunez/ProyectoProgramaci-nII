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
 */
public class saveCatalogSquema {
    ArrayList catalogsSchema;

    public saveCatalogSquema(ArrayList catalogsSchema) {
        this.catalogsSchema = catalogsSchema;
    }//constructor

    
    //getters and Setters
    public ArrayList getCatalogsSchema() {
        return catalogsSchema;
    }//get

    public void setCatalogsSchema(ArrayList catalogsSchema) {
        this.catalogsSchema = catalogsSchema;
    }//set

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("saveCatalogSquema{catalogsSchema=").append(catalogsSchema);
        sb.append('}');
        return sb.toString();
    }//To String Schema
    
    
}//Save Catalogue Schema End
