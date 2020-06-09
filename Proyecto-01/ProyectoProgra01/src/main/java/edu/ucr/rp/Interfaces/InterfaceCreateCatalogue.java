/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces;

import edu.ucr.rp.Interfaces.Logic.Catalogue;

import static edu.ucr.rp.Interfaces.UIConstaints.INPUT_WITH;
import static edu.ucr.rp.Interfaces.UIConstaints.INPUT_WITH_MAX;
import static edu.ucr.rp.Interfaces.UIConstaints.LABEL_WITH;
import static edu.ucr.rp.Interfaces.UIConstaints.LABEL_WITH_MAX;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import util.JsonUtil;

/**
 *
 * @author 
 * Marian Murillo Bonilla
 * Angie Mora Núñez
 */
public class InterfaceCreateCatalogue extends Application {
   
    private TextField TxtName;
    private TextArea txtPropiedades;
    private Stage stage;
    private Label LblName;
    private Label lblPropiedades;
    private Button BtnSalida;
    private Button btn_Agregar;
    ArrayList CatalogueList = new ArrayList();
    private Button agregarTxtField;
    private Label lblBussiness;
    private Button btnshow;
   ArrayList<Catalogue> catalogs = new ArrayList<>();
    
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
    }//launch
    
    private void title(Stage stage) {
        stage.setTitle("Sistema de Control de Empleados");
    }//title
    
     private void addHandlers() {
       
         btn_Agregar.setOnAction((ActionEvent actionEvent) -> {
           
          ArrayList ar= new ArrayList();
          ar.add(txtPropiedades.getText());
           Catalogue c = new Catalogue(TxtName.getText(), txtPropiedades.getText());
          
           System.out.println(ar.toString());
             
           catalogs.add(c);
          
            File fileCatalogueshow = new File("catalogo.txt");
            
             try {
                 FileOutputStream fos = new FileOutputStream(fileCatalogueshow,true);
                 PrintStream ps = new PrintStream(fos);
                 ps.println(new Catalogue(TxtName.getText(),txtPropiedades.getText()));
                 AddProperties(TxtName.getText(),txtPropiedades.getText());
                 AddPropertiesALL(txtPropiedades.getText());
          
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(InterfaceCreateCatalogue.class.getName()).log(Level.SEVERE, null, ex);
             }//try/catch
            
             TxtName.clear();
             txtPropiedades.clear();
             
             ImageIcon icon = new ImageIcon("confirm.png");
            JOptionPane.showMessageDialog(null, "Se ha agredado el catálogo ", "Agregar Catálogo", 2, (Icon) icon);
            
             
         });
     
        InterfaceCatalogue iME = new InterfaceCatalogue();
         BtnSalida.setOnAction(actionEvent -> {
             try {
                 iME.start(stage);
             } catch (Exception ex) {
                 Logger.getLogger(InterfaceCreateCatalogue.class.getName()).log(Level.SEVERE, null, ex);
             }
         });
         
         btnshow.setOnAction(actionEvent -> {
            ImageIcon icon = new ImageIcon("info.png");
            JOptionPane.showMessageDialog(null, "Agrega las propiedades seguidas por comas", "Agregar Catálogo", 2, (Icon) icon);
         });

         
         
         
         
         
      }//funcionamiento
   
     private GridPane buildPane() throws FileNotFoundException {
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
    }//buildPane
     
     
      public void setupControls(GridPane pane) {
       
        btn_Agregar = ButtonAdd("Agregar", pane, 4);
        TxtName= TextFieldName(pane, 4);
        BtnSalida=ButtonExit("Regresar", pane, 4);
        LblName=LabelName("Nombre:", pane, 4);
        lblPropiedades=LabelDescripcion("Propiedades:", pane, 4);
        txtPropiedades=textPropiedades(pane, 4);
        btnshow=ButtonSinfo(pane, 5);
//        lblBussiness=LabelBussines("Posgrados SA", pane, 4);
      
    }//controladores
     
      private TextArea textPropiedades(GridPane pane, int row) {
          TextArea textField = new TextArea();
          pane.add(textField, 1, 21);
          textField.setFont(new Font("Indie Flower",16));
          GridPane.setMargin(textField, new Insets(10, row, 10, row));
        return textField;
    }//textField
     
      private TextField TextFieldName(GridPane pane, int row) {
          TextField Txt2 = new TextField();
          pane.add(Txt2, 1, 19);
          Txt2.setFont(new Font("Indie Flower",16));// determinar el tipo de letra y color radio button
          GridPane.setMargin(Txt2, new Insets(10, row, 10, row));
        return Txt2;
    }//textField
      
       private Label LabelName (String text, GridPane pane, int row) {
        Label lb = new Label(text);
        pane.add(lb,0, 19);
        lb.setFont(new Font("Footlight MT Light",16));
        GridPane.setMargin(lb, new Insets(10, 0, 10, 0));
        return lb;
    }//button
       
        private Label LabelBussines (String text, GridPane pane, int row) {
        Label lb = new Label(text);
        pane.add(lb,5, 0);
        lb.setFont(new Font("Footlight MT Light",16));
        GridPane.setMargin(lb, new Insets(10, 0, 10, 0));
        return lb;
    }//button
      
        private Label LabelDescripcion(String text, GridPane pane, int row) {
        Label lb = new Label(text);
        pane.add(lb,0, 21);
        lb.setFont(new Font("Footlight MT Light",16));
        GridPane.setMargin(lb, new Insets(10, 0, 10, 0));
        return lb;
    }//button
      
     private Button ButtonAdd(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button,1, 30);
        button.setFont(new Font("Indie Flower",14));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
        File files3 = new File("addo.png");
        Image images3 = new Image(files3.toURI().toString());
        ImageView ivs3 = new ImageView(images3);
         button.setGraphic(ivs3);
        GridPane.setHalignment(button, HPos.CENTER);
        GridPane.setMargin(button, new Insets(10, 0, 10, 0));
        return button;
    }//button
 
     
      private Button ButtonSinfo(GridPane pane, int row) {
        Button button = new Button();
        pane.add(button, 2,21);
        button.setFont(new Font("Indie Flower",14));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
//        GridPane.setHalignment(button, HPos.CENTER);
        File files3 = new File("info.png");
        Image images3 = new Image(files3.toURI().toString());
        ImageView ivs3 = new ImageView(images3);
        button.setGraphic(ivs3);
//        GridPane.setMargin(button, new Insets(10, 0, 10, 0));
        return button;
    }//button
     
     
     
     
      private Button ButtonExit(String label, GridPane pane, int row) {
        Button button = new Button(label);
         button.setFont(new Font("Indie Flower",16));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
        pane.add(button,5, 40);//-fila columna
        File files3 = new File("gou.png");
        Image images3 = new Image(files3.toURI().toString());
        ImageView ivs3 = new ImageView(images3);
         button.setGraphic(ivs3);
//        GridPane.setHalignment(button, HPos.CENTER);
        GridPane.setMargin(button, new Insets(10, row, 10, row));
        return button;
    }//button  
      
     private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }//showAlert
     
    private Scene createScene (Pane pane) {
         pane.setStyle("-fx-background-color:#37D8E3" );
         return new Scene (pane,900,900);
    }//scene
     
     private void AddProperties(String name,String properties) throws FileNotFoundException{
         File fileCatalogueTokens = new File("CatalogueTokens.txt");
         FileOutputStream fos = new FileOutputStream(fileCatalogueTokens,true);
                 PrintStream ps = new PrintStream(fos);
                 ps.println(name+"|"+properties);
     }//addProperties
     
     
     
 private void AddPropertiesALL(String properties) throws FileNotFoundException{
         File fileCatalogueTokensAll = new File("CatalogueTokensAll.txt");
         FileOutputStream fos = new FileOutputStream(fileCatalogueTokensAll,true);
                 PrintStream ps = new PrintStream(fos);
                 ps.println(properties);
     }//addProperties
}//end



















