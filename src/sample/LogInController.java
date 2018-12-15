package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    public TextField username;
    public PasswordField password;
    public Label label;
    public Label warningLabel;
    public DispatchingInterface impl;

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
    public void sendLogin() {
        try {

            String name = username.getText();
            String ww = password.getText();
            // hashing ww
            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            byte[] hashedBytes = digest.digest(ww.getBytes());
            String hashedWW = bytesToHex(hashedBytes);
            String sessionToken = impl.LogIn(name, hashedWW);
            if (sessionToken == null) {
                warningLabel.setText("Foute login of al aangemeld");
                return;
            }

            System.out.println("token: " + (sessionToken));

            PrintWriter writer = new PrintWriter("sessiontoken.txt");
            writer.write(sessionToken);
            writer.close();

            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("hostJoin.fxml"));
            try {
                Loader.load();
            } catch (IOException ioe) {
            }

            Stage stage = new Stage();
            HostJoinController controller = Loader.getController();
            controller.setInterface(impl);
            controller.setSessiontoken(sessionToken);
            Parent root = Loader.getRoot();
            stage.setTitle("keuzeJoinHost");
            stage.setScene(new Scene(root, 300, 275));
            stage.show();


            Stage oldstage = (Stage) username.getScene().getWindow();
            oldstage.close();



        } catch (Exception e) {

        }
    }

        @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
