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
import java.time.LocalDate;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
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
public class InterfaceCreate extends Application {
   
    private TextField TxtName;
    private TextArea txtPropiedades;
    private Stage stage;
    private Label LblName;
    private Label lblPropiedades;
    private Button BtnSalida;
    private Button btn_Agregar;
    File insert= new File("catalogue.txt");
    
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
          String Properties[]={TxtName.getText(),txtPropiedades.getText()};
            String guardar[] ={TxtName.getText(),txtPropiedades.getText()};
          

             try {

            AgregarPuestos(guardar[0], guardar[1]);
    
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(InterfaceCreate.class.getName()).log(Level.SEVERE, null, ex);
             }
          
             try{
          FileOutputStream fos = new FileOutputStream(insert,true);
          PrintStream ps = new PrintStream(fos);
          ps.println(new Catalogue(TxtName.getText(),txtPropiedades.getText()));



           FileOutputStream fosi = new FileOutputStream(insert,true);
          PrintStream psi = new PrintStream(fosi);
          psi.println();
      }//try
            catch(FileNotFoundException fnfe){
      JOptionPane.showMessageDialog(null, "Problemas con el archivo"+fnfe);
      }//catch    
           
         });
     
        InterfaceUsers iME = new InterfaceUsers();
         BtnSalida.setOnAction(actionEvent -> {
             try {
                 iME.start(stage);
             } catch (Exception ex) {
                 Logger.getLogger(InterfaceCreate.class.getName()).log(Level.SEVERE, null, ex);
             }
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
     
     
      private void setupControls(GridPane pane) {
       
        btn_Agregar = buildGenerateButton("Agregar", pane, 5);
        TxtName= TextFieldName(pane, 5);
        BtnSalida=buildGenerateButton3("Regresar", pane, 5);
        LblName=LabelName("Nombre:", pane, 5);
        lblPropiedades=LabelDescripcion("Propiedades:", pane, 5);
        txtPropiedades=textPropiedades(pane, 5);
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
       
      
          private Label LabelDescripcion(String text, GridPane pane, int row) {
        Label lb = new Label(text);
        pane.add(lb,0, 21);
        lb.setFont(new Font("Footlight MT Light",16));
        GridPane.setMargin(lb, new Insets(10, 0, 10, 0));
        return lb;
    }//button
      
     private Button buildGenerateButton(String label, GridPane pane, int row) {
          Button button = new Button(label);
          pane.add(button,1, 30);
           button.setFont(new Font("Indie Flower",14));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
        GridPane.setHalignment(button, HPos.CENTER);
          GridPane.setMargin(button, new Insets(10, 0, 10, 0));
        return button;
    }//button
 
      private Button buildGenerateButton3(String label, GridPane pane, int row) {
        Button button = new Button(label);
         button.setFont(new Font("Indie Flower",16));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
        pane.add(button,17, 40);//-fila columna
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
         pane.setStyle("-fx-background-color:FFFACD" );
     
        
         return new Scene (pane,1000,1000);
    }//scene
     
      public void AgregarPuestos(String nameCatalogue, String properties) throws FileNotFoundException{
   PrintStream ps = getPrintStream("catalogue.txt");
   ps.println(nameCatalogue+"|"+properties+"*");
   }//agregar
   
   
   public Catalogue leeArchivos(){
    
        BufferedReader br = getBufferReader("catalogue.txt");
       
        int indice =0;
         Catalogue jB = null;
        
            
        try {
            String puestosActuales;
            puestosActuales = br.readLine();
       
            while(puestosActuales != null){
                
            StringTokenizer st = new StringTokenizer(puestosActuales,"|");
            String puestosNombres ="",nameCatalogue="",propiertes="";
            int controlaTokens = 1;
            
            while(st.hasMoreTokens()){
                if (controlaTokens==1) 
                nameCatalogue=st.nextToken();
                else if (controlaTokens==2)
                propiertes=st.nextToken();
                
               controlaTokens++; 
            }//while
            
        jB= new Catalogue(nameCatalogue, propiertes);
         
            indice++;
            puestosActuales=br.readLine();
        
        }//while
       
        } 
        catch (IOException ex) {
            Logger.getLogger(InterfaceCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    return jB;
    }//job
   
    private PrintStream getPrintStream(String nombreArchivo) throws FileNotFoundException {
     File lolo = new File(nombreArchivo);
     PrintStream ps = null;
     FileOutputStream fos = new FileOutputStream(lolo);
     ps = new PrintStream(fos);
    return ps;  
    }//get

      private BufferedReader getBufferReader(String listaauxtxt) {
   File archivo = new File(listaauxtxt);
        BufferedReader br = null;
        try{
            FileInputStream fis = new FileInputStream(archivo);
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
        }
        catch(FileNotFoundException fnfe){
            System.out.println("Problemas con el archivo.");
        }
    return br;
    }
      
     
     
}//end




