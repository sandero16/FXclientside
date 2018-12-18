package sample;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameviewController implements Initializable {
    public DispatchingInterface impl;
    public Counter counterImpl;
    public int gameIndex;
    public int viewerId;
    public ListenHelperViewer listenHelperViewer;
    public ArrayList<ImageView> views;
    public ArrayList<Image> images;
    public ArrayList<ImageView> gekozen;
    public ArrayList<Integer> waardes;

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

    public int aantalgeradenParen;

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

    public void view(){
        try{
            int serverAdres=impl.getGameServer();
            if(serverAdres==0){
                System.out.println("helaas geen games beschikbaar");
            }
            else {
                Registry myRegistry = LocateRegistry.getRegistry("localhost", serverAdres);
// search for CounterService
                counterImpl = (Counter) myRegistry.lookup("Login");
                listenHelperViewer.addImpl(counterImpl);

                ///
                counterImpl.testConnectie();
                ///
                gameIndex=counterImpl.getGame(0);
                System.out.println("watchgame: " + gameIndex);
                viewerId = counterImpl.getViewerId(gameIndex);
                waardes = new ArrayList<>();
                gekozen = new ArrayList<>();
                aantalgeradenParen = 0;
            }
        }
        catch (Exception e){

        }
        startWatching();

    }
    public void setInterface(DispatchingInterface impl){
        this.impl=impl;
    }
    public void setListenHelperViewer(ListenHelperViewer listenHelperViewer){
            this.listenHelperViewer=listenHelperViewer;
    }
    public void setPlaats(int plaats, int waarde){
        switch (plaats) {
            case 1:
                iv1.setImage(images.get(waarde));
                break;
            case 2:
                iv2.setImage(images.get(waarde));
                break;
            case 3:
                iv3.setImage(images.get(waarde));
                break;
            case 4:
                iv4.setImage(images.get(waarde));
                break;
            case 5:
                iv5.setImage(images.get(waarde));
                break;
            case 6:
                iv6.setImage(images.get(waarde));
                break;
            case 7:
                iv7.setImage(images.get(waarde));
                break;
            case 8:
                iv8.setImage(images.get(waarde));
                break;
            case 9:
                iv9.setImage(images.get(waarde));
                break;
            case 10:
                iv10.setImage(images.get(waarde));
                break;
            case 11:
                iv11.setImage(images.get(waarde));
                break;
            case 12:
                iv12.setImage(images.get(waarde));
                break;
            case 13:
                iv13.setImage(images.get(waarde));
                break;
            case 14:
                iv14.setImage(images.get(waarde));
                break;
            case 15:
                iv15.setImage(images.get(waarde));
                break;
            case 16:
                iv16.setImage(images.get(waarde));
                break;
            default:
                break;
        }

    }
    public void startWatching(){
        try {
            ArrayList<ArrayList<Integer>> temp = counterImpl.getReedsGezet(gameIndex);
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
                        for (ImageView iw : gekozen) {
                            iw.setImage(images.get(8));
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
                for (ImageView iw : gekozen) {
                    iw.setImage(images.get(8));
                }
            }
        });
        new Thread(sleeper).start();

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeImages();
    }
}
