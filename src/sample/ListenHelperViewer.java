package sample;

import javafx.application.Platform;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ListenHelperViewer implements Runnable {
    GameviewController gameviewController;
    Counter impl;
    int viewerId;
    int game;

    public ListenHelperViewer(GameviewController gameviewController) {
        this.gameviewController = gameviewController;
    }
    public void addImpl(Counter impl){
        this.impl = impl;
    }
    public void setGame(int game) {
        this.game = game;
    }
    public void setViewerId(int viewerId){
        this.viewerId=viewerId;
    }

    @Override
    public void run() {

        try {
            System.out.println("buiten");
            while (!impl.getEnd(game)) {
                int[] gok = impl.getGameGok(game, viewerId);
                if(gok[0]>100){
                    int nieuwAddres=gok[0];
                    System.out.println("het nieuwe adress is: "+nieuwAddres);
                    Registry myRegistry = LocateRegistry.getRegistry("localhost", nieuwAddres);
// search for CounterService
                    impl= (Counter) myRegistry.lookup("Login");

                    gok=impl.getGameInhaalGok(game, viewerId);
                    ///
                    for (int i: gok) {
                        System.out.println("gok part: "+i);
                    }
                }
                int[] finalgok=gok;
                System.out.println("gok gevonden");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                            gameviewController.incomingGok(finalgok);
                        }
                });
                Thread.sleep(800);


            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
