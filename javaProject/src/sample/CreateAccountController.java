/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.LoginController;
import sample.User;

/**
 * FXML Controller class
 *
 * @author Mukaffi
 */
public class CreateAccountController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private AnchorPane Create;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Back_action(ActionEvent event) throws IOException {
   Parent pane=FXMLLoader.load(getClass().getResource("Login.fxml"));
            Create.getChildren().setAll(pane);
    }

    @FXML
    private void CreateAction(ActionEvent event) throws IOException {
        String Username;
        String Password;
        
        Username=username.getText();
        Password=password.getText();
        
        LoginController.listOfUsers.add(new User(Username,Password));
        
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("User Created ");
         a.setContentText("User Created Successfully ");
         a.showAndWait();
        
    Parent pane=FXMLLoader.load(getClass().getResource("Login.fxml"));
            Create.getChildren().setAll(pane);
    
    }
    
}
