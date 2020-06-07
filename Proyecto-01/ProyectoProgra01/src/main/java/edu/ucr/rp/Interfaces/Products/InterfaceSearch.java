/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces.Products;

import edu.ucr.rp.Interfaces.*;
import edu.ucr.rp.Interfaces.Logic.Registers;
import static edu.ucr.rp.Interfaces.UIConstaints.INPUT_WITH;
import static edu.ucr.rp.Interfaces.UIConstaints.INPUT_WITH_MAX;
import static edu.ucr.rp.Interfaces.UIConstaints.LABEL_WITH;
import static edu.ucr.rp.Interfaces.UIConstaints.LABEL_WITH_MAX;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @authores 
 * Marian Murillo Bonilla
 * Angie Mora Núñez
 * 
 */
public class InterfaceSearch extends Application {
    private Label labelSearch;
    private Label labelShow;
    private Button buttonSearch;
    private Button btn_exit;
    private TextField txtSearching;
    private Label labelSearching;
    private Stage stage;
    private ComboBox cmbCatalogues;
    ArrayList list = getRegistersRegisters();
    ArrayList listComplete =getRegistersBusqueda();
    ArrayList listShow = new ArrayList();
    private TextArea txtShow;
  
    @Override
    public void start(Stage stage) throws Exception {
        this.stage=stage;
        title(stage);
        GridPane pane = buildPane();
        setupControls(pane);
        addHandlers();
        stage.setScene(createScene(pane));
        stage.show();
    }//Start
     public void display() {
        launch();
    }//dispaly
    
    private void title(Stage stage) {
        stage.setTitle("Buscar en Catálogo");
    }//title
     private void addHandlers() {
        InterfaceProducts iP = new InterfaceProducts();
         
          btn_exit.setOnAction(actionEvent -> {
            try {
                iP.start(stage);
            } catch (Exception ex) {
                Logger.getLogger(InterfaceSearch.class.getName()).log(Level.SEVERE, null, ex);
            }//Try/catch
          });//ExitAccion
         
          buttonSearch.setOnAction(actionEvent -> {
          txtShow.setVisible(true);
          listShow.add(SearchName(list, txtSearching.getText(),listComplete));   
          txtShow.setText(listShow.toString());
         });//buscarAccion
         
    
         
    }//eventos
    
