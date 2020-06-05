/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.Interfaces;


import static edu.ucr.rp.Interfaces.UIConstaints.INPUT_WITH;
import static edu.ucr.rp.Interfaces.UIConstaints.INPUT_WITH_MAX;
import static edu.ucr.rp.Interfaces.UIConstaints.LABEL_WITH;
import static edu.ucr.rp.Interfaces.UIConstaints.LABEL_WITH_MAX;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
 
public class InterfaceEntry extends Application{
    public TextField txUser= new TextField();
    private PasswordField Txt2 = new PasswordField();
    private Button btn_Admin;
     private Button btn_user;
    private Stage stage;
    private Label Lbl;
    private Button BtnSalida;
    private Label lb2;
    private Image bgImage = new Image("file:src/main/java/images/lolo.jpg");
    private StackPane container = new StackPane();
    private Label labelEntryDates;
    private ImageView Products;
    private Label lblSlogan;
    
  
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
        InterfaceChooseWay iU = new InterfaceChooseWay();
        
        btn_Admin.setOnAction(actionEvent -> {
//            String text = txUser.getText();
//             if(Txt2.getText().equals("ucr")){
//                 if(txUser.getText().equals("AngieMora")||txUser.getText().equals("AngelicaRedondo")||txUser.getText().equals("MarianMurillo")||txUser.getText().equals("SamuelLuque")){
             
             try {
              iU.start(stage);
            
             }//try 
             catch (Exception ex) {
                 Logger.getLogger(InterfaceEntry.class.getName()).log(Level.SEVERE, null, ex);
             }//catch//catch//catch//catch
//             }else
//                     
//                 JOptionPane.showMessageDialog(null,"Contraseña o usuario erroneos,"+"\n"+"Ingrese la contraseña o usuario correctos");
//                 Txt2.clear();
//                 txUser.clear();
//             }else
//                 
//              JOptionPane.showMessageDialog(null,"Contraseña o usuario erroneos,"+"\n"+"Ingrese la contraseña o usuario correctos");
//              Txt2.clear();
//              txUser.clear();
         });
     
       
        

         
         InterfacePrincipal iP = new InterfacePrincipal();
         BtnSalida.setOnAction(actionEvent -> {
             try {
                 iP.start(stage);
                
             } catch (Exception ex) {
                 Logger.getLogger(InterfaceEntry.class.getName()).log(Level.SEVERE, null, ex);
             }
         });
      
//       
         
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
     
     
      private void setupControls(GridPane pane) throws FileNotFoundException {
       
        txUser = buildTextInput(pane, 5);
        btn_Admin = buildGenerateButton("Ingresar: ", pane, 5);
        Lbl = buildLabel("Nombre de usuario: ", pane, 5);
        Txt2= (PasswordField) buildTextInput2(pane, 5);
        Lbl=buildLabel2("Contraseña", pane, 5);
        BtnSalida=buildGenerateButtonEnd("Regresar", pane, 5);
        labelEntryDates=buildLabelDates("Ingrese usuario  y contraseña:", pane, 5);
        Products=ImagePreview(pane, 5);
        lblSlogan=buildLabelSlogan("Progrados de Concreto S.A", pane, 5);
    }//controladores
     
      private TextField buildTextInput(GridPane pane, int row) {
          TextField textField = new TextField();
          pane.add(textField, 1, 18);
          textField.setFont(new Font("Indie Flower",16));
          GridPane.setMargin(textField, new Insets(10, row, 10, row));
        return textField;
    }//textField
      
      private TextField buildTextInput2(GridPane pane, int row) {
          PasswordField Txt2 = new PasswordField();
          pane.add(Txt2, 1, 20);
          Txt2.setFont(new Font("Indie Flower",16));// determinar el tipo de letra y color radio button
          GridPane.setMargin(Txt2, new Insets(10, row, 10, row));
        return Txt2;
    }//textField
      
     private Button buildGenerateButton(String label, GridPane pane, int row) {
          Button button = new Button(label);
          pane.add(button,1, 25);
           button.setFont(new Font("Indie Flower",14));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
        GridPane.setHalignment(button, HPos.CENTER);
          GridPane.setMargin(button, new Insets(10, 0, 10, 0));
        return button;
    }//button
     

      private ImageView ImagePreview(GridPane pane,int row) throws FileNotFoundException{
        FileInputStream imageStream = new FileInputStream("pala.png");
        Image imageS = new Image(imageStream);
        ImageView image = new ImageView(imageS);
        GridPane.setHalignment(image, HPos.CENTER);
        pane.add(image, 12, 0);
        GridPane.setMargin(image, new Insets(20, 0, 20, 0));
        return image;
      }
     
      private Label buildLabelSlogan(String text, GridPane pane, int row) {
        Label lb = new Label(text);
        pane.add(lb,13, 0);
        lb.setFont(new Font("Indie Flower",16));
        GridPane.setMargin(lb, new Insets(10, 0, 10, 0));
        return lb;
    }//button
    
      
      
     
     
      private Button buildGenerateButtonEnd(String label, GridPane pane, int row) {
        Button button = new Button(label);
         button.setFont(new Font("Indie Flower",16));// determinar el tipo de letra y color radio button
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: WHITE");
        pane.add(button,12, 30);//-fila columna
//        GridPane.setHalignment(button, HPos.CENTER);
        GridPane.setMargin(button, new Insets(10, row, 10, row));
        return button;
    }//button  
    private Label buildLabel(String text, GridPane pane, int row) {
        Label lb = new Label(text);
        pane.add(lb,0, 18);
        lb.setFont(new Font("Indie Flower",16));
        GridPane.setMargin(lb, new Insets(10, 0, 10, 0));
        return lb;
    }//button
    
     private Label buildLabelDates(String text, GridPane pane, int row) {
        Label lb = new Label(text);
        pane.add(lb,0, 16);
        lb.setFont(new Font("Indie Flower",16));
        GridPane.setMargin(lb, new Insets(10, 0, 10, 0));
        return lb;
    }//button
    
    
    
    private Label buildLabel2 (String text, GridPane pane, int row) {
        Label lb = new Label(text);
         lb.setFont(new Font("Indie Flower",16));
        pane.add(lb,0, 20);
        GridPane.setMargin(lb, new Insets(10, 0, 10, 0));
        return lb;
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
         pane.setStyle("-fx-background-color:#37D8E3" );
     
        
         return new Scene (pane,800,800);
    }//scene

   

  
 
}//end
