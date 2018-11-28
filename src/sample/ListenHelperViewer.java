package sample;

import javafx.application.Platform;

public class ListenHelperViewer implements Runnable {
    GameviewController gameviewController;
    Counter impl;
    int viewerId;
    int game;

    public ListenHelperViewer(GameviewController gameviewController, Counter impl) {
        this.gameviewController = gameviewController;
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
                if (gok != null) {
                    System.out.println("gok gevonden");
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            gameviewController.incomingGok(gok);
                        }
                    });
                    Thread.sleep(800);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
