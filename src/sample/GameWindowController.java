package sample;


import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class GameWindowController implements Initializable {
    public Counter impl;
    public boolean beurt;
    public ArrayList<Integer> waardes;
    public ArrayList<Integer>geraden;
    public ArrayList<Button> gekozen;
    public int aantalgeradenParen;
    public int aantalKeuzes;
    public String sessionToken;
    public ListenerHelper listenerHelper;
    public int score;


    public Label statuslabel;
    public Button button1;
    public Button button2;
    public Button button3;
    public Button button4;
    public Button button5;
    public Button button6;
    public Button button7;
    public Button button8;
    public Button button9;
    public Button button10;
    public Button button11;
    public Button button12;
    public Button button13;
    public Button button14;
    public Button button15;
    public Button button16;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setInterface(Counter impl){
        this.impl=impl;
    }

    public void setGame(String sessionToken){
        try{
            this.sessionToken=sessionToken;
            beurt=impl.setGame(sessionToken);
            aantalgeradenParen=0;
            gekozen=new ArrayList<>();
            waardes=new ArrayList<>();
            aantalKeuzes=0;
            if(beurt){
                System.out.println("jouw beurt");
                statuslabel.setText("het is jouw beurt");
            }
            else{
                System.out.println("niet jouw beurt");
                statuslabel.setText("het is aan de andere");
                listen();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }

    }
    public void button1Clicked(){
        button1.setText(Integer.toString(zet(1)));
        gekozen.add(button1);
        changeState();
    }
    public void button2Clicked(){
        button2.setText(Integer.toString(zet(2)));
        gekozen.add(button2);
        changeState();
    }
    public void button3Clicked(){
        button3.setText(Integer.toString(zet(3)));
        gekozen.add(button3);
        changeState();
    }
    public void button4Clicked(){
        button4.setText(Integer.toString(zet(4)));
        gekozen.add(button4);
        changeState();
    }
    public void button5Clicked(){
        button5.setText(Integer.toString(zet(5)));
        gekozen.add(button5);
        changeState();
    }
    public void button6Clicked(){
        button6.setText(Integer.toString(zet(6)));
        gekozen.add(button6);
        changeState();
    }
    public void button7Clicked(){
        button7.setText(Integer.toString(zet(7)));
        gekozen.add(button7);
        changeState();
    }
    public void button8Clicked(){
        button8.setText(Integer.toString(zet(8)));
        gekozen.add(button8);
        changeState();
    }
    public void button9Clicked(){
        button9.setText(Integer.toString(zet(9)));
        gekozen.add(button9);
        changeState();
    }
    public void button10Clicked(){
        button10.setText(Integer.toString(zet(10)));
        gekozen.add(button10);
        changeState();
    }
    public void button11Clicked(){
        button11.setText(Integer.toString(zet(11)));
        gekozen.add(button11);
        changeState();
    }
    public void button12Clicked(){
        button12.setText(Integer.toString(zet(12)));
        gekozen.add(button12);
        changeState();
    }
    public void button13Clicked(){
        button13.setText(Integer.toString(zet(13)));
        gekozen.add(button15);
        changeState();
    }
    public void button14Clicked(){
        button14.setText(Integer.toString(zet(14)));
        gekozen.add(button14);
        changeState();
    }
    public void button15Clicked(){
        button15.setText(Integer.toString(zet(15)));
        gekozen.add(button15);
        changeState();
    }
    public void button16Clicked(){
        button16.setText(Integer.toString(zet(16)));
        gekozen.add(button16);
        changeState();
    }

    public int zet(int i){
        try {
            if (beurt) {
                aantalKeuzes++;
                int temp=impl.getZet(i, sessionToken);
                waardes.add(temp);
                return temp;

            }
            else return -1;
        }
        catch (Exception e){

        }
        return -1;
    }
    public void setHelper(ListenerHelper listenerHelper){
        this.listenerHelper=listenerHelper;
    }
    public void changeState(){
        try {
            if (aantalKeuzes == 2) {
                beurt = false;
                /*
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        }
                        catch (Exception e){

                        }
                    }
                });*/

                Task<Void> sleeper = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        try {
                            System.out.println("sleep");
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                        }
                        return null;
                    }
                };
                sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent event) {
                        System.out.println("awake");
                        resetKeuzes();


                    }
                });
                new Thread(sleeper).start();
            }
                if(waardes.get(0)==waardes.get(1)){
                    score++;
                    gekozen.clear();
                }
                //resetKeuzes();


                impl.changeBeurt(sessionToken);
                aantalKeuzes=0;
                statuslabel.setText("het is aan de andere");
                if(aantalgeradenParen!=8) {
                    listen();
                }

            }
             catch (Exception e){

        }
    }
    public void setBeurt(){
        beurt=true;
    }
    public void resetKeuzes(){
        System.out.println("waardes: "+waardes.get(0)+" "+ waardes.get(1));
            if (waardes.get(0) == waardes.get(1)) {
                System.out.println("ze zijn gelijk");
                aantalgeradenParen++;
                gekozen.clear();

            }
            else {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        for (Button b : gekozen) {
                            b.setText("*");
                        }
                    }
                });
            }
            /*
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(4000);
                        } catch (Exception e) {
                            System.out.println("thread kan niet slapen");
                        }
                        for (Button b : gekozen) {
                            b.setText("*");
                        }
                        gekozen.clear();
                    }
                });
                */


            waardes.clear();
        System.out.println("aantalgeraden paren"+aantalgeradenParen);
        if(aantalgeradenParen==8) {
            System.out.println("open window");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    openEndWindow();
                }
            });


        }
    }
    public void openEndWindow(){
        System.out.println("binnen in openendwindow");
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("eindeSpel.fxml"));
        try {
            Loader.load();
        } catch (Exception e) {
            System.out.println("failed");
        }
        System.out.println("1e");
        Stage endstage=new Stage();
        System.out.println("2");
        eindeSpelController controller = Loader.getController();
        System.out.println("3");
        controller.getResults(impl, sessionToken, score);
        System.out.println("4");
        Parent root = Loader.getRoot();
        endstage.setTitle("Game");
        endstage.setScene(new Scene(root, 300, 275));
        endstage.show();

        Stage oldstage = (Stage) statuslabel.getScene().getWindow();

        oldstage.close();
    }
    public void incomingGok(int []gok){
        int button = gok[0];
        button++;
        int waarde = gok[1];
        waardes.add(waarde);
        System.out.println("waarde added: "+waarde);
        System.out.println("incoming: "+waardes.get(0));
        switch (button) {
            case 1:
                button1.setText(Integer.toString(waarde));

                gekozen.add(button1);
                break;
            case 2:
                button2.setText(Integer.toString(waarde));
                gekozen.add(button2);
                break;
            case 3:
                button3.setText(Integer.toString(waarde));
                gekozen.add(button3);
                break;
            case 4:
                button4.setText(Integer.toString(waarde));
                gekozen.add(button4);
                break;
            case 5:
                button5.setText(Integer.toString(waarde));
                gekozen.add(button5);
                break;
            case 6:
                button6.setText(Integer.toString(waarde));
                gekozen.add(button6);
                break;
            case 7:
                button7.setText(Integer.toString(waarde));
                gekozen.add(button7);
                break;
            case 8:
                button8.setText(Integer.toString(waarde));
                gekozen.add(button8);
                break;
            case 9:
                button9.setText(Integer.toString(waarde));
                gekozen.add(button9);
                break;
            case 10:
                button10.setText(Integer.toString(waarde));
                gekozen.add(button10);
                break;
            case 11:
                button11.setText(Integer.toString(waarde));
                gekozen.add(button11);
                break;
            case 12:
                button12.setText(Integer.toString(waarde));
                gekozen.add(button12);
                break;
            case 13:
                button13.setText(Integer.toString(waarde));
                gekozen.add(button13);
                break;
            case 14:
                button14.setText(Integer.toString(waarde));
                gekozen.add(button14);
                break;
            case 15:
                button15.setText(Integer.toString(waarde));
                gekozen.add(button15);
                break;
            case 16:
                button16.setText(Integer.toString(waarde));
                gekozen.add(button16);
                break;
            default:
                break;
        }

        if(waardes.size()==2){

            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        System.out.println("sleep");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    System.out.println("awake");
                    resetKeuzes();


                }
            });
            new Thread(sleeper).start();

        }
    }
    public void listen(){

        new Thread(listenerHelper).start();


    }
    public void setLabel(){
        statuslabel.setText("het is jouw beurt");
    }




}
