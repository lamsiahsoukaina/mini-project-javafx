
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class mainframe {
    @FXML
    TextField username;

    @FXML
    PasswordField password;

    public void login(Event e){
        try{
            if(username.getText().trim().matches("[aA]dmin")&&password.getText().equals("123")){

                Parent root = FXMLLoader.load(getClass().getResource("test/mainfr.fxml"));
                Scene scene=new Scene(root);
                Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Home");
                Rectangle2D rd= Screen.getPrimary().getVisualBounds();
                stage.setX((rd.getWidth()-stage.getWidth())/2);
                stage.setY((rd.getHeight()-stage.getHeight())/2);
                   

                
        }else{
            JOptionPane.showMessageDialog(null, "Check your username or password .");
        }
        }catch(Exception ex){

        }
    }

    public void exit(){
        Platform.exit();
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {

    }

}

