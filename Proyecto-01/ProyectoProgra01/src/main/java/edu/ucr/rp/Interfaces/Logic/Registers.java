/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces.Logic;

import java.util.ArrayList;

/**
 *
 * @author Equipo
 */
public class Registers {
    private String Name;
    private ArrayList<Properties> properties;

    public Registers(String Name, ArrayList<Properties> properties) {
        this.Name = Name;
        this.properties = properties;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public ArrayList<Properties> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Properties> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Registers{Name=").append(Name);
        sb.append(", properties=").append(properties);
        sb.append('}');
        return sb.toString();
    }
    
    
}


