/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces.Products;

import edu.ucr.rp.Interfaces.*;
import edu.ucr.rp.Interfaces.Logic.Catalog;
import edu.ucr.rp.Interfaces.Logic.Registers;
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
 * @authores 
 * Marian Murillo Bonilla
 * Angie Mora Núñez
 * 
 */
public class InterfaceAddRegisters extends Application {
    private Label labelAnunced;
    private Label labelnameProduct;
    private Label labelCatalogues;
    private Button ButtonSave;
    private Button btn_exit;
    private Stage stage;
    private TextField txtNameProduct;
    private Button btnSearch;
    private ComboBox cBListNames;
    private ComboBox cBProperties;
    private TextField txtAgregarPropiedad;
    private Button btnAddProperties;
    private Label lblNameCatalog;
    private TextField txtmos;
    private Label lblEdit;
    private Button btninfo;
    ArrayList listAux = getRegistersRegisters();
    ArrayList propertiesList = getRegistersRegistersAll();
    ArrayList listSi = new ArrayList();
    ArrayList propetiesCombo=new ArrayList();
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
        stage.setTitle("Agregar Registros");
    }//title
    
     private void addHandlers() {//funcionalidad
         ArrayList nameProduct = new ArrayList();
         ArrayList name = new ArrayList();
         ArrayList propertiesAdd = new ArrayList();
         ArrayList description = new ArrayList();
         btnSearch.setOnAction(actionEvent -> {
             for (int i = 0; i < propertiesList.size(); i++) {
             listSi.add(new Catalog(listAux.get(i).toString(),propertiesList.get(i).toString()));
             }//for

              propetiesCombo = getObteinProperties(SearchName(listAux,cBListNames.getValue()+"", propertiesList));
              for (int i = 0; i < propetiesCombo.size(); i++) {
              cBProperties.getItems().addAll(propetiesCombo.get(i));
              }//for
              
              labelCatalogues.setVisible(false);
              btnSearch.setVisible(false);
              cBListNames.setVisible(false);
              
             cBProperties.setVisible(true);
             labelnameProduct.setVisible(true);
             txtNameProduct.setVisible(true);
             btnAddProperties.setVisible(true);
             txtAgregarPropiedad.setVisible(true);
             txtNameProduct.setEditable(true);
             
             
             name.add(cBListNames.getValue()+"");
              txtmos.setVisible(true);
              txtmos.setEditable(false);
              txtmos.setStyle("-fx-background: color \"Blue\"");
              txtmos.setText(cBListNames.getValue()+"");
              lblEdit.setVisible(true);
              labelAnunced.setVisible(false);
             
         });//acción search
         
         btnAddProperties.setOnAction(actionEvent -> {
            
            txtNameProduct.setEditable(false);
            nameProduct.add(txtNameProduct.getText());
            propertiesAdd.add(cBProperties.getValue()+"");
            description.add(txtAgregarPropiedad.getText()); 
            ImageIcon icon = new ImageIcon("add.png");
            JOptionPane.showMessageDialog(null, "Se ha agredado la propiedad", "Agregar Producto", 2, (Icon) icon);
            txtNameProduct.clear();
         });//accion boton properties
         
         ButtonSave.setOnAction(actionEvent -> {
             ArrayList showInfo = new ArrayList();
             labelCatalogues.setVisible(true);
             btnSearch.setVisible(true);
             cBListNames.setVisible(true);
             cBProperties.setVisible(false);
             labelnameProduct.setVisible(false);
             txtNameProduct.setVisible(false);
             btnAddProperties.setVisible(false);
             txtAgregarPropiedad.setVisible(false);
             txtNameProduct.setEditable(false);
             String nameCatalogues = name.get(0).toString();
             String namesProducts = nameProduct.get(0).toString();
             String propertiesFile="";
             String descriptions ="";
             String show ="";
             String showAll="";
             
             for (int i = 0; i < propertiesAdd.size(); i++) {
                propertiesFile+=propertiesAdd.get(i).toString()+",";
             }//for
             
              for (int i = 0; i < description.size(); i++) {
                descriptions+=description.get(i).toString()+",";
             }//for
              
               for (int i = 0; i < description.size(); i++) {
                show+=propertiesAdd.get(i).toString()+":"+" "+description.get(i).toString()+",";
             }//for
               
               showAll="{ Nombre del catálogo :"+" "+nameCatalogues+" "+"Nombre de producto :"+" "+namesProducts+" "+" "+"Propiedades: "+show+"}";
               
             File nameCataloguesRegisters = new File("nameCataloguesRegisters.txt");
             File nameProducts = new File("nameProducts.txt");
             File propertiesFiles = new File("propertiesFiles.txt");
             File propertiesDescription = new File("propertiesDescription.txt");
             File showRegisters = new File("ShowRegisters.txt");
             showInfo.add(showAll);
             
              try {
                  
                 FileOutputStream fos = new FileOutputStream(nameCataloguesRegisters,true);
                 PrintStream ps = new PrintStream(fos);
                 ps.println(nameCatalogues);
                
                 FileOutputStream fo = new FileOutputStream(nameProducts,true);
                 PrintStream psi = new PrintStream(fo);
                 psi.println(namesProducts);
          
                 FileOutputStream fo1 = new FileOutputStream(propertiesFiles,true);
                 PrintStream ps1 = new PrintStream(fo1);
                 ps1.println(propertiesFile);
          
                 FileOutputStream fo2 = new FileOutputStream(propertiesDescription,true);
                 PrintStream ps2 = new PrintStream(fo2);
                 ps2.println(descriptions);
                 
                 FileOutputStream fo3 = new FileOutputStream(showRegisters,true);
                 PrintStream ps3 = new PrintStream(fo3);
                 ps3.println(showInfo.toString());
                 AddBuilderTokens(nameCatalogues, namesProducts, show);
                 ImageIcon icon = new ImageIcon("confirm.png");
            JOptionPane.showMessageDialog(null, "Se ha agredado el producto", "Agregar Producto", 2, (Icon) icon);
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(InterfaceCreateCatalogue.class.getName()).log(Level.SEVERE, null, ex);
             }//try/catch
             nameProduct.clear();
             propertiesAdd.clear();
             description.clear();
             name.clear();
              
         });//accionGuardar
         
         
             
         InterfaceProducts iP = new InterfaceProducts();
         btn_exit.setOnAction(actionEvent -> {
            
                try {
                    iP.start(stage);
                } catch (Exception ex) {
                    Logger.getLogger(InterfaceAddRegisters.class.getName()).log(Level.SEVERE, null, ex);
                }//try/catch
         });//btn salir
    }//eventos
    
     private GridPane buildPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 10,10, 10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints columnOneConstraints = new ColumnConstraints(LABEL_WITH, LABEL_WITH, LABEL_WITH_MAX);
