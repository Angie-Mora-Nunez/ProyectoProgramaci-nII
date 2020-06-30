/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces;

import edu.ucr.rp.Clients.ClientListingCatalogs;
import edu.ucr.rp.Clients.ClientListingRegisters;
import edu.ucr.rp.Interfaces.Logic.Catalog;
import edu.ucr.rp.Interfaces.Logic.manteinFile;
import static edu.ucr.rp.Interfaces.UIConstaints.INPUT_WITH;
import static edu.ucr.rp.Interfaces.UIConstaints.INPUT_WITH_MAX;
import static edu.ucr.rp.Interfaces.UIConstaints.LABEL_WITH;
import static edu.ucr.rp.Interfaces.UIConstaints.LABEL_WITH_MAX;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    TableView<Catalog> tvCatalogs= new TableView<>();
    private Stage stage;
    ExecutorService executorService = Executors.newCachedThreadPool();
  
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
         });//salida accion
         
         generateButton.setOnAction(actionEvent -> {
              executorService.submit(() -> {
              
                ClientListingCatalogs clientListing = new ClientListingCatalogs("127.0.0.1", 24987);

              txtShow.setVisible(true);
              txtShow.setText(clientListing.getRegister());
             });//executorService
             
             
             
             
             
         });//mostrar accion
         
         
         
         
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
         
    
//        tvCatalogs=buildTableView(pane,6);
        txtShow=buildTextAreaShow("", pane, 5);
        btn_exit= buildGenerateButton("Regresar", pane, 5);
        lp=buildLabelAreaShow("Mostrar Catalogos", pane, 5);
        generateButton=buildGenerateButtonShow("Mostrar", pane, 5);
        txtShow.setVisible(false);
         
    }//Controladores
     
     
       private TextArea buildTextAreaShow(String text,GridPane pane, int row) {
        TextArea txtArea = new TextArea(text);
        pane.add(txtArea, 2, 2);
        TextField textField = new TextField();
        GridPane.setHalignment(txtArea, HPos.CENTER);
        return txtArea;
    }//TExtField
     
        private Label buildLabelAreaShow(String text,GridPane pane, int row) {
        Label txtArea = new Label(text);
        pane.add(txtArea, 0, 0);
        TextField textField = new TextField();
        GridPane.setHalignment(txtArea, HPos.CENTER);
        return txtArea;
    }//TExtField
       
       
       
     private Button buildGenerateButton(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 4, 0);
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
     
     
     
     
      private Button buildGenerateButtonShow(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 3, 0);
        button.setFont(new Font("Indie Flower",16));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
        File files3 = new File("se.png");
        Image images3 = new Image(files3.toURI().toString());
        ImageView ivs3 = new ImageView(images3);
        button.setGraphic(ivs3);
        GridPane.setHalignment(button, HPos.CENTER);
        GridPane.setMargin(button, new Insets(20, 0, 20, 0));
        return button;
    }//button
     
     
     
     
     
     
     
     
     
     
     
      private TableView buildTableView(GridPane pane, int row) throws IOException {
        TableView<Catalog> tvCatalog= new TableView<>();
         // crear columnas 
        TableColumn tc_NameCatalog = new TableColumn("Nombre");
        tc_NameCatalog.setCellValueFactory(new PropertyValueFactory("nameCatalog"));
        
        TableColumn tc_Properties = new TableColumn("Propiedades");
        tc_Properties.setCellValueFactory(new PropertyValueFactory("properties"));
        
         // mostrar columnas en la tabla 
        tvCatalog.setItems(getDataFile());
        tvCatalog.getColumns().addAll(tc_NameCatalog,tc_Properties);
        
        pane.add(tvCatalog, 0, 1);
        TextField textField = new TextField();
        GridPane.setHalignment(tvCatalog, HPos.CENTER);
        return tvCatalog;
    }//TExtField
    
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

    private ObservableList<Catalog> getDataFile() throws IOException {
        
        manteinFile mf = new manteinFile();
        ArrayList temp = mf.getRegistersFileCatalog();
        ArrayList aL= new ArrayList();
        
        for (int i = 0; i < temp.size(); i++) {
             aL.add(temp.get(i));
        }
        ObservableList<Catalog>oL_dataCatalog =FXCollections.observableArrayList(aL); 
        return oL_dataCatalog;
    }
    
    
}//end













