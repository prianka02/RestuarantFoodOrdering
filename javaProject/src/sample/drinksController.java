package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class drinksController implements Initializable {
    @FXML
    public TextField foodName;
    @FXML
    public TextField price;

    @FXML
    private TableView<foodList> drinksTable;
    @FXML
    private TableColumn<foodList,String> foodNameColum;
    @FXML
    private TableColumn<foodList,String> priceColum;

    ObservableList<foodList> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        drinksTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.list.clear();
        try{
            File file = new File("drinksItem.txt");
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

        drinksTable.setItems(this.list);

    }
    @FXML
    public void addFood() throws IOException {

        String s1 = foodName.getText();
        String s2 = price.getText();

        if(s1.equals("") && s2.equals("")) return;
        foodList obj = new foodList(s1,s2);

        File file = new File("drinksItem.txt");
        if (!file.exists()) file.createNewFile();
        FileWriter fileWriter = new FileWriter(file,true);
        fileWriter.write(s1+"#"+s2+"\n");
        fileWriter.close();

        list.add(obj);

        foodName.clear();
        price.clear();


    }
    @FXML
    public void deleteFood()throws IOException{
        foodList selectedItems = drinksTable.getSelectionModel().getSelectedItem();
        this.list.remove(selectedItems);

        File file = new File("drinksItem.txt");
        FileWriter writer = new FileWriter(file);

        String str = "";
        for(foodList obj:this.list){
            str += obj.getFoodName()+"#"+obj.getPrice()+"\n";
        }
        writer.write(str);
        writer.close();

    }


}






