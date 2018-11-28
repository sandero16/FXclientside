package sample;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.tree.ExpandVetoException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class waitingRoomController implements Initializable {
    public Label statusLabel;
    public Counter impl;
    public waitingForPlayer waitingThread;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setInterface(Counter impl){
        this.impl=impl;
    }
    public void setThread(waitingForPlayer w){
        waitingThread=w;
    }
    public void waitForOtherPlayer(String sessionToken, int aantalspelers){
        System.out.println("starting thread sessiontoken" +sessionToken);
        try {
            impl.addToGame(sessionToken, aantalspelers);
            System.out.println("zoeken naar ander"+ sessionToken);
            new Thread(waitingThread).start();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }




        }
        public void playerGevonden(String sessionToken){
            System.out.println("game started");
            FXMLLoader Loader=new FXMLLoader();
            Loader.setLocation(getClass().getResource("gameWindow.fxml"));
            try{
                Loader.load();
            }
            catch (Exception e){
                System.out.println("failed");
            }

            Stage stage=new Stage();
            GameWindowController controller =Loader.getController();
            controller.setInterface(impl);
            controller.setHelper(new ListenerHelper(controller,impl,sessionToken));
            Parent root=Loader.getRoot();
            stage.setTitle("Game");
            stage.setScene(new Scene(root, 300, 275));
            stage.show();
            Stage oldstage  = (Stage) statusLabel.getScene().getWindow();
            oldstage.close();
            controller.setGame(sessionToken);



        }
}