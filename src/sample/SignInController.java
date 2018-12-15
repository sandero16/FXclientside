package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
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

            // hashing ww
            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            byte[] hashedBytes = digest.digest(signInww.getBytes());
            String hashedWW = bytesToHex(hashedBytes);


            boolean succes = impl.SignIn(name, hashedWW);
            if(!succes){
                warningLabel.setText("Username al in gebruik");
                return;
            }


            Stage stage  = (Stage) SignInUsername.getScene().getWindow();
            stage.close();
        }
        catch (Exception e){
            System.out.println("error");
        }
    }
}
