/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces.Products;

import edu.ucr.rp.Clients.ClientListingRegisters;
import edu.ucr.rp.Clients.ClientRegisters;
import edu.ucr.rp.Interfaces.*;
import edu.ucr.rp.Interfaces.Logic.Registers;
import edu.ucr.rp.Interfaces.Logic.manteinFile;
import static edu.ucr.rp.Interfaces.UIConstaints.INPUT_WITH;
import static edu.ucr.rp.Interfaces.UIConstaints.INPUT_WITH_MAX;
import static edu.ucr.rp.Interfaces.UIConstaints.LABEL_WITH;
import static edu.ucr.rp.Interfaces.UIConstaints.LABEL_WITH_MAX;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 * Marian Murillo Bonilla
 * Angie Mora Núñez
 */
public class InterfaceListingRegisters extends Application {
    private Label lp;
    private Button generateButton;
    private Button btn_exit;
    TableView<Registers> tvRegisters= new TableView<>();
    private Button btnShow;
    ExecutorService executorService = Executors.newCachedThreadPool();
    TextArea txtArea;
    private Label labelMostrar;
    
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
         InterfaceProducts iu = new InterfaceProducts();
         btn_exit.setOnAction(actionEvent -> {
             try {
                 iu.start(stage);
             } catch (Exception ex) {
                 Logger.getLogger(InterfaceListingCatalogs.class.getName()).log(Level.SEVERE, null, ex);
             }
         });
         
         
         
         btnShow.setOnAction(actionEvent -> {
             
             
             
            executorService.submit(() -> {
              
                ClientListingRegisters clientListing = new ClientListingRegisters("127.0.0.1", 14563);

              txtArea.setVisible(true);
              txtArea.setText(clientListing.getRegister());
             });//executorService
             
              
             
         });//btnshow
         
         
         
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

     private void setupControls(GridPane pane) throws IOException {
         
         btn_exit= buildGenerateButton("Regresar", pane, 5);
//         tvRegisters=buildTextAreaShow(pane, 5);
         btnShow=buildGenerateButtonShow("Mostrar", pane, 5);
         txtArea=Show("", pane,5);
         txtArea.setVisible(false);
         labelMostrar=ShowRegisters("Mostrar Registros", pane, 5);
    }//Controladores
     
     
       private TableView buildTextAreaShow(GridPane pane, int row) throws IOException {
    
        TableView<Registers> tvtArea= new TableView<>();
         // crear columnas 
        TableColumn tc_NameCatalog = new TableColumn("Nombre Catálogo");
        tc_NameCatalog.setCellValueFactory(new PropertyValueFactory("nameCatalogue"));
        
        TableColumn tc_NameProduct = new TableColumn("Nombre del producto");
        tc_NameProduct.setCellValueFactory(new PropertyValueFactory("nameProduct"));
        
        TableColumn tc_Properties = new TableColumn("Propiedades");
        tc_Properties.setCellValueFactory(new PropertyValueFactory("properties"));
        
        TableColumn tc_Description = new TableColumn("Descripción");
        tc_Description.setCellValueFactory(new PropertyValueFactory("descriptionProperties"));
         // mostrar columnas en la tabla 
        tvtArea.setItems(getDataFile());
        tvtArea.getColumns().addAll(tc_NameCatalog,tc_NameProduct,tc_Properties,tc_Description);
        pane.add(tvtArea, 0, 1);
        GridPane.setHalignment(tvtArea, HPos.CENTER);
        return tvtArea;
        
        
    }//TExtField
     
       
     private Button buildGenerateButton(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 4, 0);
        GridPane.setHalignment(button, HPos.CENTER);
         File files3 = new File("return.png");
        Image images3 = new Image(files3.toURI().toString());
        ImageView ivs3 = new ImageView(images3);
         button.setGraphic(ivs3);
        GridPane.setMargin(button, new Insets(20, 0, 20, 0));
        return button;
    }//button
     
     
     private Button buildGenerateButtonShow(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 3, 0);
        GridPane.setHalignment(button, HPos.CENTER);
         File files3 = new File("se.png");
        Image images3 = new Image(files3.toURI().toString());
        ImageView ivs3 = new ImageView(images3);
         button.setGraphic(ivs3);
        GridPane.setMargin(button, new Insets(20, 0, 20, 0));
        return button;
    }//button
     
     private TextArea Show(String label, GridPane pane, int row) {
        TextArea txt = new TextArea(label);
        pane.add(txt, 2, 2);
        GridPane.setHalignment(txt, HPos.CENTER);
        GridPane.setMargin(txt, new Insets(20, 0, 20, 0));
        return txt;
    }//button
     
     private Label ShowRegisters(String label, GridPane pane, int row) {
        Label txt = new Label(label);
        pane.add(txt, 1, 0);
        GridPane.setHalignment(txt, HPos.CENTER);
        GridPane.setMargin(txt, new Insets(20, 0, 20, 0));
        return txt;
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

 
    private ObservableList<Registers> getDataFile() throws IOException {
        
        manteinFile mf = new manteinFile();
        ArrayList temp = mf.getRegistersFileRegister();
        ArrayList aL= new ArrayList();
        
        for (int i = 0; i < temp.size(); i++) {
             aL.add(temp.get(i));
        }
        ObservableList<Registers>oL_dataCatalog =FXCollections.observableArrayList(aL); 
        return oL_dataCatalog;
    }//
   
    
    
    
    
    
}//end













