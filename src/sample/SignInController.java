package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {
    public TextField SignInUsername;
    public TextField SignInWW1;
    public TextField SignInWW2;
    public Label warningLabel;
    public DispatchingInterface impl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setInterface(DispatchingInterface impl){
        this.impl=impl;
    }
    public void sendSignIn(){
        try {
            String name=SignInUsername.getText();
            String signInww=SignInWW1.getText();
            String signInww2=SignInWW2.getText();

            System.out.println(name+" " +signInww+" "+signInww2);

            while(!signInww.equals(signInww2)){
                System.out.println("niet identiek");
                warningLabel.setText("ww zijn niet identiek");
                return;
            }
            //methode schrijven die duplicats ziet;

            boolean succes = impl.SignIn(name, signInww);
            if(!succes){
                warningLabel.setText("Username al in gebruik");
                return;
            }
            System.out.println("gelukt");

            Stage stage  = (Stage) SignInUsername.getScene().getWindow();
            stage.close();
        }
        catch (Exception e){
            System.out.println("error");
        }
    }
}
