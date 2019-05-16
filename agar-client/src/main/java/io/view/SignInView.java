package io.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.management.Notification;

import io.connection.Persona;
import io.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInView {

    @FXML
    private Button SignInButton;

    @FXML
    private Label TitleEmail;

    @FXML
    private Label TitlePassword;

    @FXML
    private TextField emailTxt;

    @FXML
    private PasswordField passwordTxt;



    @FXML
    void SignInBut(ActionEvent event) throws Exception {
    	
    	if(leerUsuarios()) {
    		
    		String email = emailTxt.getText();
    		String pass = passwordTxt.getText();
    		Controller controller = new Controller();
        	controller.open(email, pass);
        	Stage stageAct = (Stage) SignInButton.getScene().getWindow();
    		stageAct.close();
    		
    	}
    	
    	
    	

    }
    
    public boolean leerUsuarios() {
    	
    	boolean respuesta = false;
		File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;

	      try {
	         
	         archivo = new File ("../data/usuarios.txt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         
	         String linea;
	         while((linea=br.readLine())!=null) {
//	            String info[] = linea.split("\n");
	            
//	            for(int i = 0; i<info.length;i++) {
	            	
	            	String data[] = linea.split(";");     	
	            	if(data[0].equals(emailTxt.getText()) && data[1].equals(passwordTxt.getText())) {
	            		
	            		respuesta = true;
	            		
	            	}
	            	
	            }
//	         }  
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	        
	    	  
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	      
	      return respuesta;
	
	}

}