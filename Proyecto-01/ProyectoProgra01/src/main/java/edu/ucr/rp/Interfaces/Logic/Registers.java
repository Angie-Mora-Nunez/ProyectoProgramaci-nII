/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces.Logic;

import java.util.ArrayList;

/**
 *
 * @authores 
 * Marian Murillo Bonilla
 * Angie Mora Núñez
 * 
 */
public class Registers {
    private String nameCatalogue;
    private String nameProduct;
    private String properties;
    private String descriptionProperties;

    public Registers(String nameCatalogue, String nameProduct, String properties, String descriptionProperties) {
        this.nameCatalogue = nameCatalogue;
        this.nameProduct = nameProduct;
        this.properties = properties;
        this.descriptionProperties = descriptionProperties;
    }

    public String getNameCatalogue() {
        return nameCatalogue;
    }

    public void setNameCatalogue(String nameCatalogue) {
        this.nameCatalogue = nameCatalogue;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getDescriptionProperties() {
        return descriptionProperties;
    }

    public void setDescriptionProperties(String descriptionProperties) {
        this.descriptionProperties = descriptionProperties;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Registers{nameCatalogue=").append(nameCatalogue);
        sb.append(", nameProduct=").append(nameProduct);
        sb.append(", properties=").append(properties);
        sb.append(", descriptionProperties=").append(descriptionProperties);
        sb.append('}');
        return sb.toString();
    }
    
    

   
}//Registers



