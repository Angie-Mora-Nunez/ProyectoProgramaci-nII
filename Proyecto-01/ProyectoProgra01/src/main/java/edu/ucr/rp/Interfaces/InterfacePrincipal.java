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
  
    @Override
    public void start(Stage stage) throws Exception {
          this.stage=stage;
        title(stage);
        GridPane pane = buildPane();
        setupControls(pane);
        addHandlers();
        stage.setScene(createScene(pane));
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
        gridPane.setPadding(new Insets(40, 40,40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints columnOneConstraints = new ColumnConstraints(LABEL_WITH, LABEL_WITH, LABEL_WITH_MAX);
        columnOneConstraints.setHalignment(HPos.RIGHT);
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(INPUT_WITH, INPUT_WITH, INPUT_WITH_MAX);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }//Gridpane
     
      private TextField buildTextInput(String text, GridPane pane, int row) {
        Label minNumberLabel = new Label(text);
        pane.add(minNumberLabel, 0, row);
        TextField textField = new TextField();
        pane.add(textField, 1, row);
        return textField;
    }//TextField
     
     private void setupControls(GridPane pane) {
        Calendar calendario = new GregorianCalendar();
           int hora = calendario.get(Calendar.HOUR_OF_DAY);
        btn_Welcome = buildGenerateButton("Bienvenido", pane, 5);
        btn_exit= buildGenerateButton("Salir", pane, 6);
     
      
     }
     
     private Button buildGenerateButton(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 0, row, 2, 1);
        GridPane.setHalignment(button, HPos.CENTER);
      button.setFont(new Font("Indie Flower",16));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
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
     
     

     private Scene createScene (Pane pane){
          pane.setStyle("-fx-background-color:#37D8E3" );
          
    return new Scene (pane,800,800);
    }//scene

   
   
    
    
}//end
