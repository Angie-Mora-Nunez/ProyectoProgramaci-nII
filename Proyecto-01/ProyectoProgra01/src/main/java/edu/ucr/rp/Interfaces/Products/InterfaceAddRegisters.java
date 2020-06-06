/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces.Products;

import edu.ucr.rp.Interfaces.*;
import edu.ucr.rp.Interfaces.Logic.Catalogue;
import static edu.ucr.rp.Interfaces.UIConstaints.INPUT_WITH;
import static edu.ucr.rp.Interfaces.UIConstaints.INPUT_WITH_MAX;
import static edu.ucr.rp.Interfaces.UIConstaints.LABEL_WITH;
import static edu.ucr.rp.Interfaces.UIConstaints.LABEL_WITH_MAX;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Equipo
 */
public class InterfaceAddRegisters extends Application {
    private Label lp;
    private Button generateButton;
    private Button btn_exit;
    private Stage stage;
    private TextField txtBuscar;
    private Button btnSearch;
    private ComboBox cBListNames;
    ArrayList listAux = getRegistersRegisters();
    ArrayList listSi = new ArrayList();
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
         btnSearch.setOnAction(actionEvent -> {
             ArrayList e = getRegistersRegistersAll();
             
             for (int i = 0; i < e.size(); i++) {
//                 Catalogue cat =new Catalogue(listAux.get(i).toString(),e.get(i).toString());
             listSi.add(new Catalogue(listAux.get(i).toString(),e.get(i).toString()));
             }//for
             System.out.println(listSi.toString());
//             ArrayList j=cataloguesComplete(listAux, e);
//             System.out.println(j.toString());
//             


          
         
         
         });
         
         InterfaceProducts iP = new InterfaceProducts();
         btn_exit.setOnAction(actionEvent -> {
                try {
                    iP.start(stage);
                } catch (Exception ex) {
                    Logger.getLogger(InterfaceAddRegisters.class.getName()).log(Level.SEVERE, null, ex);
                }//try/catch
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
     
     private void setupControls(GridPane pane) {
       
        generateButton = ButtonAgregar("Agregar Registros", pane, 5);
        btn_exit= buildGenerateButton("Salir", pane, 5);
        cBListNames=comboBoxNames(pane, 5);
        btnSearch=ButtonBuscar("Buscar Catalogo", pane, 5);
    }//Controladores

      private ComboBox comboBoxNames(GridPane pane, int row) {
        ComboBox cmbList = new ComboBox();
        pane.add(cmbList, 0,19);
          for (int i = 0; i < listAux.size(); i++) {
              cmbList.getItems().addAll(listAux.get(i));
          }//for
        return cmbList;
    }//TExtField
     
     
     
     private Button buildGenerateButton(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 5,25);
        GridPane.setHalignment(button, HPos.CENTER);
        GridPane.setMargin(button, new Insets(20, 0, 20, 0));
        return button;
    }//button
     
     
      private Button ButtonAgregar(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 4,25);
        GridPane.setHalignment(button, HPos.CENTER);
        GridPane.setMargin(button, new Insets(20, 0, 20, 0));
        return button;
    }//button
     
     
     private Button ButtonBuscar(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 2, 19);
        GridPane.setHalignment(button, HPos.CENTER);
        GridPane.setMargin(button, new Insets(20, 0, 20, 0));
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
      
//      private ArrayList cataloguesComplete(ArrayList arrayList1,ArrayList arrayList2){
//      ArrayList arrayListComplete = new ArrayList();
//          for (int i = 0; i < arrayList1.size(); i++) {
//              Catalogue catalogue1= new Catalogue(arrayList1.get(i).toString(), arrayList2.get(i).toString());
//              arrayListComplete.add(catalogue1);
//          }//FOR PRIMERA LIST
//    return arrayListComplete; 
//      }//cataloguesComplete
      
      
      
      
      
}//InterfaceAddRegister









