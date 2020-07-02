/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces.Products;

import edu.ucr.rp.Clients.Client;
import edu.ucr.rp.Clients.ClientModificatedRegisters;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 * Marian Murillo Bonilla
 * Angie Mora Núñez
 */
public class InterfaceEdit extends Application {
    private Label lp;
    private Button searchButton;
    private Button btn_exit;
    private TextField txtBuscar;
    private ComboBox cmbSearchNames;
    private Label lp_NameProduct;
    private TextField txt_nameProduct;
    private ComboBox cmbProperties;
    private Button btnShowInfo;
    private  TextField txtDescription;
    private Button btnSave;
    private Button btnConfirm;
    private Stage stage;
    int position; 
    private ArrayList registers = new ArrayList();
    manteinFile f = new manteinFile();
    ArrayList properties = new ArrayList();
    ArrayList Description = new ArrayList();
    String nameCatalog ="";
    ArrayList products = new ArrayList();
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
        stage.setTitle("Buscar en Catálogo");
    }//title
     private void addHandlers() {
        InterfaceProducts iP = new InterfaceProducts();
        
        searchButton.setOnAction(actionEvent -> {
            try {  
                registers = f.getRegistersFileRegister();
                
                products = f.GetposRegisterNames(registers,cmbSearchNames.getValue()+"");
               
                Registers re = f.GetposRegisterName(registers,cmbSearchNames.getValue()+"");
                nameCatalog=re.getNameCatalogue();
                 properties = f.getPropertiespos(re.getProperties());
                for (int i = 0; i < properties.size(); i++) {
                    cmbProperties.getItems().addAll(properties.get(i));
                }
                Description = f.getPropertiespos(re.getDescriptionProperties());
            } catch (IOException ex) {
                Logger.getLogger(InterfaceEdit.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            
         });//actionSeacrh
        
        btnShowInfo.setOnAction(actionEvent -> {
           int pos = f.GetposNameCatalog(properties,cmbProperties.getValue()+"");
            txtDescription.setText(Description.get(pos).toString());
            
        });//showInfo
        ArrayList propertiesText = new ArrayList();
        ArrayList DescriptionText = new ArrayList();
         btnConfirm.setOnAction(actionEvent -> {
           propertiesText.add(cmbProperties.getValue().toString());
           DescriptionText.add(txtDescription.getText());
             
         });//actionConfirm
        
         btnSave.setOnAction(actionEvent -> {
            String properties ="";
            String Description ="";
             for (int i = 0; i < propertiesText.size(); i++) {
                 properties+=propertiesText.get(i)+",";
             }
             for (int i = 0; i < DescriptionText.size(); i++) {
                 Description+=DescriptionText.get(i)+",";
             }
             
             Registers r = new Registers(nameCatalog,cmbSearchNames.getValue().toString(),properties,Description);
             products.add(r);
              File fi = new File("FileRegister.txt");
//              f.addOnFileNew(fi);
             String Register="";
//              executorService.submit(() -> {
//            ClientModificatedRegisters clientModificate = new ClientModificatedRegisters("127.0.0.1", 36978,r.toString());
//            
//        });
             
             
             for (int i = 0; i < products.size(); i++) {
              Registers re =(Registers)products.get(i);
              Register=re.getNameCatalogue()+"|"+re.getNameProduct()+"|"+re.getProperties()+"|"+re.getDescriptionProperties();
//              f.addOnFile(fi, Register);
//              executorService.submit(() -> {
//            ClientModificatedRegisters clientModificate = new ClientModificatedRegisters("127.0.0.1", 36978,re.toString());
//        });
             }
            
          
             
        });//actionSave
        
        btn_exit.setOnAction(actionEvent -> {
            try {
                iP.start(stage);
            } catch (Exception ex) {
                Logger.getLogger(InterfaceSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
         });//actionExit
         
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
       
         searchButton = ButtonSearch("Buscar en Catálogo", pane, 5);
         btn_exit= ButtonExit("Regresar", pane, 5);
         lp=labelCatalogue("Buscar Producto: ", pane, 5);
         cmbSearchNames=comboBoxNames(pane, 5);
         lp_NameProduct=labelProductName("Propiedades: ", pane,5);
         cmbProperties=comboBoxProperties(pane,5);
         txtDescription=TextFieldDescription(pane,5);
         btnShowInfo=ButtonShowInfo("Mostrar", pane, 5);
         btnSave=ButtonSave("Guardar", pane, 5);
         btnConfirm=ButtonConfirm("Confirmar", pane, 5);
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

        return button;
    }//button
     
     private Button ButtonSave(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 1, 9);
        button.setFont(new Font("Indie Flower",14));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
        File files3 = new File("save.png");
        Image images3 = new Image(files3.toURI().toString());
        ImageView ivs3 = new ImageView(images3);
        button.setGraphic(ivs3);

        return button;
    }//button
     
     private Button ButtonConfirm(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 4, 7);
        button.setFont(new Font("Indie Flower",14));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
        File files3 = new File("confirm.png");
        Image images3 = new Image(files3.toURI().toString());
        ImageView ivs3 = new ImageView(images3);
        button.setGraphic(ivs3);

        return button;
    }//button
     
     private Button ButtonShowInfo(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 3, 7);
        button.setFont(new Font("Indie Flower",14));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
        File files3 = new File("show.png");
        Image images3 = new Image(files3.toURI().toString());
        ImageView ivs3 = new ImageView(images3);
        button.setGraphic(ivs3);

        return button;
    }//button
     
     
    private Label labelCatalogue(String name,GridPane pane, int row) {
          Label label = new Label(name);
          pane.add(label, 0,5);
          label.setFont(new Font("Footlight MT Light",16));
//          GridPane.setMargin(label, new Insets(10, 0, 10, 0));
        return label;
    }//TExtField
         
    private Label labelProductName(String name,GridPane pane, int row) {
          Label label = new Label(name);
          pane.add(label, 0,7);
          label.setFont(new Font("Footlight MT Light",16));
          GridPane.setMargin(label, new Insets(10, 0, 10, 0));
        return label;
    }//Label
    
    private TextField TextFieldNameProduct(GridPane pane, int row) {
          TextField txtnameProduct = new TextField();
          pane.add(txtnameProduct,2,7);
          txtnameProduct.setFont(new Font("Footlight MT Light",16));
        return txtnameProduct;
    }//TExtField
    private TextField TextFieldDescription(GridPane pane, int row) {
          TextField txtDescription = new TextField();
          pane.add(txtDescription,2,7);
          txtDescription.setFont(new Font("Footlight MT Light",16));
         return txtDescription;
    }//TExtField
          
         private ComboBox comboBoxNames(GridPane pane, int row) {
          ArrayList nameProduct = new ArrayList();
             try {
            registers = f.getRegistersFileRegister();
            
            for (int i = 0; i < registers.size(); i++) {
               Registers r = (Registers) registers.get(i);
               nameProduct.add(r.getNameProduct());
            }
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        ComboBox cmbList = new ComboBox();
        cmbList.setValue("      Nombre de Productos:              ");
        cmbList.setStyle(("-fx-font: 16px \"Footlight MT Light\""));
        for (int i = 0; i < nameProduct.size(); i++) {
              cmbList.getItems().addAll(nameProduct.get(i));
          }//for
        
        pane.add(cmbList, 1,5);
          
        return cmbList;
    }//TExtField
         
     private ComboBox comboBoxProperties(GridPane pane, int row) { 
        ComboBox cmbList = new ComboBox();
        cmbList.setValue("      Propiedades:              ");
        cmbList.setStyle(("-fx-font: 16px \"Footlight MT Light\""));
        pane.add(cmbList, 1,7);
        return cmbList;
    }//TExtField
     
     
       private Button ButtonSearch(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 2, 5);
        button.setFont(new Font("Indie Flower",14));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
         File files3 = new File("se.png");
        Image images3 = new Image(files3.toURI().toString());
        ImageView ivs3 = new ImageView(images3);
         button.setGraphic(ivs3);
//        GridPane.setHalignment(button, HPos.CENTER);
//        GridPane.setMargin(button, new Insets(10, 0, 10, 0));
        return button;
    }//button
      
       private Button ButtonExit(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button,4, 10);
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
            
}//end

