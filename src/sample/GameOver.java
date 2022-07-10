package sample;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameOver implements Runnable {
    Model model;
    Snake snake;

    public GameOver(Model model, Snake snake) {
        this.model = model;
        this.snake=snake;

    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                if (model.list.size() == model.points.size()) {
                    model.gameOver = true;
                }

                if(model.list.size()>3) {
                    for (int i = 1; i < model.list.size(); i++) {
                        if (model.list.get(0).x == model.list.get(i).x && model.list.get(0).y == model.list.get(i).y) {
                            model.gameOver = true;
                        }
                    }
                }

                boolean result=false;

                for (Points points : model.points){
                    if(model.list.get(0).x == points.x && model.list.get(0).y == points.y){
                        result=true;
                    }
                }
                if(!result){ model.gameOver=true;
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }snake.timeline.play();
                    model.tmp=true;
                    Thread.currentThread().interrupt();
                }


        }
    }
}

