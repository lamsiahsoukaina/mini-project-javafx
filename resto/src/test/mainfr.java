package test;
import java.net.URL;
import java.util.ResourceBundle;

import com.mysql.cj.xdevapi.Table;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class mainfr {
    @FXML
    TextField numDrinks;
    @FXML
    TextField numMeals;
    @FXML
    Pane Drinks;
    @FXML
    Pane Meals;
    @FXML
    ComboBox typD;
    @FXML
    ComboBox typM;

    @FXML
    TextField numM;
    @FXML
    TextField namM;
    @FXML
    TextField cosM;
    @FXML
    Label doneM;

    @FXML
    TextField numD;
    @FXML
    TextField namD;
    @FXML
    TextField cosD;
    @FXML
    Label doneD;

    @FXML
    TableView <Meals> tableM;
    @FXML
    TableColumn <Meals,Integer> numTM;
    @FXML
    TableColumn <Meals,String> namTM;
    @FXML
    TableColumn <Meals,String> typTM;
    @FXML
    TableColumn <Meals,Integer> cosTM;

    ObservableList<Meals> listM;

    @FXML
    TableView <Dranks> tableD;
    @FXML
    TableColumn <Dranks,Integer> numTD;
    @FXML
    TableColumn <Dranks,String> namTD;
    @FXML
    TableColumn <Dranks,String> typTD;
    @FXML
    TableColumn <Dranks,Integer> cosTD;

    ObservableList<Dranks> listD;

    int indexM=-1;
    int indexD=-1;

   @FXML
    TextField searchM;
    @FXML
    TextField searchD;

    public void entred(Event e){
            ((Button) e.getSource()).setScaleX(1.1);
            ((Button) e.getSource()).setScaleY(1.1);
        }
    
    public void exited(Event e){
            ((Button) e.getSource()).setScaleX(1);
            ((Button) e.getSource()).setScaleY(1);
        }

    public void Dranks(){
          Drinks.setVisible(true);
          Meals.setVisible(false);
    }

    public void Meals(){
        Drinks.setVisible(false);
        Meals.setVisible(true);
    }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    public void insertMeals(){
       try {
            int num=Integer.parseInt(numM.getText());
        String name=namM.getText();
        String type=typM.getSelectionModel().getSelectedItem().toString();
        int cost=Integer.parseInt(cosM.getText());

        if(!DB.insert("Meals", num, name, type, cost)){
            listM.add(new Meals(num, name, type, cost));
            doneM.setText("Added successfuly !");
            doneM.setVisible(true);
            numMeals.setText(Integer.parseInt(numMeals.getText())+1+"");
        }
       } catch (Exception e) {
       }

    }
     public void insertDranks(){
       int num=Integer.parseInt(numD.getText());
       String name=namD.getText();
       String type=typD.getSelectionModel().getSelectedItem().toString();
       int cost=Integer.parseInt(cosD.getText());

       if(!DB.insert("Drinks", num, name, type, cost)){
        listD.add(new Dranks(num, name, type, cost));
        doneD.setText("Added successfuly !");
        doneD.setVisible(true);
        numDrinks.setText(Integer.parseInt(numDrinks.getText())+1+"");
       }

    }

    public void getSelectedMeals(){
        indexM=tableM.getSelectionModel().getSelectedIndex();

        if(indexM <=-1){
            return;
        }

        numM.setText(numTM.getCellData(indexM).toString());
        namM.setText(namTM.getCellData(indexM).toString());
        typM.getSelectionModel().select(typTM.getCellData(indexM));
        cosM.setText(cosTM.getCellData(indexM).toString());
    }

    public void updateMeals(){
        try {
             int num=Integer.parseInt(numM.getText());
            String name=namM.getText();
            String type=typM.getSelectionModel().getSelectedItem().toString();
            int cost=Integer.parseInt(cosM.getText());

       if(DB.update("Meals","where numM="+num,name,type,cost)){
             listM.set(indexM, new Meals(num, name, type, cost));
             doneM.setText("Modified successfully");
             doneM.setVisible(true);
             clearM();
       }  
        } catch (Exception e) {
           
        }
    
    }

    public void clearM(){
        numM.clear();
        namM.clear();
        typM.getSelectionModel().select(-1);
        cosM.clear();

    }

    public void getSelectedDrinks(){
        indexD=tableD.getSelectionModel().getSelectedIndex();

        if(indexD <=-1){
            return;
        }

        numD.setText(numTD.getCellData(indexD).toString());
        namD.setText(namTD.getCellData(indexD).toString());
        typD.getSelectionModel().select(typTD.getCellData(indexD));
        cosD.setText(cosTD.getCellData(indexD).toString());
    }

    public void updateDrinks(){
        try {
             int num=Integer.parseInt(numD.getText());
            String name=namD.getText();
            String type=typD.getSelectionModel().getSelectedItem().toString();
            int cost=Integer.parseInt(cosD.getText());

       if(DB.update("Drinks","where numD="+num,name,type,cost)){
             listD.set(indexD, new Dranks(num, name, type, cost));
             doneD.setText("Modified successfully");
             doneD.setVisible(true);
             clearD();
       }  
        } catch (Exception e) {
           
        }
    
    }

    public void clearD(){
        numD.clear();
        namD.clear();
        typD.getSelectionModel().select(-1);
        cosD.clear();

    }

    public void deleteMeals() {
        if (indexM == -1) {
            return;
        }
    
        try {
            int numMValue = numTM.getCellData(indexM);
            if (DB.delete("Meals", "numM=" + numMValue)) {
                doneM.setText("Deleted successfully");
                doneM.setVisible(true);
                int currentNumMeals = Integer.parseInt(numMeals.getText()) - 1;
                numMeals.setText(String.valueOf(currentNumMeals));
                listM.remove(indexM);
                indexM = -1;
                clearM();
            } else {
                doneM.setText("Deletion failed");
                doneM.setVisible(true);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            doneM.setText("Error: Invalid number format");
            doneM.setVisible(true);
        }
    }
    
    
    public void deleteDrinks() {
        if (indexD == -1) {
            return;
        }
    
        try {
            int numDValue = numTD.getCellData(indexD);
            if (DB.delete("Drinks", "numD=" + numDValue)) {
                doneD.setText("Deleted successfully");
                doneD.setVisible(true);
                int currentNumDrinks = Integer.parseInt(numDrinks.getText()) - 1;
                numDrinks.setText(String.valueOf(currentNumDrinks));
                listD.remove(indexD);
                indexD = -1;
                clearD();
            } else {
                doneD.setText("Deletion failed");
                doneD.setVisible(true);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            doneD.setText("Error: Invalid number format");
            doneD.setVisible(true);
        }
    }
    
  public void searchMeals(){
        searchM.textProperty().addListener(new InvalidationListener(){

            @Override
          public void invalidated(Observable o){
                if(searchM.textProperty().get().isEmpty()){
                    tableM.setItems(listM);
                    return;
                }
                ObservableList <Meals> tableItems=FXCollections.observableArrayList();
                ObservableList <TableColumn<Meals,?>> cols= tableM.getColumns();
                for(int i=0;i<listM.size();i++){
                    for(int j=0;j<cols.size();j++){
                        TableColumn col=cols.get(j);
                        String cellValue=col.getCellData(listM.get(i)).toString();
                        cellValue =cellValue.toLowerCase();
                        



                        if(cellValue.contains (searchM.getText().toLowerCase()) && cellValue.startsWith(searchM.getText().toLowerCase() )){
                           tableItems.add(listM.get(i));

                                break;
                        }

                    }
                }
                tableM.setItems(tableItems);
          }
            
        });


  }
  

public void searchDrinks(){
        searchD.textProperty().addListener(new InvalidationListener(){

            @Override
          public void invalidated(Observable o){
                if(searchD.textProperty().get().isEmpty()){
                    tableD.setItems(listD);
                    return;
                }
                ObservableList <Dranks> tableItems=FXCollections.observableArrayList();
                ObservableList <TableColumn<Dranks,?>> cols= tableD.getColumns();
                for(int i=0;i<listD.size();i++){
                    for(int j=0;j<cols.size();j++){
                        TableColumn col=cols.get(j);
                        String cellValue=col.getCellData(listD.get(i)).toString();
                        cellValue =cellValue.toLowerCase();
                        



                        if(cellValue.contains (searchD.getText().toLowerCase()) && cellValue.startsWith(searchD.getText().toLowerCase() )){
                           tableItems.add(listD.get(i));

                                break;
                        }

                    }
                }
                tableD.setItems(tableItems);
          }
            
        });


  }

  public void logout(Event e){
    try{
                Parent root = FXMLLoader.load(getClass().getResource("../mainframe.fxml"));
                Scene scene=new Scene(root);
                Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Login");
                Rectangle2D rd= Screen.getPrimary().getVisualBounds();
                stage.setX((rd.getWidth()-stage.getWidth())/2);
                stage.setY((rd.getHeight()-stage.getHeight())/2);
    }catch(Exception ex){

    }
  }



    @FXML
    void initialize() {
        
        numTM.setCellValueFactory(new PropertyValueFactory<Meals,Integer>("id"));
        namTM.setCellValueFactory(new PropertyValueFactory<Meals,String>("name"));
        typTM.setCellValueFactory(new PropertyValueFactory<Meals,String>("type"));
        cosTM.setCellValueFactory(new PropertyValueFactory<Meals,Integer>("cost"));

        numTD.setCellValueFactory(new PropertyValueFactory<Dranks,Integer>("id"));
        namTD.setCellValueFactory(new PropertyValueFactory<Dranks,String>("name"));
        typTD.setCellValueFactory(new PropertyValueFactory<Dranks,String>("type"));
        cosTD.setCellValueFactory(new PropertyValueFactory<Dranks,Integer>("cost"));

        listM = DB.getMeals();
        tableM.setItems(listM);

        listD = DB.getDranks();
        tableD.setItems(listD);

        numDrinks.setText(DB.count("numD","drinks")+"");
        numMeals.setText(DB.count("numM","meals")+"");
        
        ObservableList listM=FXCollections.observableArrayList("Extra Big","Big","Medium","Small");
        typM.setItems(listM);
        
        ObservableList listD=FXCollections.observableArrayList("Hot","Warm","Cold","Freezy");
        typD.setItems(listD);


    }

}
