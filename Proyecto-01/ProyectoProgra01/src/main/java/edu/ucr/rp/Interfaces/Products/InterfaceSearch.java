/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces.Products;

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
    ArrayList list = new ArrayList();
    ArrayList listComplete =new ArrayList();
    ArrayList listShow = new ArrayList();
    private TextArea txtShow;
    manteinFile f = new manteinFile();
  
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
            
            try {
                listComplete=f.getRegistersFileRegister();
                Registers re = f.GetposRegister(listComplete,cmbCatalogues.getValue()+"",txtSearching.getText());
                if (re==null) {
                    showAlert(Alert.AlertType.ERROR, stage,"Buscando producto", "El producto no se encontró , intente de nuevo");
                }else {
                    txtShow.setVisible(true);
                    txtShow.setText(re.toString());
                }
            } catch (IOException ex) {
                Logger.getLogger(InterfaceSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
                
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
         btn_exit= ButtonExit("Regresar", pane, 3);
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
        list=f.getRegistersName();
        cmbList.setValue("               Catálogos                 ");
        cmbList.setStyle(("-fx-font: 16px \"Footlight MT Light\""));
        pane.add(cmbList, 1,5);
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
            File files3 = new File("se.png");
            Image images3 = new Image(files3.toURI().toString());
            ImageView ivs3 = new ImageView(images3);
            button.setGraphic(ivs3);
            return button;
    }//button
      
       private Button ButtonExit(String label, GridPane pane, int row) {
          Button button = new Button(label);
          pane.add(button,2, 10);
          button.setFont(new Font("Indie Flower",14));// determinar el tipo de letra y color radio button
          button.setTextFill(Color.BLACK);
          button.setStyle("-fx-background-color: WHITE");
          File files3 = new File("return.png");
         Image images3 = new Image(files3.toURI().toString());
         ImageView ivs3 = new ImageView(images3);
         button.setGraphic(ivs3);
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
  
     
     
     
     
    
    
    
}//endSearch











