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
            impl.testConnectie();
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
        if(beurt) {
            gekozen.add(button1);
            button1.setText(Integer.toString(zet(1)));
        }

    }
    public void button2Clicked(){
        if(beurt) {
            gekozen.add(button2);
            button2.setText(Integer.toString(zet(2)));
        }
    }
    public void button3Clicked(){
        System.out.println(beurt);
            if(beurt) {
                gekozen.add(button3);
                button3.setText(Integer.toString(zet(3)));
            }

    }
    public void button4Clicked(){
        System.out.println(beurt);
            if(beurt) {
                gekozen.add(button4);
                button4.setText(Integer.toString(zet(4)));
            }
    }
    public void button5Clicked(){
            if(beurt) {
                gekozen.add(button5);
                button5.setText(Integer.toString(zet(5)));
            }
    }
    public void button6Clicked(){
            if(beurt) {
                gekozen.add(button6);
                button6.setText(Integer.toString(zet(6)));
            }
    }
    public void button7Clicked(){
            if(beurt) {
                gekozen.add(button7);
                button7.setText(Integer.toString(zet(7)));
            }
    }
    public void button8Clicked(){
            if(beurt) {
                gekozen.add(button8);
                button8.setText(Integer.toString(zet(8)));
            }
    }
    public void button9Clicked(){
            if(beurt) {
                gekozen.add(button9);
                button9.setText(Integer.toString(zet(9)));
            }
    }
    public void button10Clicked(){
            if(beurt) {
                gekozen.add(button10);
                button10.setText(Integer.toString(zet(10)));
            }
    }
    public void button11Clicked(){
            if(beurt) {
                gekozen.add(button11);
                button11.setText(Integer.toString(zet(11)));
            }
    }
    public void button12Clicked(){
            if(beurt) {
                gekozen.add(button12);
                button12.setText(Integer.toString(zet(12)));
            }
    }
    public void button13Clicked(){
            if(beurt) {
                gekozen.add(button13);
                button13.setText(Integer.toString(zet(13)));
            }
    }
    public void button14Clicked(){
            if(beurt) {
                gekozen.add(button14);
                button14.setText(Integer.toString(zet(14)));
            }
    }
    public void button15Clicked(){
            if(beurt) {
                gekozen.add(button15);
                button15.setText(Integer.toString(zet(15)));
            }
    }
    public void button16Clicked(){
            if(beurt) {
                gekozen.add(button16);
                button16.setText(Integer.toString(zet(16)));
            }
    }

    public int zet(int i){
        try {
            if (beurt) {
                aantalKeuzes++;
                int temp=impl.getZet(i, sessionToken);
                waardes.add(temp);
                System.out.println("aantalkeuzes: "+aantalKeuzes);
                if(!checkBonusZet()){
                    if(aantalKeuzes==2) {
                        impl.changeBeurt(sessionToken);
                    }
                }
                impl.geefNotify(sessionToken);
                changeState();

                return temp;
            }
            else return -1;
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        return -1;
    }
    public void setHelper(ListenerHelper listenerHelper){
        this.listenerHelper=listenerHelper;
    }
    public Boolean checkBonusZet(){
        if(aantalKeuzes==2){
            if(waardes.get(0)==waardes.get(1)){
                return true;
            }

        }
        return false;
    }
    public void changeState(){
        try {
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
            if (aantalKeuzes == 2) {
                //resetten keuzes
                aantalKeuzes = 0;
                System.out.println("waarde 1: "+waardes.get(0)+" waarde 2:"+waardes.get(1));
                if (waardes.get(0) == waardes.get(1)) {
                    score++;
                    aantalgeradenParen++;
                    System.out.println("aantalgeradenparen: "+aantalgeradenParen);
                    gekozen.clear();
                    waardes.clear();
                    if(aantalgeradenParen==8){
                        System.out.println("open window");
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                openEndWindow();
                            }
                        });

                    }
                } else {
                    System.out.println("het is aan de andere");
                    statuslabel.setText("het is aan de andere");
                    beurt=false;
                    new Thread(sleeper).start();
                    if (aantalgeradenParen != 8) {
                        listen();
                    }

                }
            }
        }
             catch (Exception e){
                e.printStackTrace();
                 System.out.println(e);
            }
        }
    public void setBeurt(){
        beurt=true;
    }
    public void resetKeuzes() {
        System.out.println("waardes: " + waardes.get(0) + " " + waardes.get(1));
        System.out.println(gekozen.size());
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (Button b : gekozen) {
                    System.out.println("button");
                    b.setText("*");
                }
                gekozen.clear();
            }
        });

        waardes.clear();
        System.out.println("aantalgeraden paren"+aantalgeradenParen);
        //check if the game is ended
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
    public void resetListenKeuzes() {
        System.out.println("waardes: " + waardes.get(0) + " " + waardes.get(1));
        System.out.println(gekozen.size());
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (Button b : gekozen) {
                    System.out.println("button");
                    b.setText("*");
                }
                gekozen.clear();
            }
        });
        waardes.clear();
    }
    public void clearWaardes(){
        System.out.println("hier aantalgeraden: "+aantalgeradenParen);
        gekozen.clear();
        System.out.println("aantalgeraden paren"+aantalgeradenParen);
        waardes.clear();
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
                    resetListenKeuzes();


                }
            });
            if(waardes.get(0)==waardes.get(1)){
                aantalgeradenParen++;
                clearWaardes();
            }
            else {
                new Thread(sleeper).start();
            }

        }
    }
    public void listen(){

        /*Task<Void> task = new Task<Void>() {
            @Override protected Void call() throws Exception {
                System.out.println("hier");
                impl.addToGame(sessionToken, aantalspelers);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        playerGevonden(sessionToken);
                    }
                });
                System.out.println("player gevonden");
                return null;
            }
        };
        Thread th = new Thread(task);

        th.setDaemon(true);

        th.start();
        System.out.println("zoeken naar ander"+ sessionToken);
    */


        ///////
        try {
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    while (!impl.checkBeurt(sessionToken)) {
                        int[] gok = impl.getAndereGok(sessionToken);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                incomingGok(gok);
                            }
                        });

                    }
                    System.out.println("change beurt");
                    setBeurt();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            setLabel();
                        }
                    });
                    return null;
                }
            };

            Thread th=new Thread(task);
            th.setDaemon(true);
            th.start();


        }
        catch (Exception e){
            System.out.println("this failed");
        }


    }
    public void setLabel(){
        statuslabel.setText("het is jouw beurt");
    }




}
