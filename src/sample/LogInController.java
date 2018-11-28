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
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    public TextField username;
    public PasswordField password;
    public Label label;
    public Label warningLabel;
    public Counter impl;

    public void setInterface(Counter impl){
        this.impl=impl;
    }

    public void sendLogin() {
        try {

            String name = username.getText();
            String ww = password.getText();
            String sessionToken = impl.LogIn(name, ww);
            if (sessionToken == null) {
                warningLabel.setText("Foute login of al aangemeld");
                return;
            }

            System.out.println("token: " + (sessionToken));

            PrintWriter writer = new PrintWriter("sessiontoken.txt");
            writer.write(sessionToken);
            writer.close();

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
            stage.setTitle("keuzespelers");
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
