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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author 
 * Marian Murillo Bonilla
 * Angie Mora Núñez
 */
public class InterfacePrincipal extends Application {
    // elementos fx a usar 
    private Button btn_Welcome;
    private Stage stage;
    private Button btn_exit;
    private ImageView img;
  
    @Override
    public void start(Stage stage) throws Exception {
          this.stage=stage;
        title(stage);
        GridPane pane = buildPane();
        setupControls(pane);
        addHandlers();
        stage.setScene(createScene(pane));
        stage.setResizable(false);
        stage.show();
    }//start
     public void display() {
        launch();
    }//display
    
    private void title(Stage stage) {
        stage.setTitle("Sistema de Inventario");
    }//tile
     private void addHandlers() {
         InterfaceCatalogue iu = new InterfaceCatalogue();
         InterfaceEntry iG = new InterfaceEntry();
         btn_Welcome.setOnAction(actionEvent -> {
             try {
                 iG.start(stage);
             }//try 
             catch (Exception ex) {
                 Logger.getLogger(InterfacePrincipal.class.getName()).log(Level.SEVERE, null, ex);
             }//catch
         });
         
         btn_exit.setOnAction(acctionEvent-> stage.close());
         
    }//Acionbuttones
    
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
    }//Gridpane
     
      private TextField buildTextInput(String text, GridPane pane, int row) {
        Label minNumberLabel = new Label(text);
        pane.add(minNumberLabel, 0, row);
        TextField textField = new TextField();
        pane.add(textField, 1, row);
        return textField;
    }//TextField
     
     private void setupControls(GridPane pane) throws FileNotFoundException {
        Calendar calendario = new GregorianCalendar();
           int hora = calendario.get(Calendar.HOUR_OF_DAY);
        btn_Welcome = ButtonGetIn("Ingresar", pane, 3);
        btn_exit= ButtongetOut("Salir", pane, 3);
        img=ImagePreview(pane, 6);
      
     }
     
     private Button ButtonGetIn(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 0, 1);
        GridPane.setHalignment(button, HPos.CENTER);
      button.setFont(new Font("Indie Flower",16));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
        File files3 = new File("entry.png");
        Image images3 = new Image(files3.toURI().toString());
        ImageView ivs3 = new ImageView(images3);
         button.setGraphic(ivs3);
        GridPane.setMargin(button, new Insets(20, 0, 20, 0));
        return button;
    }//Button
     
     
     private Button ButtongetOut(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 0,2 );//filas columnas
        GridPane.setHalignment(button, HPos.CENTER);
      button.setFont(new Font("Indie Flower",16));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
        File files3 = new File("salida.png");
        Image images3 = new Image(files3.toURI().toString());
        ImageView ivs3 = new ImageView(images3);
         button.setGraphic(ivs3);
        GridPane.setMargin(button, new Insets(20, 0, 20, 0));
        return button;
    }//Button
     
     
    
     private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }//showAlert
     
      private Label buildTextInpu(String text, GridPane pane, int row) {
        Label label = new Label(text);
        pane.add(label, 0, row);
       GridPane.setHalignment(label, HPos.CENTER);
        GridPane.setMargin(label, new Insets(20, 0, 20, 0));
        return label;
    }//TextField
     
      
      private ImageView ImagePreview(GridPane pane,int row) throws FileNotFoundException{
        FileInputStream imageStream = new FileInputStream("hola.png");
        Image imageS = new Image(imageStream);
        ImageView image = new ImageView(imageS);
        GridPane.setHalignment(image, HPos.CENTER);
        pane.add(image, 0, 0);
        GridPane.setMargin(image, new Insets(20, 0, 20, 0));
        return image;
      }

    private Scene createScene (Pane pane) {
         pane.setStyle("-fx-background-color:#37D8E3" );
         return new Scene (pane,900,900);
    }//scene

   
   
    
    
}//end
