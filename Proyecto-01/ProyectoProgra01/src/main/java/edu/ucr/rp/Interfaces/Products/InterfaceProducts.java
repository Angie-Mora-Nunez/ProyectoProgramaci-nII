/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces.Products;

import edu.ucr.rp.Interfaces.*;
import static edu.ucr.rp.Interfaces.UIConstaints.INPUT_WITH;
import static edu.ucr.rp.Interfaces.UIConstaints.INPUT_WITH_MAX;
import static edu.ucr.rp.Interfaces.UIConstaints.LABEL_WITH;
import static edu.ucr.rp.Interfaces.UIConstaints.LABEL_WITH_MAX;
import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.ImageIcon;

/**
 *
 * @author 
 * Marian Murillo Bonilla
 * Angie Mora Núñez
 */
public class InterfaceProducts extends Application {
    // elementos javafx a usar
    private Label lp;
    private Button generateButton;
    private Stage stage;
    
    
    // instancias a las clases 
    InterfacePrincipal ip = new InterfacePrincipal();
    InterfaceCreateCatalogue iC = new InterfaceCreateCatalogue();
    InterfaceListingCatalogs iLc = new InterfaceListingCatalogs();
   InterfaceAddRegisters iA = new InterfaceAddRegisters();
  
  
    
    public void start(Stage stage) throws Exception {
          this.stage=stage;
        title(stage);
        VBox pane = getVBox();
        
        stage.setScene(createScene(pane));
        stage.show();
    }//Start
     public void display() {
        launch();
    }//dispaly
    
    private void title(Stage stage) {
        stage.setTitle("Bienvenido a las acciones para los catálogos");
    }//title
     private void addHandlers() {
         
         
    }//eventos
    
      public VBox getVBox() {
       
        VBox vb_main = new VBox(); // VBox que contendrá las opciones del menú 
        
         
        VBox VB_Windows = new VBox(); // VBox para contener las demás ventanas sin alterar el principal 
        // barra de menú 
        
        MenuBar Mb_menu = new MenuBar();
      
        //1-ítem de crear catálogo
         Menu m_Create= new Menu("Agregar Producto");
          m_Create.setStyle("-fx-background-color: linear-gradient(#31E0EE, #C0EDF1);");
         
       // IMAGEN 
       File file = new File("create.png");
       Image image = new Image(file.toURI().toString());
       ImageView iv = new ImageView(image);
       
       MenuItem mI_Create = new MenuItem("Crear");
       mI_Create.setGraphic(iv);
       
      // acción de crear 
       m_Create.setOnAction(actionEvent -> {
           VB_Windows.getChildren().clear(); // limpiar VBox
            try {
                iA.start(stage);
            } catch (Exception ex) {
                Logger.getLogger(InterfaceProducts.class.getName()).log(Level.SEVERE, null, ex);
            }
         });
          
      m_Create.getItems().addAll(mI_Create); 
       
      //2- ítem de la lista de catálogos existentes 
       Menu m_ListCatalogs = new Menu("Buscar Producto");
       m_ListCatalogs.setStyle("-fx-background-color: linear-gradient(#31E0EE, #C0EDF1);");
       // imagen
       File file1 = new File("watch.png");
       Image image1 = new Image(file1.toURI().toString());
       ImageView iv1 = new ImageView(image1);
       InterfaceSearch iS = new InterfaceSearch();
       
       MenuItem mI_ListCatalogs = new MenuItem("Buscar");
       mI_ListCatalogs.setGraphic(iv1);
       // acción de los catálogos exixtentes 
        mI_ListCatalogs.setOnAction((event)->{
            VB_Windows.getChildren().clear(); // limpiar VBox
            try {
                iS.start(stage);
            } catch (Exception ex) {
                Logger.getLogger(InterfaceProducts.class.getName()).log(Level.SEVERE, null, ex);
            }
       
       });
       m_ListCatalogs.getItems().addAll(mI_ListCatalogs);
       
       
         //3- ítem de la lista de catálogos existentes 
       Menu m_Modificate = new Menu("Modificar Producto");
       m_Modificate.setStyle("-fx-background-color: linear-gradient(#31E0EE, #C0EDF1);");
       // imagen
    
       MenuItem mI_Modificate = new MenuItem("Modificar");
       mI_Modificate.setGraphic(iv1);
       // acción de los catálogos exixtentes 
       InterfaceEdit iE = new InterfaceEdit();
        mI_Modificate.setOnAction((event)->{
            VB_Windows.getChildren().clear(); // limpiar VBox
            try {
                iE.start(stage);
            } catch (Exception ex) {
                Logger.getLogger(InterfaceProducts.class.getName()).log(Level.SEVERE, null, ex);
            }
       
       });
       m_Modificate.getItems().addAll(mI_Modificate);
     
       
       //2- ítem de la lista de catálogos existentes 
       Menu m_ListProducts = new Menu("Lista de Productos");
       m_ListProducts.setStyle("-fx-background-color: linear-gradient(#31E0EE, #C0EDF1);");
       // imagen
      
       
       MenuItem mI_ListProducts = new MenuItem("Ver");
       mI_ListProducts.setGraphic(iv1);
       // acción de los catálogos exixtentes 
        mI_ListProducts.setOnAction((event)->{
            VB_Windows.getChildren().clear(); // limpiar VBox
            try {
                iLc.start(stage);
            } catch (Exception ex) {
                Logger.getLogger(InterfaceProducts.class.getName()).log(Level.SEVERE, null, ex);
            }
       
       });
       m_ListProducts.getItems().addAll(mI_ListProducts);
       
       
       
       
       
       
       
       
       
     
       //7-ítem para salir
       Menu m_Exit= new Menu("Salir");
       m_Exit.setStyle("-fx-background-color: linear-gradient(#31E0EE, #C0EDF1);");
      // imagen 
       File file6 = new File("exit.png");
       Image image6 = new Image(file6.toURI().toString());
       ImageView iv6 = new ImageView(image6);
       MenuItem mI_Exit = new MenuItem("Salir");
       // acción para listar registros
       mI_Exit.setGraphic(iv6);
      mI_Exit.setOnAction(actionEvent -> {
            try {
                ip.start(stage);
            } catch (Exception ex) {
                Logger.getLogger(InterfaceProducts.class.getName()).log(Level.SEVERE, null, ex);
            }
         });
       
      m_Exit.getItems().addAll(mI_Exit);
       
       
       
       // agregar el menú a la barra 
       Mb_menu.getMenus().addAll(m_Create,m_ListCatalogs,m_Modificate,m_ListProducts,m_Exit);

       
      
       // agregar barra al VBox
       vb_main.getChildren().addAll(Mb_menu, VB_Windows);
        return vb_main;
        
    }// end VBox
    
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
}

























