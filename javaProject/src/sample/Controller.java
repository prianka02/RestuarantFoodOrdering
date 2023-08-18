package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;


public class Controller {

    BufferedReader cReader;
    BufferedWriter cWriter;

    public Controller(){
        try {
            Socket sc = new Socket("localhost", 6700);

            OutputStreamWriter o = new OutputStreamWriter(sc.getOutputStream());
            cWriter = new BufferedWriter(o);

            InputStreamReader isr = new InputStreamReader(sc.getInputStream());
            cReader = new BufferedReader(isr);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    PasswordField hidePass;
    @FXML
    Button loginMn;
    @FXML
    CheckBox check;
    @FXML
    private void managerAction(){

        username.setDisable(false);
        password.setDisable(false);
        hidePass.setDisable(false);
        check.setDisable(false);
        loginMn.setDisable(false);


    }

    @FXML
    private void managerLoginAction(javafx.event.ActionEvent event) throws IOException{
        String uname = username.getText();
        String pass = password.getText();
        String hidepass = hidePass.getText();
        username.clear();
        password.clear();
        hidePass.clear();

        if((uname == null && pass == null ) || (uname.length()==0 && pass.length()==0 )){
            return;
        }
        try {
            cWriter.write("check\n");
            cWriter.write(uname + "\n");
            System.out.println(uname);

            checkVisibility();
            if (password.isVisible()){
                cWriter.write(pass + "\n");
                System.out.println(pass);
            }
            else {
                cWriter.write(hidepass+ "\n");
                System.out.println(hidepass);
            }
            cWriter.flush();

            int result = cReader.read();

            System.out.println(result);
            if (result == 1){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
               // alert.setTitle("Exit");
                alert.setHeaderText("You are Logged in."+"\n"+"Click the OK button!");
                if(alert.showAndWait().get() == ButtonType.OK){
                    Parent parent = FXMLLoader.load(getClass().getResource("manager.fxml"));
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setTitle("Food List");
                    window.setScene(new Scene(parent));
                    window.show();

//        this will go to manager page where h will able to add/update food
                       }


            }
            else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("You are unable to Logged in!"+"\n"+"Do you want to close the window?");
                if(alert.showAndWait().get() == ButtonType.OK){
                    stage = (Stage)pane.getScene().getWindow();
                    stage.close();

                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void checkVisibility(){

        if (check.isSelected()) {
            password.setText(hidePass.getText());
            password.setVisible(true);
            hidePass.setVisible(false);
            return;
        }
        hidePass.setText(password.getText());
        hidePass.setVisible(true);
        password.setVisible(false);
        return;
    }
    @FXML
    public void customerAction(javafx.event.ActionEvent event)throws IOException {
      //  Parent parent = FXMLLoader.load(getClass().getResource("customer.fxml"));
        Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       // window.setTitle("CustInfo");
        window.setScene(new Scene(parent));
        window.show();
    }


    @FXML
    private Pane pane;
    @FXML
    private Button exitHomePage;
    @FXML
    Stage stage;
    @FXML
    public void exitHomeAction(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Do you want to logout from Restaurant Management System?");
        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage)pane.getScene().getWindow();
            stage.close(); }
    }
}
