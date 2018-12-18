package sample;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HostJoinController implements Initializable {
    public Button join_button;
    public String sessionToken;
    public DispatchingInterface impl;
    public Boolean Host;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setInterface(DispatchingInterface impl) {
        this.impl=impl;
    }
    public void setSessiontoken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public void onJoinClicked(){
        Host=false;
        goToSpelerKeuzeWindow();
    }
    public void onHostClicked(){
        Host=true;
        goToSpeelbordKeuzeWindow();
    }
    public void goToSpelerKeuzeWindow(){
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("keuzemenuSpelers.fxml"));
        try {
            Loader.load();
        } catch (IOException ioe) {
        }

        Stage stage = new Stage();
        keuzeMenuSpelersController controller = Loader.getController();
        controller.setInterface(impl);
        controller.setSessiontoken(sessionToken);
        Parent root = Loader.getRoot();
        stage.setTitle("Memoryzzz");
        stage.setScene(new Scene(root, 650, 600));
        stage.show();


        Stage oldstage = (Stage) join_button.getScene().getWindow();
        oldstage.close();


    }
   public void goToSpeelbordKeuzeWindow(){
       FXMLLoader Loader = new FXMLLoader();
       Loader.setLocation(getClass().getResource("keuzemenuSpelers.fxml"));
       try {
           Loader.load();
       } catch (IOException ioe) {
       }

       Stage stage = new Stage();
       keuzeMenuSpelersController controller = Loader.getController();
       controller.setInterface(impl);
       controller.setSessiontoken(sessionToken);
       controller.setHost();
       Parent root = Loader.getRoot();
       stage.setTitle("MemoryZZZ");
       stage.setScene(new Scene(root, 650, 600));
       stage.show();


       Stage oldstage = (Stage) join_button.getScene().getWindow();
       oldstage.close();
   }
}
