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
 * 
 */
public class Registers {
    private String nameCatalogue;
    private String nameProduct;
    private String descriptionProperties;

    public Registers(String nameCatalogue, String nameProduct,String descriptionProperties) {
        this.nameCatalogue = nameCatalogue;
        this.nameProduct = nameProduct;
        this.descriptionProperties = descriptionProperties;
    }//Constructor

    public String getNameCatalogue() {
        return nameCatalogue;
    }//getNameCatalogue

    public void setNameCatalogue(String nameCatalogue) {
        this.nameCatalogue = nameCatalogue;
    }//setnameCatalogue

    public String getNameProduct() {
        return nameProduct;
    }//getnameProduct

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }//setnameProduct

    public String getDescriptionProperties() {
        return descriptionProperties;
    }//getDescription

    public void setDescriptionProperties(String descriptionProperties) {
        this.descriptionProperties = descriptionProperties;
    }//setDescription

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Registers{nameCatalogue=").append(nameCatalogue);
        sb.append(", nameProduct=").append(nameProduct);
        sb.append(", descriptionProperties=").append(descriptionProperties);
        sb.append('}');
        return sb.toString();
    }//toString

}//Registers


