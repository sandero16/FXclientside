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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class GameWindowController implements Initializable {
    public Counter impl;
    public boolean beurt;
    public ArrayList<Integer> waardes;
    public ArrayList<Integer>geraden;
    public ArrayList<ImageView> gekozen;

    public ArrayList<ImageView> views;
    public ArrayList<Image> images;
    public int aantalgeradenParen;
    public int aantalKeuzes;
    public String sessionToken;
    public ListenerHelper listenerHelper;
    public int score;


    public Label statuslabel;
    public ImageView iv1;
    public ImageView iv2;
    public ImageView iv3;
    public ImageView iv4;
    public ImageView iv5;
    public ImageView iv6;
    public ImageView iv7;
    public ImageView iv8;
    public ImageView iv9;
    public ImageView iv10;
    public ImageView iv11;
    public ImageView iv12;
    public ImageView iv13;
    public ImageView iv14;
    public ImageView iv15;
    public ImageView iv16;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeImages();
    }
    private void initializeImages() {
        views = new ArrayList<ImageView>();
        images = new ArrayList<Image>(); // 8 images voor 16 plaatsen

        views.add(iv1);
        views.add(iv2);
        views.add(iv3);
        views.add(iv4);
        views.add(iv5);
        views.add(iv6);
        views.add(iv7);
        views.add(iv8);
        views.add(iv9);
        views.add(iv10);
        views.add(iv11);
        views.add(iv12);
        views.add(iv13);
        views.add(iv14);
        views.add(iv15);
        views.add(iv16);

        File duck1 = new File("src/Images/duck1.png");
        File duck2 = new File("src/Images/duck2.png");
        File horse = new File("src/Images/horse.png");
        File kitten = new File("src/Images/kitten.png");
        File pig = new File("src/Images/pig.png");
        File bird = new File("src/Images/bird.png");
        File cow = new File("src/Images/cow.png");
        File rabbit = new File("src/Images/rabbit.png");
        File question = new File("src/Images/question.jpg");

        images.add(new Image(duck1.toURI().toString()));
        images.add(new Image(duck2.toURI().toString()));
        images.add(new Image(horse.toURI().toString()));
        images.add(new Image(kitten.toURI().toString()));
        images.add(new Image(pig.toURI().toString()));
        images.add(new Image(bird.toURI().toString()));
        images.add(new Image(cow.toURI().toString()));
        images.add(new Image(rabbit.toURI().toString()));
        images.add(new Image(question.toURI().toString()));
        System.out.println(images.size());
        System.out.println(views.size());


        for(ImageView iv: views) {
            Image i = images.get(8);
            iv.setImage(i);
        }
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
    public void image1Clicked(){
        if(beurt) {
            gekozen.add(iv1);
            iv1.setImage(images.get(zet(1)));
        }

    }
    public void image2Clicked(){
        if(beurt) {
            gekozen.add(iv2);
            iv2.setImage(images.get(zet(2)));
        }

    }
    public void image3Clicked(){
        if(beurt) {
            gekozen.add(iv3);
            iv3.setImage(images.get(zet(3)));
        }

    }
    public void image4Clicked(){
        if(beurt) {
            gekozen.add(iv4);
            iv4.setImage(images.get(zet(4)));
        }

    }
    public void image5Clicked(){
        if(beurt) {
            gekozen.add(iv5);
            iv5.setImage(images.get(zet(5)));
        }

    }
    public void image6Clicked(){
        if(beurt) {
            gekozen.add(iv6);
            iv6.setImage(images.get(zet(6)));
        }

    }
    public void image7Clicked(){
        if(beurt) {
            gekozen.add(iv7);
            iv7.setImage(images.get(zet(7)));
        }

    }
    public void image8Clicked(){
        if(beurt) {
            gekozen.add(iv8);
            iv8.setImage(images.get(zet(8)));
        }

    }
    public void image9Clicked(){
        if(beurt) {
            gekozen.add(iv9);
            iv9.setImage(images.get(zet(9)));
        }

    }
    public void image10Clicked(){
        if(beurt) {
            gekozen.add(iv10);
            iv10.setImage(images.get(zet(10)));
        }

    }
    public void image11Clicked(){
        if(beurt) {
            gekozen.add(iv11);
            iv11.setImage(images.get(zet(11)));
        }

    }
    public void image12Clicked(){
        if(beurt) {
            gekozen.add(iv12);
            iv12.setImage(images.get(zet(12)));
        }

    }
    public void image13Clicked(){
        if(beurt) {
            gekozen.add(iv13);
            iv13.setImage(images.get(zet(13)));
        }

    }
    public void image14Clicked(){
        if(beurt) {
            gekozen.add(iv14);
            iv14.setImage(images.get(zet(14)));
        }

    }
    public void image15Clicked(){
        if(beurt) {
            gekozen.add(iv15);
            iv15.setImage(images.get(zet(15)));
        }

    }
    public void image16Clicked(){
        if(beurt) {
            gekozen.add(iv16);
            iv16.setImage(images.get(zet(16)));
        }

    }

    public int zet(int i){
        try {
            if (beurt) {
                aantalKeuzes++;
                int temp=impl.getZet(i, sessionToken);
                if(temp>100){
                    int nieuwAdres=temp;
                    System.out.println("nieuw adres"+temp);
                    Registry myRegistry = LocateRegistry.getRegistry("localhost", nieuwAdres);
// search for CounterService
                    impl= (Counter) myRegistry.lookup("Login");
                    impl.testConnectie();
                    temp=impl.getZet(i, sessionToken);

                }
                waardes.add(temp);
                System.out.println("aantalkeuzes: "+aantalKeuzes);
                if(!checkBonusZet()){
                    if(aantalKeuzes==2) {
                        impl.changeBeurt(sessionToken);
                    }
                }
                //TODO hier poortnummer terug geven
                impl.geefNotify(sessionToken);

                changeState();

                return temp;
            }
            else return 8;
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        return 8;
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
                for (ImageView iv : gekozen) {
                    System.out.println("button");
                    iv.setImage(images.get(8));
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
                for (ImageView iv : gekozen) {
                    System.out.println("button");
                    iv.setImage(images.get(8));
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
                iv1.setImage(images.get(waarde));
                gekozen.add(iv1);
                break;
            case 2:
                iv2.setImage(images.get(waarde));
                gekozen.add(iv2);
                break;
            case 3:
                iv3.setImage(images.get(waarde));
                gekozen.add(iv3);
                break;
            case 4:
                iv4.setImage(images.get(waarde));
                gekozen.add(iv4);
                break;
            case 5:
                iv5.setImage(images.get(waarde));
                gekozen.add(iv5);
                break;
            case 6:
                iv6.setImage(images.get(waarde));
                gekozen.add(iv6);
                break;
            case 7:
                iv7.setImage(images.get(waarde));
                gekozen.add(iv7);
                break;
            case 8:
                iv8.setImage(images.get(waarde));
                gekozen.add(iv8);
                break;
            case 9:
                iv9.setImage(images.get(waarde));
                gekozen.add(iv9);
                break;
            case 10:
                iv10.setImage(images.get(waarde));
                gekozen.add(iv10);
                break;
            case 11:
                iv11.setImage(images.get(waarde));
                gekozen.add(iv11);
                break;
            case 12:
                iv12.setImage(images.get(waarde));
                gekozen.add(iv12);
                break;
            case 13:
                iv13.setImage(images.get(waarde));
                gekozen.add(iv13);
                break;
            case 14:
                iv14.setImage(images.get(waarde));
                gekozen.add(iv14);
                break;
            case 15:
                iv15.setImage(images.get(waarde));
                gekozen.add(iv15);
                break;
            case 16:
                iv16.setImage(images.get(waarde));
                gekozen.add(iv16);
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
                        if(gok[0]>100){
                            int nieuwAddres=gok[0];
                            System.out.println("het nieuwe adress is: "+nieuwAddres);
                            Registry myRegistry = LocateRegistry.getRegistry("localhost", nieuwAddres);
// search for CounterService
                            impl= (Counter) myRegistry.lookup("Login");

                             gok=impl.getAndereGok(sessionToken);
                        }
                        int[]finalGok=gok;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                incomingGok(finalGok);
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
