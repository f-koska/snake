package sample;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.util.Duration;


public class Time implements Runnable {
    Snake snake;
    Model model;

    public Time(Snake snake, Model model) {
        this.snake = snake;
        this.model=model;

    }


    @Override
    public void run() {
        while (!model.gameOver) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            Platform.runLater(()->{

                model.seconds++;
                snake.labelSeconds.setText("" +  model.seconds);
                if (model.seconds == 60) {
                    model.minutes++;
                    model.seconds = 0;
                    snake.labelSeconds.setText("" +  model.seconds);
                    snake.labelMinutes.setText("" + model.minutes);

                }

                if (model.minutes == 60) {
                    model.hours++;
                    model.minutes = 0;
                    snake.labelMinutes.setText("" + model.minutes);
                    snake.labelHours.setText("" + model.hours);

                }
                if(model.gameOver){Thread.currentThread().interrupt();}

            });


        }
    }
}