//        columnOneConstraints.setHalignment(HPos.RIGHT);
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(INPUT_WITH, INPUT_WITH, INPUT_WITH_MAX);
//        columnTwoConstrains.setHgrow(Priority.ALWAYS);
        return gridPane; 
    }//GridPane
     
     private void setupControls(GridPane pane) {
        ButtonSave = ButtonSave("Guardar", pane, 5);
        btn_exit= buildGenerateButton("Regresar", pane, 5);
        cBListNames=comboBoxNames(pane, 5);
        btnSearch=ButtonSearch("Buscar Catalogo", pane, 5);
        cBProperties=comboBoxProperties(pane, 5);
        cBProperties.setVisible(false);
        labelAnunced=labelName("Agregar productos",pane, 5);
        labelCatalogues=labelCatalogue("Catálogos disponibles: ", pane, 5);
        labelnameProduct=labelNameProduct("Nombre de producto", pane, 5);
        labelnameProduct.setVisible(false);
        txtNameProduct=TextFieldNameProduct(pane, 5);
        txtNameProduct.setVisible(false);
        btnAddProperties=ButtonAddProperties("Agregar propiedades", pane, 5);
        btnAddProperties.setVisible(false);
        txtAgregarPropiedad=TextFieldAddProduct(pane, 5);
        txtAgregarPropiedad.setVisible(false);
        txtmos=TextFieldada(pane, 5);
        txtmos.setVisible(false);
        lblEdit=labelEdit("Producto a agregar:", pane, 5);
        lblEdit.setVisible(false);
    }//Controladores

      private ComboBox comboBoxNames(GridPane pane, int row) {
        ComboBox cmbList = new ComboBox();
        cmbList.setValue("               Catálogos                 ");
        cmbList.setStyle(("-fx-font: 16px \"David Libre\""));
        pane.add(cmbList, 1,5);
          for (int i = 0; i < listAux.size(); i++) {
              cmbList.getItems().addAll(listAux.get(i));
          }//for
        return cmbList;
    }//TExtField
      
       private Label labelName(String name,GridPane pane, int row) {
          Label label = new Label(name);
          pane.add(label, 1,3);
          label.setFont(new Font("David Libre",16));
          GridPane.setMargin(label, new Insets(10, 0, 10, 0));
        return label;
    }//TExtField
       
       
        private Label labelEdit(String name,GridPane pane, int row) {
          Label label = new Label(name);
          pane.add(label, 0,0);
          label.setFont(new Font("David Libre",16));
          File files3 = new File("herra.png");
         Image images3 = new Image(files3.toURI().toString());
         ImageView ivs3 = new ImageView(images3);
         label.setGraphic(ivs3);
         
        return label;
    }//TExtField
   
       
       private Label labelNameProduct(String name,GridPane pane, int row) {
          Label label = new Label(name);
          pane.add(label, 0,6);
          label.setFont(new Font("David Libre",16));
          GridPane.setMargin(label, new Insets(10, 0, 10, 0));
        return label;
    }//TExtField
       
        private TextField TextFieldNameProduct(GridPane pane, int row) {
          TextField txtnameProduct = new TextField();
          pane.add(txtnameProduct, 1,6);
          txtnameProduct.setFont(new Font("David Libre",16));
          GridPane.setMargin(txtnameProduct, new Insets(10, 0, 10, 0));
        return txtnameProduct;
    }//TExtField
        
        
        
         private TextField TextFieldada(GridPane pane, int row) {
          TextField txtnameProduct = new TextField();
          pane.add(txtnameProduct, 1,0);
          txtnameProduct.setFont(new Font("David Libre",16));
          GridPane.setMargin(txtnameProduct, new Insets(10, 0, 10, 0));
        return txtnameProduct;
    }//TExtField
        
        private TextField TextFieldAddProduct(GridPane pane, int row) {
          TextField txtnameProduct = new TextField();
          pane.add(txtnameProduct, 1,7);
          txtnameProduct.setFont(new Font("David Libre",16));
          GridPane.setMargin(txtnameProduct, new Insets(10, 0, 10, 0));
        return txtnameProduct;
    }//TExtField
   
        private Label labelCatalogue(String name,GridPane pane, int row) {
          Label label = new Label(name);
          pane.add(label, 0,5);
          label.setFont(new Font("David Libre",16));
//          GridPane.setMargin(label, new Insets(10, 0, 10, 0));
        return label;
    }//TExtField
       
      private ComboBox comboBoxProperties(GridPane pane, int row) {
          ComboBox cmbList = new ComboBox();
          cmbList.setValue("               Propiedades                 ");
          cmbList.setStyle(("-fx-font: 16px \"David Libre\""));
          pane.add(cmbList, 0,7);
        return cmbList;
    }//TExtField
     
     private Button buildGenerateButton(String label, GridPane pane, int row) {
          Button button = new Button(label);
          pane.add(button, 2,25);
//          GridPane.setHalignment(button, HPos.CENTER);
          button.setFont(new Font("Indie Flower",14));// determinar el tipo de letra y color radio button
          button.setTextFill(Color.BLACK);
          button.setStyle("-fx-background-color: WHITE");
//          GridPane.setHalignment(button, HPos.CENTER);
          File files3 = new File("return.png");
         Image images3 = new Image(files3.toURI().toString());
         ImageView ivs3 = new ImageView(images3);
         button.setGraphic(ivs3);
//          GridPane.setMargin(button, new Insets(10, 0, 10, 0));
         
        return button;
    }//button
     
      private Button ButtonSave(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 1,25);
        button.setFont(new Font("Indie Flower",14));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
