package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.foodList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class aptOrderController implements Initializable {
    @FXML
    public TextField foodName;
    @FXML
    public TextField price;

    @FXML
    private TableView<foodList> appetizers;
    @FXML
    private TableColumn<foodList,String> foodNameColum;
    @FXML
    private TableColumn<foodList,String> priceColum;

    ObservableList<foodList> list = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appetizers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.list.clear();

        try{
            File file = new File("items.txt");
            if (!file.exists()) file.createNewFile();

            Scanner sc = new Scanner(file);
            while (sc.hasNext()){
                String str = sc.nextLine();
                String parts[] = str.split("#");
                foodList obj = new foodList(parts[0],parts[1]);
                this.list.add(obj);


            }
        }catch (IOException e){
            e.printStackTrace();
        }

        foodNameColum.setCellValueFactory( new PropertyValueFactory<foodList,String>("foodName"));
        priceColum.setCellValueFactory( new PropertyValueFactory<foodList,String>("price"));

        appetizers.setItems(this.list);

    }

    @FXML
    public void OrderAction() throws IOException{
        List<foodList> selectedItems = appetizers.getSelectionModel().getSelectedItems();


        File file = new File("orderTable.txt");
        if (!file.exists()) file.createNewFile();
        FileWriter writer = new FileWriter(file,true);

        String str = "";
        for(foodList obj:selectedItems){
            str += obj.getFoodName()+"#"+obj.getPrice()+"\n";
        }
        writer.write(str);
        writer.close();

        Pane pane = null;
        Stage stage;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Your Order is confirmed!");
            if(alert.showAndWait().get() == ButtonType.OK){
                stage = (Stage)pane.getScene().getWindow();
                stage.close();


        }
    }

}
