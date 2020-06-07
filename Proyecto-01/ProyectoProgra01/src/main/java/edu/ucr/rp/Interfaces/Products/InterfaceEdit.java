/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces.Products;

import edu.ucr.rp.Interfaces.*;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    ArrayList listNamesProducto = getRegisters();
    ArrayList listNamesCatalog = getAllNameCatalog();
    ArrayList listProperties = getAllProperties();
    ArrayList listDescription = getAllDescriptions();
    private Label lp_NameProduct;
    private TextField txt_nameProduct;
    private ComboBox cmbProperties;
    private Button btnShowInfo;
    private  TextField txtDescription;
    private Button btnSave;
    private Button btnConfirm;
    ArrayList PropertiesL = getAllProperties();
    ArrayList DescriptionL = getAllDescriptions();
    ArrayList arrayAux = new ArrayList();
    File propertiesDescription = new File("propertiesDescription.txt");
    File fileRegisTokens = new File("RegisTokens.txt");
    private Stage stage;
    int position; 
    ArrayList DescriptionsList = getDescriptions();
    private ComboBox cmbCatalogues;
    ArrayList list = getRegistersRegisters();
    ArrayList listComplete =getRegistersBusqueda();
    ArrayList listShow = new ArrayList();
    private TextArea txtShow;
  
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
           
            int i = SearchName(listNamesProducto,cmbSearchNames.getValue()+"");
            String Name =listNamesCatalog.get(i).toString();
            String NameProduct=listNamesProducto.get(i).toString();
            String Properties = listProperties.get(i).toString();
            String Description = listDescription.get(i).toString();
           
            
            System.out.println(Name);
            System.out.println(NameProduct);
            System.out.println(Properties);
            System.out.println(Description);
            
            PropertiesL=getObteinDat(Properties);
            DescriptionL=getObteinDat(Description);
            
            for (int j = 0; j <PropertiesL.size(); j++) {
              cmbProperties.getItems().addAll(PropertiesL.get(j));
            }//forcombo
         });//actionSeacrh
        
        btnShowInfo.setOnAction(actionEvent -> {
            position = SearchName(PropertiesL, cmbProperties.getValue()+"");
             String descriptionSet = DescriptionL.get(position).toString();
             txtDescription.setText(descriptionSet);
        });//showInfo
        
         ArrayList Modificate = new ArrayList();
         btnConfirm.setOnAction(actionEvent -> {
           Modificate.add(txtDescription.getText());
         });//actionConfirm
        
         btnSave.setOnAction(actionEvent -> {
             ArrayList addModificate = new ArrayList();
             String listConcaten ="";
             int pos = SearchName(DescriptionL,cmbSearchNames+"");
             
             for (int i = 0; i < Modificate.size(); i++) {
                 listConcaten+=Modificate.get(i).toString()+",";
                }//for
               
             for (int i = 0; i < DescriptionsList.size(); i++) {
                 if (i==pos) {
                     addModificate.add(listConcaten);
                 }else//endif
                    addModificate.add(DescriptionsList.get(i));
             }//for 
              try {
               FileOutputStream fos = new FileOutputStream(propertiesDescription);
                PrintStream ps = new PrintStream(fos);
                ps.println();
          
                FileOutputStream fosRe = new FileOutputStream(fileRegisTokens);
                PrintStream psRe = new PrintStream(fosRe);
                psRe.println();
                
                // limpiar el archivo 
                
                
           
                FileOutputStream fo2 = new FileOutputStream(propertiesDescription,true);
                 PrintStream ps2 = new PrintStream(fo2);
                 for (int i = 0; i < addModificate.size(); i++) {
                     ps2.println(addModificate.get(i).toString());
                }//for
                 
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InterfaceEdit.class.getName()).log(Level.SEVERE, null, ex);
            }//try/catch
                
             
             
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
         btn_exit= ButtonExit("Salir", pane, 5);
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
//        GridPane.setHalignment(button, HPos.CENTER);
//        GridPane.setMargin(button, new Insets(10, 0, 10, 0));
        return button;
    }//button
     
     private Button ButtonSave(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 1, 9);
        button.setFont(new Font("Indie Flower",14));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
//        GridPane.setHalignment(button, HPos.CENTER);
//        GridPane.setMargin(button, new Insets(10, 0, 10, 0));
        return button;
    }//button
     
     private Button ButtonConfirm(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 4, 7);
        button.setFont(new Font("Indie Flower",14));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
//        GridPane.setHalignment(button, HPos.CENTER);
//        GridPane.setMargin(button, new Insets(10, 0, 10, 0));
        return button;
    }//button
     
     private Button ButtonShowInfo(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 3, 7);
        button.setFont(new Font("Indie Flower",14));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
