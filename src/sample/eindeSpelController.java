package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;


import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class eindeSpelController implements Initializable {
    public AppInterface impl;
    public Label status;
    public Label score1;
    public Label punten;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void getResults(AppInterface impl, String sessionToken, int score){
        System.out.println("Scores" + score);
        String gedeeld = "";
        try {
            int plaats = 0;
            try {
                plaats = impl.getResult(sessionToken);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if(plaats>10) {
                gedeeld = " gedeeld";
                plaats -=10;
            }


            if(plaats == 1)
                status.setText("Proficiat, je bent" + gedeeld + " eerste!!!!");
            else{
                String plaatsStr = "";
                if(plaats == 2) plaatsStr = " tweede";
                else if(plaats == 3) plaatsStr = " derde";
                else if(plaats == 4) plaatsStr = " vierde";
                status.setText("Je bent" + gedeeld + plaatsStr);
            }
            score1.setText("Jouw score: "+Integer.toString(impl.getScore(sessionToken)));
            int points = impl.getPunten(sessionToken);
            System.out.println("PUNTEN " + points);
            if(points == 1){
                punten.setText("Je ontvangt "+ points + " rankingpunt.");
            }
            else
                punten.setText("Je ontvangt "+ points + " rankingpunten.");

        }
        catch (Exception e){

        }
    }
}
