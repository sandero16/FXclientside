package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;


import java.net.URL;
import java.util.ResourceBundle;

public class eindeSpelController implements Initializable {
    public Counter impl;
    public String sessionToken;
    public Label status;
    public Label score1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void getResults(Counter impl, String sessionToken, int score){
        try {
            if(impl.getResult(sessionToken)){
                status.setText("Je hebt gewonnen");
            }
            else {
                status.setText("Je bent verloren");
            }
            score1.setText("jouw score: "+Integer.toString(impl.getScore(sessionToken)));

        }
        catch (Exception e){

        }
    }
}
