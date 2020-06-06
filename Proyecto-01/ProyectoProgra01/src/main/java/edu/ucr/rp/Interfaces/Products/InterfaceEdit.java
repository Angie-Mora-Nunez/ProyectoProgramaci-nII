/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces.Products;

import edu.ucr.rp.Interfaces.*;
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
 * @author 
 * Marian Murillo Bonilla
 * Angie Mora Núñez
 */
public class InterfaceEdit extends Application {
     private Label lp;
    private Button generateButton;
    private Button btn_exit;
    private TextField txtBuscar;
    private ComboBox cmbSearchNames;
    ArrayList list = getRegisters();
    
    private Stage stage;
  
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
            }
         });
         
    }//eventos
    
     private GridPane buildPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 10,10, 10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints columnOneConstraints = new ColumnConstraints(LABEL_WITH, LABEL_WITH, LABEL_WITH_MAX);
        columnOneConstraints.setHalignment(HPos.RIGHT);
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(INPUT_WITH, INPUT_WITH, INPUT_WITH_MAX);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);
        return gridPane; 
    }//GridPane
     
      private TextField buildTextInput(String text, GridPane pane, int row) {
        Label minNumberLabel = new Label(text);
        pane.add(minNumberLabel, 0, row);
        TextField textField = new TextField();
        pane.add(textField, 1, row);
        return textField;
    }//TExtField
     
     private void setupControls(GridPane pane) {
       
         generateButton = ButtonSearch("Buscar en Catálogo", pane, 5);
         btn_exit= ButtonExit("Salir", pane, 5);
         lp=labelCatalogue("Buscar Producto: ", pane, 5);
         cmbSearchNames=comboBoxNames(pane, 5);
         
    }//Controladores
     
     
     private TextField searchCatalogue(String text, GridPane pane, int row) {
        Label minNumberLabel = new Label(text);
        pane.add(minNumberLabel, 0,19);
        TextField textField = new TextField();
        pane.add(textField, 1,19 );
        return textField;
    }//TExtField
     
     
     private Button buildGenerateButton(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 1, 1);
        button.setFont(new Font("Indie Flower",14));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
        GridPane.setHalignment(button, HPos.CENTER);
        GridPane.setMargin(button, new Insets(10, 0, 10, 0));
        return button;
    }//button
     
     
         private Label labelCatalogue(String name,GridPane pane, int row) {
          Label label = new Label(name);
          pane.add(label, 0,5);
          label.setFont(new Font("Footlight MT Light",16));
          GridPane.setMargin(label, new Insets(10, 0, 10, 0));
        return label;
    }//TExtField
         
         
         private ComboBox comboBoxNames(GridPane pane, int row) {
            
        ComboBox cmbList = new ComboBox();
        cmbList.setValue("      Nombre de Productos:              ");
        cmbList.setStyle(("-fx-font: 16px \"Footlight MT Light\""));
        pane.add(cmbList, 1,5);
          for (int i = 0; i < list.size(); i++) {
              cmbList.getItems().addAll(list.get(i));
          }//for
        return cmbList;
    }//TExtField
     
     
       private Button ButtonSearch(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 2, 5);
        button.setFont(new Font("Indie Flower",14));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
        GridPane.setHalignment(button, HPos.CENTER);
        GridPane.setMargin(button, new Insets(10, 0, 10, 0));
        return button;
    }//button
      
       private Button ButtonExit(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button,6, 25);
        button.setFont(new Font("Indie Flower",14));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
        GridPane.setHalignment(button, HPos.CENTER);
        GridPane.setMargin(button, new Insets(10, 0, 10, 0));
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
      
      
      public ArrayList getRegisters(){
     ArrayList list = new ArrayList();
      File nameProducts = new File("nameProducts.txt");
         try {
             FileInputStream fis = new FileInputStream(nameProducts);
             InputStreamReader isr= new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr);
            
             String actualRegister = br.readLine();
             
             while(actualRegister!=null){
             String nombre="";
             int controlaTokens=1;
             StringTokenizer sT = new StringTokenizer(actualRegister,",");
             
             list.add(sT.nextToken());
             
             
             actualRegister=br.readLine();
             }//whileActualRegisters
             
         } catch (FileNotFoundException ex) {
             Logger.getLogger(InterfaceAddRegisters.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(InterfaceAddRegisters.class.getName()).log(Level.SEVERE, null, ex);
         }//try/catch
        
     return list;
     }//getRegistersRegisters 
      
      
      
      
      
      
}//end




