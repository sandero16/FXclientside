package sample;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.server.ExportException;
import java.util.ResourceBundle;

public class keuzeMenuSpelersController  implements Initializable {

    public Button twee;
    public Button drie;
    public Button vier;
    public Button afmelden;
    public DispatchingInterface impl;
    public String sessionToken;
    public Boolean host=false;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void tweeclicked() {
        openWaitingRoom(2);
    }
    public void drieclicked() {
        openWaitingRoom(3);
    }
    public void vierclicked() {
        openWaitingRoom(4);
    }
    public void setHost(){this.host=true;}
    public void setInterface(DispatchingInterface impl) {
        this.impl=impl;
    }
    public void setSessiontoken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
    public void afmeldenclicked(){
        try {
            impl.logOut(sessionToken);
            Stage toClose = (Stage) afmelden.getScene().getWindow();
            toClose.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void openWaitingRoom(int spelers){
        FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("waitingRoom.fxml"));
        try{
            Loader.load();
        }
        catch (IOException ioe){
        }

        Stage stage=new Stage();
        waitingRoomController controller =Loader.getController();
        controller.setInterface(impl);
        Parent root=Loader.getRoot();
        stage.setTitle("waitingroom");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
        System.out.println("sessiontoken: "+sessionToken);
        Stage oldstage  = (Stage) twee.getScene().getWindow();
        oldstage.close();
        System.out.println("ik ben er nog");
        controller.waitForOtherPlayer(sessionToken, spelers, host);
    }

}

