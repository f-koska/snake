package sample;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Model {
    public int columns;
    public int rows;
    List<Points> list = new ArrayList();
    List <Points> points = new ArrayList<>();
    boolean gameOver;
    int Score;

    int counterInc;
    double foodIncX;
    double foodIncY;
    Circle circleInc = new Circle(foodIncX+12.5,foodIncY+12.5,10);

    double foodShoX;
    double foodShoY;
    Circle circleSho = new Circle(foodShoX+12.5,foodShoY+12.5,10);

    int seconds;
    int minutes;
    int hours;

    boolean tmp;


    public Model(){

        list.add(new Points(0,25));

        list.get(0).direction=Direction.Right;
        gameOver=false;

        counterInc=2;

        tmp=false;


    }

    public void increment(){
        Score++;
        switch (list.get(0).direction) {
            case Down:
            list.add(new Points(list.get(list.size() - 1).x, list.get(list.size() - 1).y-25));
            break;
            case Right:
                list.add(new Points(list.get(list.size() - 1).x-25, list.get(list.size() - 1).y));
                break;
            case Up:
                list.add(new Points(list.get(list.size() - 1).x, list.get(list.size() - 1).y+25));
                break;
            case Left:
                list.add(new Points(list.get(list.size() - 1).x+25, list.get(list.size() - 1).y));
                break;


        }


    }
    public void decrement(){
        Score--;
        if(list.size()>1) {
            list.remove(list.size() - 1);
        }
    }

    public double points(){
        double points = Score+ (Score/(hours*3600+minutes*60+seconds))*100;

    return points;}

    public void addToRanking(Snake snake){
        AddToRanking addToRanking = new AddToRanking(new GridPane(),this);
        Stage stage = (Stage) snake.getWindow();
        stage.close();
        Stage st = new Stage();
        st.setScene(addToRanking);
        st.show();

    }







}
