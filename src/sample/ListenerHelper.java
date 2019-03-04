package sample;

import javafx.application.Platform;

public class ListenerHelper implements Runnable {
    GameWindowController gameWindowController;
    AppInterface impl;
    String sessionToken;


    public ListenerHelper(GameWindowController gameWindowController, AppInterface impl, String sessionToken){
        this.gameWindowController=gameWindowController;
        this.impl=impl;
        this.sessionToken=sessionToken;
    }

    public void run(){
        try {

            while (!impl.checkBeurt(sessionToken)) {
                int[] gok = impl.getAndereGok(sessionToken);
                if (gok != null) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            gameWindowController.incomingGok(gok);
                        }
                    });
                    Thread.sleep(800);
                }
            }
            gameWindowController.setBeurt();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    gameWindowController.setLabel();
                }
            });

        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
    }
    }
}
