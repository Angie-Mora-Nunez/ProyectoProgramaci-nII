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
import java.awt.ComponentOrientation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

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
   
    Pane panes = new Pane();
     
     
      public VBox getVBox() throws FileNotFoundException {
       
        VBox vb_main = new VBox(); // VBox que contendrá las opciones del menú 
        
      
        VBox VB_Windows = new VBox(); // VBox para contener las demás ventanas sin alterar el principal 
        // barra de menú 
       
        
        
        MenuBar Mb_menu = new MenuBar();
        
        //1-ítem de crear catálogo
         Menu m_Create= new Menu("Agregar Producto");
          m_Create.setStyle("-fx-background-color: linear-gradient(#24B6C3, #FFFFFF);");
          File file1 = new File("adding.png");
          Image image1 = new Image(file1.toURI().toString());
          ImageView iv1 = new ImageView(image1);
          m_Create.setGraphic(iv1);
          
          
          
         
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
       m_ListCatalogs.setStyle("-fx-background-color: linear-gradient(#24B6C3, #FFFFFF);");
       // imagen
       File file2 = new File("searching.png");
       Image image2 = new Image(file2.toURI().toString());
       ImageView iv2 = new ImageView(image2);
       m_ListCatalogs.setGraphic(iv2);
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
       m_Modificate.setStyle("-fx-background-color: linear-gradient(#24B6C3, #FFFFFF);");
        File file3 = new File("modi.png");
       Image image3 = new Image(file3.toURI().toString());
       ImageView iv3 = new ImageView(image3);
        m_Modificate.setGraphic(iv3);
       
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
       m_ListProducts.setStyle("-fx-background-color: linear-gradient(#24B6C3, #FFFFFF);");
        File file4 = new File("view.png");
       Image image4 = new Image(file4.toURI().toString());
       ImageView iv4 = new ImageView(image4);
        m_ListProducts.setGraphic(iv4);
       
       
       
       
       // imagen
      
       InterfaceListingRegisters iLR = new InterfaceListingRegisters();
       MenuItem mI_ListProducts = new MenuItem("Ver");
       mI_ListProducts.setGraphic(iv1);
       // acción de los catálogos exixtentes 
        mI_ListProducts.setOnAction((event)->{
            VB_Windows.getChildren().clear(); // limpiar VBox
            try {
                iLR.start(stage);
            } catch (Exception ex) {
                Logger.getLogger(InterfaceProducts.class.getName()).log(Level.SEVERE, null, ex);
            }
       
       });
       m_ListProducts.getItems().addAll(mI_ListProducts);
       
       
          
       
       
       
      
       
       
     
       //7-ítem para salir
       Menu m_Exit= new Menu("Regresar");
       m_Exit.setStyle("-fx-background-color: linear-gradient(#24B6C3, #FFFFFF);");
        File file5 = new File("return.png");
       Image image5 = new Image(file5.toURI().toString());
       ImageView iv5 = new ImageView(image5);
        m_Exit.setGraphic(iv5); 
      // imagen 
       File file6 = new File("return.png");
       Image image6 = new Image(file6.toURI().toString());
       ImageView iv6 = new ImageView(image6);
       MenuItem mI_Exit = new MenuItem("Regresar");
       // acción para listar registros
       mI_Exit.setGraphic(iv6);
          InterfaceChooseWay iC = new InterfaceChooseWay();
      mI_Exit.setOnAction(actionEvent -> {
            try {
                iC.start(stage);
            } catch (Exception ex) {
                Logger.getLogger(InterfaceProducts.class.getName()).log(Level.SEVERE, null, ex);
            }
         });
       
         m_Exit.getItems().addAll(mI_Exit);
       
         Menu m_Exitf= new Menu("Salir"); 
         m_Exitf.setStyle("-fx-background-color: linear-gradient(#24B6C3, #FFFFFF);");
         File files4 = new File("salida.png");
         Image images4 = new Image(files4.toURI().toString());
         ImageView ivs4 = new ImageView(images4);
         m_Exitf.setGraphic(ivs4);
         
         MenuItem m_Ex = new MenuItem("Salir");
         m_Ex.setGraphic(ivs4);
         m_Ex.setOnAction(actionEvent -> {
             System.exit(0);});//cierro
         m_Exitf.getItems().addAll(m_Ex);
         
      
      
      
       
       
       // agregar el menú a la barra 
       Mb_menu.getMenus().addAll(m_Create,m_ListCatalogs,m_Modificate,m_ListProducts,m_Exit,m_Exitf);

        FileInputStream imageStream = new FileInputStream("pro.gif");
        Image imageS = new Image(imageStream);
        ImageView imagen = new ImageView(imageS);
         new Insets(0,10,0,10);
       
         
       // agregar barra al VBox
       vb_main.getChildren().addAll(Mb_menu, VB_Windows,imagen,panes);
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

     private Scene createScene (Pane pane) {
         pane.setStyle("-fx-background-color:#FFFEFE");
         return new Scene (pane,900,900);
    }//scene
     
}//InterfaceProducts

























