
package sample;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class LoginController implements Initializable {

    @FXML
    private TextField UserName;
    @FXML
    private PasswordField password;
    @FXML
    private AnchorPane loginPage;

 static List<User> listOfUsers = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @FXML
    private void loginAction(ActionEvent event) throws IOException {
   


    
        String greeting = "Hello";
        String username;
        String Password;

        // Used to hold the instance of a user who successfully logged in
        User loggedInUser = null;

        // Create an empty list to hold users
       

        // Add 3 users to the list
        listOfUsers.add(new User("user1","password1"));
        listOfUsers.add(new User("user2","password2"));
        listOfUsers.add(new User("user3","password3"));
        listOfUsers.add(new User("Mukaffi","123456"));

        // Get user input
        username=UserName.getText();
        Password=password.getText();

        // Iterate through list of users to see if we have a match
        for (User user : listOfUsers)
        {
            if (user.getUsername().equals(username))
            {
                if (user.getPassword().equals(Password))
                {
                    loggedInUser = user;



            Parent pane=FXMLLoader.load(getClass().getResource("customer.fxml"));
            loginPage.getChildren().setAll(pane);
                    break;
                }
            }
        }

        // if loggedInUser was changed from null, it was successful
        if (loggedInUser != null)
        {
            System.out.println("User successfully logged in: "+loggedInUser.getUsername());

            try {
                Socket sc = new Socket("localhost", 7500);
                BufferedReader cReader;
                BufferedWriter cWriter;
                OutputStreamWriter o = new OutputStreamWriter(sc.getOutputStream());
                cWriter = new BufferedWriter(o);

                InputStreamReader isr = new InputStreamReader(sc.getInputStream());
                cReader = new BufferedReader(isr);

                cWriter.write(loggedInUser.getUsername() + "\n");

               // cWriter.write(Password+"\n");
                //    System.out.println(uname);

                cWriter.flush();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Invalid username/password combination");
        }
    }

    
    

    @FXML
    private void createAction(ActionEvent event) throws IOException {
    Parent pane=FXMLLoader.load(getClass().getResource("createAccount.fxml"));
            loginPage.getChildren().setAll(pane);
}

    @FXML
    private void Back_action(ActionEvent event) throws IOException {
        Parent pane=FXMLLoader.load(getClass().getResource("sample.fxml"));
        loginPage.getChildren().setAll(pane);
    }
    
}