     private GridPane buildPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(5, 5,5, 5));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        ColumnConstraints columnOneConstraints = new ColumnConstraints(LABEL_WITH, LABEL_WITH, LABEL_WITH_MAX);
        columnOneConstraints.setHalignment(HPos.RIGHT);
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(INPUT_WITH, INPUT_WITH, INPUT_WITH_MAX);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);
        return gridPane; 
    }//GridPane
     
     private void setupControls(GridPane pane) {
         buttonSearch = ButtonSearch("Buscar en Catálogo", pane, 3);
         btn_exit= ButtonExit("Salir", pane, 3);
         labelShow=labelShow("Lista de Catálogos", pane, 3);
         labelSearch=labelCatalogue("Buscar: ", pane, 3);
         txtShow=txtArea("", pane, 3);
         txtShow.setVisible(false);
         cmbCatalogues=comboBoxNamesCatalogue(pane, 3);
         txtSearching=textFieldSearching(pane, 3);
         labelSearching=labelSearching("Criterio de Busqueda", pane, 3);
    }//Controladores
     
     
       private Label labelCatalogue(String name,GridPane pane, int row) {
          Label label = new Label(name);
          pane.add(label, 0,5);
          label.setFont(new Font("Footlight MT Light",16));
          return label;
    }//Label
       
        private Label labelSearching(String name,GridPane pane, int row) {
          Label label = new Label(name);
          pane.add(label, 0,6);
          label.setFont(new Font("Footlight MT Light",16));
          return label;
    }//Label
       
         private TextField textFieldSearching(GridPane pane, int row) {
          TextField txt = new TextField();
          pane.add(txt, 1,6);
          txt.setFont(new Font("Footlight MT Light",16));
          return txt;
    }//Label
 
        private TextArea txtArea(String name,GridPane pane, int row) {
          TextArea txt = new TextArea(name);
          pane.add(txt, 1,8);
          return txt;
    }//Label
         
        private Label labelShow(String name,GridPane pane, int row) {
          Label label = new Label(name);
          pane.add(label, 0,0);
          label.setFont(new Font("Footlight MT Light",16));
          return label;
    }//Label
     
        private ComboBox comboBoxNamesCatalogue(GridPane pane, int row) {
        ComboBox cmbList = new ComboBox();
        cmbList.setValue("               Catálogos                 ");
        cmbList.setStyle(("-fx-font: 16px \"Footlight MT Light\""));
        pane.add(cmbList, 1,5);
          for (int i = 0; i < list.size(); i++) {
              cmbList.getItems().addAll(list.get(i));
          }//for
        return cmbList;
       }//ComboBox
        
        
        private ComboBox comboBoxNames(GridPane pane, int row) {
        ComboBox cmbList = new ComboBox();
        cmbList.setValue("               Productos                 ");
        cmbList.setStyle(("-fx-font: 16px \"Footlight MT Light\""));
        pane.add(cmbList, 1,6);
          for (int i = 0; i < list.size(); i++) {
              cmbList.getItems().addAll(list.get(i));
          }//for
        return cmbList;
       }//ComboBox
        
      private Button ButtonSearch(String label, GridPane pane, int row) {
            Button button = new Button(label);
            pane.add(button, 2, 6);
            button.setFont(new Font("Indie Flower",14));// determinar el tipo de letra y color radio button
            button.setTextFill(Color.BLACK);
            button.setStyle("-fx-background-color: WHITE");
            return button;
    }//button
      
       private Button ButtonExit(String label, GridPane pane, int row) {
          Button button = new Button(label);
          pane.add(button,2, 10);
          button.setFont(new Font("Indie Flower",14));// determinar el tipo de letra y color radio button
          button.setTextFill(Color.BLACK);
          button.setStyle("-fx-background-color: WHITE");
        return button;
    }//button
     
     private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }//showalert

    private Scene createScene (Pane pane) {
         pane.setStyle("-fx-background-color:#37D8E3" );
         return new Scene (pane,900,900);
    }//scene
  
     public ArrayList getRegistersRegisters(){
     ArrayList listas = new ArrayList();
      File fileCatalogueTokens = new File("CatalogueTokens.txt");
         try {
             FileInputStream fis = new FileInputStream(fileCatalogueTokens);
             InputStreamReader isr= new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr);
             String actualRegister = br.readLine();
             while(actualRegister!=null){
             String nombre="";
             int controlaTokens=1;
             StringTokenizer sT = new StringTokenizer(actualRegister,"|");
             listas.add(sT.nextToken());
             actualRegister=br.readLine();
             }//whileActualRegisters
             
         } catch (FileNotFoundException ex) {
             Logger.getLogger(InterfaceAddRegisters.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(InterfaceAddRegisters.class.getName()).log(Level.SEVERE, null, ex);
         }//try/catch
        
     return listas;
     }//getRegistersRegisters 
     
     
      public ArrayList getRegistersBusqueda(){
      ArrayList list2 = new ArrayList();
       File fileRegisTokens = new File("RegisTokens.txt");
         try {
             FileInputStream fiso = new FileInputStream(fileRegisTokens);
             InputStreamReader isro= new InputStreamReader(fiso);
             BufferedReader bro = new BufferedReader(isro);
             String actualRegister = bro.readLine();
             
             while(actualRegister!=null){
             String Catalog="",name="",namePropie="";
             int controlaTokens=1;
             StringTokenizer sT = new StringTokenizer(actualRegister,"|");
             
             while(sT.hasMoreElements()){
             if(controlaTokens==1)
                 Catalog=sT.nextToken();
             else if(controlaTokens==2)
                 name=sT.nextToken();
             else if(controlaTokens==3)
                 namePropie=sT.nextToken();
             
             controlaTokens++;
             }//While
                 Registers registers = new Registers(Catalog, name, namePropie);
                 list2.add(registers);
             actualRegister=bro.readLine();
             }//whileActualRegisters
             
         } catch (FileNotFoundException ex) {
             Logger.getLogger(InterfaceAddRegisters.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(InterfaceAddRegisters.class.getName()).log(Level.SEVERE, null, ex);
         }//try/catch
        
     return list2;
     }//getRegistersRegisters 

      public Registers SearchName(ArrayList name , String nameSearch,ArrayList properties) {
            String output =""; 
            Registers registr=null;
            for (int j = 0; j < name.size(); j++) {
                for (int i = 0; i <properties.size(); i++) {
                    registr=(Registers)properties.get(i);
                    if (name.get(j).toString().equalsIgnoreCase(registr.getNameCatalogue())&& nameSearch.equalsIgnoreCase(registr.getNameProduct())){ 
                          return registr;
                    }//if
                }//for
                }//for
             return registr;
       }//searchName      
    
    
    
    
}//endSearch




