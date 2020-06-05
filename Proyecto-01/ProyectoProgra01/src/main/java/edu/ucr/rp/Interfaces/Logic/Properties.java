/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces.Logic;

/**
 *
 * @author Equipo
 */
public class Properties {
    private String Name;
    private String Description;

    public Properties(String Name, String Description) {
        this.Name = Name;
        this.Description = Description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Properties{Name=").append(Name);
        sb.append(", Description=").append(Description);
        sb.append('}');
        return sb.toString();
    }
    
    
}


