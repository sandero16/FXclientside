package sample;

import javafx.application.Platform;

public class waitingForPlayer implements Runnable {
    waitingRoomController controller;
    Counter impl;
    String sessionToken;
    int aantalspelers;

    public waitingForPlayer(waitingRoomController w, Counter impl, String sessionToken, int aantalspelers) {
        controller=w;
        this.impl = impl;
        this.sessionToken = sessionToken;
        this.aantalspelers = aantalspelers;


    }

    @Override
    public void run() {
        try {
            System.out.println("running");
            while (!impl.vindtTegenspeler(sessionToken)) {
                    Thread.sleep(2000);
                    System.out.println("sleept");
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //controller.playerGevonden(sessionToken);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();

            // Prints what exception has been thrown
            System.out.println(e);
        }
    }
}
