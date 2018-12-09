package sample;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameviewController implements Initializable {
    public Counter impl;
    public int gameIndex;
    public int viewerId;
    public ListenHelperViewer listenHelperViewer;

    public ArrayList<Button> gekozen;
    public ArrayList<Integer> waardes;

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

    public int aantalgeradenParen;


    public void view(){
        try{
            gameIndex=impl.getGame(0);
            System.out.println("watchgame: "+gameIndex);
            viewerId=impl.getViewerId(gameIndex);
            waardes=new ArrayList<>();
            gekozen=new ArrayList<>();
            aantalgeradenParen=0;
        }
        catch (Exception e){

        }
        startWatching();

    }
    public void setInterface(Counter impl){
        this.impl=impl;
    }
    public void setListenHelperViewer(ListenHelperViewer listenHelperViewer){
            this.listenHelperViewer=listenHelperViewer;
    }
    public void setPlaats(int plaats, int waarde){
        switch (plaats) {
            case 1:
                button1.setText(Integer.toString(waarde));
                break;
            case 2:
                button2.setText(Integer.toString(waarde));
                break;
            case 3:
                button3.setText(Integer.toString(waarde));
                break;
            case 4:
                button4.setText(Integer.toString(waarde));
                break;
            case 5:
                button5.setText(Integer.toString(waarde));
                break;
            case 6:
                button6.setText(Integer.toString(waarde));
                break;
            case 7:
                button7.setText(Integer.toString(waarde));
                break;
            case 8:
                button8.setText(Integer.toString(waarde));
                break;
            case 9:
                button9.setText(Integer.toString(waarde));
                break;
            case 10:
                button10.setText(Integer.toString(waarde));
                break;
            case 11:
                button11.setText(Integer.toString(waarde));
                break;
            case 12:
                button12.setText(Integer.toString(waarde));
                break;
            case 13:
                button13.setText(Integer.toString(waarde));
                break;
            case 14:
                button14.setText(Integer.toString(waarde));
                break;
            case 15:
                button15.setText(Integer.toString(waarde));
                break;
            case 16:
                button16.setText(Integer.toString(waarde));
                break;
            default:
                break;
        }

    }
    public void startWatching(){
        try {
            ArrayList<ArrayList<Integer>> temp = impl.getReedsGezet(gameIndex);
            for (ArrayList<Integer> a : temp) {
                System.out.println("hier");
                int value=a.get(0);
                int plaats1=a.get(1)+1;
                System.out.println("plaats1"+plaats1);
                int plaats2=a.get(2)+1;
                System.out.println("plaats2"+plaats2);

                setPlaats(plaats1, value);
                setPlaats(plaats2, value);
            }
            listenHelperViewer.setGame(gameIndex);
            listenHelperViewer.setViewerId(viewerId);
            Listen();

        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void Listen(){
        new Thread(listenHelperViewer).start();
    }


    public void incomingGok(int []gok) {
        int button = gok[0];
        button++;
        int waarde = gok[1];
        waardes.add(waarde);
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

        if (waardes.size() == 2) {

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
    public void resetKeuzes(){
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
                    }
                });

        }
    }
    public void kaartenOmdraaien(){

        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                for (Button b: gekozen) {
                    b.setText("*");
                }
            }
        });
        new Thread(sleeper).start();

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
