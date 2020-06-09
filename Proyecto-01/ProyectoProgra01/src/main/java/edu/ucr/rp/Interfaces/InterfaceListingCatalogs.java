/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces;

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
import java.util.Collections;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 * Marian Murillo Bonilla
 * Angie Mora Núñez
 */
public class InterfaceListingCatalogs extends Application{
      private Label lp;
    private Button generateButton;
    private Button btn_exit;
    private TextArea txtShow;
    
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
        stage.setTitle("Lista de catálogos");
    }//title
     private void addHandlers() {
         InterfaceCatalogue iu = new InterfaceCatalogue();
         btn_exit.setOnAction(actionEvent -> {
             try {
                 iu.start(stage);
             } catch (Exception ex) {
                 Logger.getLogger(InterfaceListingCatalogs.class.getName()).log(Level.SEVERE, null, ex);
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

     private void setupControls(GridPane pane) {
          String value="";
          BufferedReader br = getBufferedReader("catalogo.txt");
          ArrayList listpo= new ArrayList();
         String output="";
   
          try {
              while ((value = br.readLine())!=null) {
                  
                  listpo.add(value);
              }//while
               Collections.sort(listpo);
               
              for (int i = 0; i < listpo.size(); i++) {
                  output+=listpo.get(i)+"\n";
              }//for
              
          } catch (IOException ex) {
              Logger.getLogger(InterfaceListingCatalogs.class.getName()).log(Level.SEVERE, null, ex);
          }//try/catch
    
      
         btn_exit= buildGenerateButton("Regresar", pane, 6);
         txtShow=buildTextAreaShow(output, pane, 5);
    }//Controladores
     
     
       private TextArea buildTextAreaShow(String text,GridPane pane, int row) {
        TextArea txtArea = new TextArea(text);
        pane.add(txtArea, 0, 1);
        TextField textField = new TextField();
        GridPane.setHalignment(txtArea, HPos.CENTER);
        return txtArea;
    }//TExtField
     
       
     private Button buildGenerateButton(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 1, 3);
        button.setFont(new Font("Indie Flower",16));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
        File files3 = new File("gou.png");
        Image images3 = new Image(files3.toURI().toString());
        ImageView ivs3 = new ImageView(images3);
         button.setGraphic(ivs3);
        GridPane.setHalignment(button, HPos.CENTER);
        GridPane.setMargin(button, new Insets(20, 0, 20, 0));
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

    private BufferedReader getBufferedReader(String lisProperties) {
          File listas = new File("catalogo.txt");
        BufferedReader br = null;
        try{
            FileInputStream fis = new FileInputStream(listas);
            InputStreamReader isr = new InputStreamReader(fis);
             br = new BufferedReader(isr);
        }//try
        catch(FileNotFoundException fnfe){
           JOptionPane.showMessageDialog(null, "problema con el archivo"+fnfe);
        }//catch
        return br;  
    }//getBufferes
    
   
    
    
    
    
    
}//end