//        GridPane.setHalignment(button, HPos.CENTER);
//        GridPane.setMargin(button, new Insets(10, 0, 10, 0));
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
//          GridPane.setMargin(txtnameProduct, new Insets(10, 0, 10, 0));
        return txtnameProduct;
    }//TExtField
    private TextField TextFieldDescription(GridPane pane, int row) {
          TextField txtDescription = new TextField();
          pane.add(txtDescription,2,7);
          txtDescription.setFont(new Font("Footlight MT Light",16));
//          GridPane.setMargin(txtDescription, new Insets(10, 0, 10, 0));
        return txtDescription;
    }//TExtField
          
         private ComboBox comboBoxNames(GridPane pane, int row) {
            
        ComboBox cmbList = new ComboBox();
        cmbList.setValue("      Nombre de Productos:              ");
        cmbList.setStyle(("-fx-font: 16px \"Footlight MT Light\""));
        pane.add(cmbList, 1,5);
          for (int i = 0; i < listNamesProducto.size(); i++) {
              cmbList.getItems().addAll(listNamesProducto.get(i));
          }//for
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
//        GridPane.setHalignment(button, HPos.CENTER);
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
      
      
      public ArrayList getRegisters(){
     ArrayList list = new ArrayList();
      File nameProducts = new File("nameProducts.txt");
         try {
             FileInputStream fis = new FileInputStream(nameProducts);
             InputStreamReader isr= new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr);
            
             String actualRegister = br.readLine();
             
             while(actualRegister!=null){
             String nombre="";
             int controlaTokens=1;
             StringTokenizer sT = new StringTokenizer(actualRegister,",");
             
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
     
   }
   public ArrayList getAllProperties(){
      ArrayList list4 = new ArrayList();
  
        File propertiesFiles = new File("propertiesFiles.txt");
         try {
             FileInputStream fis = new FileInputStream(propertiesFiles);
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
            list4.add(properties);
             actualRegister=br.readLine();
             }//whileActualRegisters
             
         } catch (FileNotFoundException ex) {
             Logger.getLogger(InterfaceAddRegisters.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(InterfaceAddRegisters.class.getName()).log(Level.SEVERE, null, ex);
         }//try/catch
        
     return list4;
     
     }//getRegistersRegisters 
   
   public ArrayList getAllNameCatalog(){
      ArrayList list2 = new ArrayList();
  
        File nameCataloguesRegisters = new File("nameCataloguesRegisters.txt");
         try {
             FileInputStream fis = new FileInputStream(nameCataloguesRegisters);
             InputStreamReader isr= new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr);
            
             String actualRegister = br.readLine();
             
             while(actualRegister!=null){
             String properties="";
             int controlaTokens=1;
             StringTokenizer sT2 = new StringTokenizer(actualRegister,",");
            
            while(sT2.hasMoreTokens()){
               properties=sT2.nextToken();
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
   
     public ArrayList getAllDescriptions(){
      ArrayList list2 = new ArrayList();
  
        File propertiesDescription = new File("propertiesDescription.txt");
         try {
             FileInputStream fis = new FileInputStream(propertiesDescription);
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
     
      public ArrayList getDescriptions(){
      ArrayList list2 = new ArrayList();
  
        File propertiesDescription = new File("propertiesDescription.txt");
         try {
             FileInputStream fis = new FileInputStream(propertiesDescription);
             InputStreamReader isr= new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr);
            
             String actualRegister = br.readLine();
             
             while(actualRegister!=null){
              list2.add(actualRegister);
             actualRegister=br.readLine();
             }//whileActualRegisters
             
         } catch (FileNotFoundException ex) {
             Logger.getLogger(InterfaceAddRegisters.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(InterfaceAddRegisters.class.getName()).log(Level.SEVERE, null, ex);
         }//try/catch
        
     return list2;
     
     }//getRegistersRegisters 
   
      public int SearchName(ArrayList name , String nameSearch) {
            int output =0; 
                for (int i = 0; i <name.size(); i++) {
                    if (name.get(i).toString().equals(nameSearch)){ 
                          output=i;
                    }//if
                }//for
                  return output;
       }//searchName   
     
       public ArrayList getObteinDat(String properties){
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
      
       
        public ArrayList getRegistersRegisters(){
     ArrayList listas = new ArrayList();
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
             listas.add(sT.nextToken());
             actualRegister=br.readLine();
             }//whileActualRegisters
             
         } catch (FileNotFoundException ex) {
             Logger.getLogger(InterfaceAddRegisters.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(InterfaceAddRegisters.class.getName()).log(Level.SEVERE, null, ex);
         }//try/catch
        
     return listas;
     }//getRegistersRegisters 
     
     
      public ArrayList getRegistersBusqueda(){
      ArrayList list2 = new ArrayList();
       File fileRegisTokens = new File("RegisTokens.txt");
         try {
             FileInputStream fiso = new FileInputStream(fileRegisTokens);
             InputStreamReader isro= new InputStreamReader(fiso);
             BufferedReader bro = new BufferedReader(isro);
             String actualRegister = bro.readLine();
             
             while(actualRegister!=null){
             String Catalog="",name="",namePropie="";
             int controlaTokens=1;
             StringTokenizer sT = new StringTokenizer(actualRegister,"|");
             
             while(sT.hasMoreElements()){
             if(controlaTokens==1)
                 Catalog=sT.nextToken();
             else if(controlaTokens==2)
                 name=sT.nextToken();
             else if(controlaTokens==3)
                 namePropie=sT.nextToken();
             
             controlaTokens++;
             }//While
                 Registers registers = new Registers(Catalog, name, namePropie);
                 list2.add(registers);
             actualRegister=bro.readLine();
             }//whileActualRegisters
             
         } catch (FileNotFoundException ex) {
             Logger.getLogger(InterfaceAddRegisters.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(InterfaceAddRegisters.class.getName()).log(Level.SEVERE, null, ex);
         }//try/catch
        
     return list2;
     }//getRegistersRegisters 

      public Registers SearchName(ArrayList name , String nameSearch,ArrayList properties) {
            String output =""; 
            Registers registr=null;
            for (int j = 0; j < name.size(); j++) {
                for (int i = 0; i <properties.size(); i++) {
                    registr=(Registers)properties.get(i);
                    if (name.get(j).toString().equalsIgnoreCase(registr.getNameCatalogue())&& nameSearch.equalsIgnoreCase(registr.getNameProduct())){ 
                          return registr;
                    }//if
                }//for
                }//for
             return registr;
       }//searchName      
       
       
       
       
}//end