//        GridPane.setHalignment(button, HPos.CENTER);
        File files3 = new File("save.png");
        Image images3 = new Image(files3.toURI().toString());
        ImageView ivs3 = new ImageView(images3);
        button.setGraphic(ivs3);
//        GridPane.setMargin(button, new Insets(10, 0, 10, 0));
        return button;
    }//button
      
      
     
      private Button ButtonAddProperties(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 2,7);
        button.setFont(new Font("Indie Flower",14));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
//        GridPane.setHalignment(button, HPos.CENTER);
        File files3 = new File("add.png");
        Image images3 = new Image(files3.toURI().toString());
        ImageView ivs3 = new ImageView(images3);
        button.setGraphic(ivs3);
//        GridPane.setMargin(button, new Insets(10, 0, 10, 0));
        return button;
    }//button
      
      
     private Button ButtonSearch(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 2, 5);
        button.setFont(new Font("Indie Flower",14));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
//        GridPane.setHalignment(button, HPos.CENTER);
        File files3 = new File("se.png");
        Image images3 = new Image(files3.toURI().toString());
        ImageView ivs3 = new ImageView(images3);
        button.setGraphic(ivs3);
//        GridPane.setMargin(button, new Insets(10, 0, 10, 0));
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
      
     public ArrayList getRegistersRegisters(){
     ArrayList list = new ArrayList();
      File fileCatalogueTokens = new File("CatalogueTokens.txt");
         try {
             FileInputStream fis = new FileInputStream(fileCatalogueTokens);
             InputStreamReader isr= new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr);
            
             String actualRegister = br.readLine();
             
             while(actualRegister!=null){
             String nombre="";
             int controlaTokens=1;
             StringTokenizer sT = new StringTokenizer(actualRegister,"|");
             
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
      
      
      public ArrayList getRegistersRegistersAll(){
      ArrayList list2 = new ArrayList();
  
       File fileCatalogueTokensAll = new File("CatalogueTokensAll.txt");
         try {
             FileInputStream fis = new FileInputStream(fileCatalogueTokensAll);
             InputStreamReader isr= new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr);
            
             String actualRegister = br.readLine();
             
             while(actualRegister!=null){
             String properties="";
             int controlaTokens=1;
             StringTokenizer sT2 = new StringTokenizer(actualRegister,",");
            
            while(sT2.hasMoreTokens()){
               properties+=sT2.nextToken()+",";
               controlaTokens++;
            }//whilecontrolaTokens
            list2.add(properties);
             actualRegister=br.readLine();
             }//whileActualRegisters
             
         } catch (FileNotFoundException ex) {
             Logger.getLogger(InterfaceAddRegisters.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(InterfaceAddRegisters.class.getName()).log(Level.SEVERE, null, ex);
         }//try/catch
        
     return list2;
     }//getRegistersRegisters 
      
        public String SearchName(ArrayList name , String nameSearch,ArrayList properties) {
            String output =""; 
                for (int i = 0; i <name.size(); i++) {
                    if (name.get(i).toString().equals(nameSearch)){ 
                          output=(String) properties.get(i);
                    }//if
                }//for
                  return output;
       }//searchName      
      
      
         public ArrayList getObteinProperties(String properties){
         ArrayList listgetObtein = new ArrayList();
             String propertie="";
             int controlaTokens=1;
             StringTokenizer sT2 = new StringTokenizer(properties,",");
            
            while(sT2.hasMoreTokens()){
               propertie=sT2.nextToken();
               controlaTokens++;
               listgetObtein.add(propertie);
            }//whilecontrolaTokens
      
          return listgetObtein;
     }//getRegistersRegisters 
      
      private void AddBuilderTokens(String Catalog,String name,String namePropie ) throws FileNotFoundException{
         File fileRegisTokens = new File("RegisTokens.txt");
         FileOutputStream fos = new FileOutputStream(fileRegisTokens,true);
                 PrintStream ps = new PrintStream(fos);
                 ps.println(Catalog+"|"+name+"|"+namePropie);
     }//addProperties  
        
        
        
      
      
}//InterfaceAddRegister









