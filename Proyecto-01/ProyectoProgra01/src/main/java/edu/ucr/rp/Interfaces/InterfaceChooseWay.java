/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces;


import edu.ucr.rp.Interfaces.Products.InterfaceProducts;
import static edu.ucr.rp.Interfaces.UIConstaints.INPUT_WITH;
import static edu.ucr.rp.Interfaces.UIConstaints.INPUT_WITH_MAX;
import static edu.ucr.rp.Interfaces.UIConstaints.LABEL_WITH;
import static edu.ucr.rp.Interfaces.UIConstaints.LABEL_WITH_MAX;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
 
public class InterfaceChooseWay extends Application{
    private Label lp;
    private Button btn_catalogue;
    private Button btn_exit;
    private Button btn_Products;
    private Stage stage;
    private Label lblBussiness;
   // Intancias 
 
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
        stage.setTitle("Agregar catalogos o productos");
    }//title
     private void addHandlers() {
     InterfaceEntry iu = new InterfaceEntry();
     InterfaceCatalogue iU = new InterfaceCatalogue();
     InterfaceProducts iP = new InterfaceProducts();
         btn_exit.setOnAction(actionEvent -> {
         try {
             iu.start(stage);
        
         } catch (Exception ex) {
             Logger.getLogger(InterfaceChooseWay.class.getName()).log(Level.SEVERE, null, ex);
         }
         });
         
         btn_catalogue.setOnAction(actionEvent -> {
         
         try {
             iU.start(stage);
        
         
         } catch (Exception ex) {
             Logger.getLogger(InterfaceChooseWay.class.getName()).log(Level.SEVERE, null, ex);
         }
         });
         
         btn_Products.setOnAction(actionEvent -> {
         
         try {
             iP.start(stage);
         } catch (Exception ex) {
             Logger.getLogger(InterfaceChooseWay.class.getName()).log(Level.SEVERE, null, ex);
         }
             
         });
         
         
         
    }//eventos
    
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
    }//GridPane
     
      private TextField buildTextInput(String text, GridPane pane, int row) {
        Label minNumberLabel = new Label(text);
        pane.add(minNumberLabel, 0, row);
        TextField textField = new TextField();
        pane.add(textField, 1, row);
        return textField;
    }//TExtField
     
     private void setupControls(GridPane pane) {
       
        btn_catalogue = buttonCatalogue("Catalogos", pane, 5);
        btn_Products=buttonProducts("Productos", pane, 5);
        btn_exit= buttonSalir("Salir", pane, 5);
        lblBussiness=LabelBussines("Posgrados S.A", pane, 5);

    }//Controladores
     
     private Button buttonCatalogue(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 0, 15);
         button.setFont(new Font("Indie Flower",16));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
        
        GridPane.setMargin(button, new Insets(20, 0, 20, 0));
        return button;
    }//button
     
      private Button buttonProducts(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 1, 15);
         button.setFont(new Font("Indie Flower",16));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
        
        GridPane.setMargin(button, new Insets(20, 0, 20, 0));
        return button;
    }//button
      
       private Button buttonSalir(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 3, 19);
         button.setFont(new Font("Indie Flower",16));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
        
        GridPane.setMargin(button, new Insets(20, 0, 20, 0));
        return button;
    }//button
     
       private Label LabelBussines (String text, GridPane pane, int row) {
        Label lb = new Label(text);
        pane.add(lb,5, 0);
        lb.setFont(new Font("Footlight MT Light",16));
        GridPane.setMargin(lb, new Insets(10, 0, 10, 0));
        return lb;
    }//button
       
       
       
    
     private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }//showalert

     private Scene createScene (Pane pane){
         
          pane.setStyle("-fx-background-color:#37D8E3" );
         
    return new Scene (pane,800,800);
    }//scene

  
 
}//end
