package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public DispatchingInterface impl;

    public void setInterface(DispatchingInterface impl){
        this.impl=impl;
    }
    public void LoginScherm(){
        try {

            impl.testConnectie();

            FXMLLoader Loader=new FXMLLoader();
            Loader.setLocation(getClass().getResource("logIn.fxml"));
            try{
                Loader.load();
            }
            catch (IOException ioe){
            }

            Stage stage=new Stage();
            LogInController controller =Loader.getController();
            controller.setInterface(impl);
            Parent root=Loader.getRoot();
            stage.setTitle("Memoryzzz");
            stage.setScene(new Scene(root, 350, 400));
            stage.show();


        }
        catch (Exception e){

        }

    }
    public void viewMatch(){
        FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("gameView.fxml"));
        try{
            Loader.load();
        }
        catch (IOException ioe){

        }
        Stage stage=new Stage();
        GameviewController controller =Loader.getController();
        controller.setInterface(impl);
        controller.setListenHelperViewer(new ListenHelperViewer(controller));
        Parent root=Loader.getRoot();
        stage.setTitle("gamewindow");
        stage.setScene(new Scene(root, 780, 840));
        stage.show();
        controller.view();
    }
    public void SignInscherm(){
        try {
            impl.testConnectie();


            FXMLLoader Loader=new FXMLLoader();
            Loader.setLocation(getClass().getResource("signIn.fxml"));
            try{
                Loader.load();
            }
            catch (IOException ioe){

            }
            Stage stage=new Stage();
            SignInController controller =Loader.getController();
            controller.setInterface(impl);
            Parent root=Loader.getRoot();
            stage.setTitle("Hello World");
            stage.setScene(new Scene(root, 375, 450));
            stage.show();


        }
        catch (Exception e){
            System.out.println("signinscherm exception");
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
