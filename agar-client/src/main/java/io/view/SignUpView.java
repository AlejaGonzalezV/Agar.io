package io.view;

import java.io.FileWriter;
import java.io.PrintWriter;

import io.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpView {

    @FXML
    private Button SignUpButton;

    @FXML
    private Label TitleEmail;

    @FXML
    private Label TitlePassword;

    @FXML
    private TextField emailTxt;

    @FXML
    private PasswordField passwordTxt;
    private Stage stage = new Stage();

    @FXML
    void SignUpBut(ActionEvent event) throws Exception {
    	
    	EscribeFichero();
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/SignIn.fxml"));
		Parent root = (Parent)fxmlLoader.load();          
		SignInView controller = fxmlLoader.<SignInView>getController();
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		stage.setTitle("Agar.io");
		stage.show();
		Stage stageAct = (Stage) SignUpButton.getScene().getWindow();
		stageAct.close();
    	
//    	Controller controller = new Controller();
//    	controller.open();
    	

    }
    
    public void EscribeFichero()
    {

            FileWriter fichero = null;
            PrintWriter pw = null;
            try
            {
                fichero = new FileWriter("../data/usuarios.txt",true);
                pw = new PrintWriter(fichero);
                    pw.println(emailTxt.getText()+";"+passwordTxt.getText());

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
               try {
              
               if (null != fichero)
                  fichero.close();
               } catch (Exception e2) {
                  e2.printStackTrace();
               }
            
        }
    }
    
    

}