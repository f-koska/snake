package sample;


import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Snake extends Scene {
    Controller controller;
    Canvas canvas;
    GraphicsContext graphicsContext;
    Label labelPoints;
    Label labelHours;
    Label labelMinutes;
    Label labelSeconds;
    Model model;
    BorderPane borderPane;
    Pane pane;
    boolean resultInc;
    boolean resultSho;
    Timeline timeline;





    public Snake(BorderPane borderPane,Controller controller, Model model) {
        super(borderPane, 25 * model.columns, 25 * model.rows + 60);
        this.controller = controller;
        this.model = model;
        resultInc = false;
        resultSho = false;


        double length = 25;
        int x = 0;
        int y = 0;
        Canvas canvas1 = new Canvas(model.columns * 25, model.rows * 25+25);
        GraphicsContext graphicsContext1 = canvas1.getGraphicsContext2D();
        Canvas canvas2 = new Canvas(model.columns * 25, model.rows * 25+25);
        this.canvas = canvas2;
        GraphicsContext graphicsContext2 = canvas2.getGraphicsContext2D();
        graphicsContext2.setFill(Color.RED);

        Pane pane = new Pane();
        this.pane = pane;
        pane.getChildren().add(canvas1);
        pane.getChildren().add(canvas2);
        borderPane.setCenter(pane);
        canvas2.toFront();

        this.graphicsContext = graphicsContext2;


        for (int i = 0; i < controller.model.columns * controller.model.rows; i++, x += length) {
            if (i % controller.model.columns == 0) {
                x = 0;
                y += length;
            }
            if (i % 2 == 0) {
                graphicsContext1.setFill(Color.LIMEGREEN);
                graphicsContext1.fillRect(x, y, length, length);
                model.points.add(new Points(x, y));


            } else {
                graphicsContext1.setFill(Color.LIGHTGREEN);
                graphicsContext1.fillRect(x, y, length, length);
                model.points.add(new Points(x, y));
            }

        }
        graphicsContext.setFill(Color.RED);

        this.borderPane = borderPane;


        borderPane.setStyle("-fx-background-color: lightgrey");
        HBox hBox = new HBox();
        borderPane.setTop(hBox);

        Label label1 = new Label("Score: ");
        label1.setTextFill(Color.rgb(166,141,39));
        label1.setFont(Font.font("Verdana", FontWeight.BOLD,20));
        Label labelPoints = new Label();
        labelPoints.setFont(Font.font("Verdana", FontWeight.BOLD,20));
        this.labelPoints = labelPoints;
        hBox.getChildren().add(label1);
        hBox.getChildren().add(labelPoints);



        Label labelHours = new Label();
        this.labelHours = labelHours;
        labelHours.setFont(Font.font("Verdana", FontWeight.BOLD,20));
        labelHours.setText("0");
        Label label2 = new Label(":");
        label2.setFont(Font.font("Verdana", FontWeight.BOLD,20));
        Label labelMinutes = new Label();
        labelMinutes.setFont(Font.font("Verdana", FontWeight.BOLD,20));
        this.labelMinutes = labelMinutes;
        labelMinutes.setText("0");
        Label label3 = new Label(":");
        label3.setFont(Font.font("Verdana", FontWeight.BOLD,20));
        Label labelSeconds = new Label();
        labelSeconds.setFont(Font.font("Verdana", FontWeight.BOLD,20));
        this.labelSeconds = labelSeconds;

        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        hBox.getChildren().add(region);


        hBox.getChildren().add(labelHours);
        hBox.getChildren().add(label2);
        hBox.getChildren().add(labelMinutes);
        hBox.getChildren().add(label3);
        hBox.getChildren().add(labelSeconds);


        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(0), actionEvent -> {

            switch (model.list.get(0).direction) {
                case Down:

                    model.list.get(0).earlierDirection = model.list.get(0).direction;
                    model.list.get(0).direction = Direction.Down;
                    model.list.get(0).earlierX = model.list.get(0).x;
                    model.list.get(0).earlierY = model.list.get(0).y;
                    model.list.get(0).y += 25;
                    for (int i = 0; i < model.list.size() - 1; i++) {
                        model.list.get(i + 1).earlierX = model.list.get(i + 1).x;
                        model.list.get(i + 1).earlierY = model.list.get(i + 1).y;
                        model.list.get(i + 1).earlierDirection = model.list.get(i + 1).direction;
                        model.list.get(i + 1).direction = model.list.get(i).earlierDirection;
                        model.list.get(i + 1).x = model.list.get(i).earlierX;
                        model.list.get(i + 1).y = model.list.get(i).earlierY;

                    }
                    for (int i = 0; i < model.list.size(); i++) {
                        graphicsContext.fillRoundRect(model.list.get(i).x, model.list.get(i).y, 25, 25, 10, 10);
                        graphicsContext.clearRect(model.list.get(i).earlierX, model.list.get(i).earlierY, 25, 25);
                    }
                    break;

                case Right:

                    model.list.get(0).earlierDirection = model.list.get(0).direction;
                    model.list.get(0).direction = Direction.Right;
                    model.list.get(0).earlierX = model.list.get(0).x;
                    model.list.get(0).earlierY = model.list.get(0).y;
                    model.list.get(0).x += 25;
                    for (int i = 0; i < model.list.size() - 1; i++) {
                        model.list.get(i + 1).earlierX = model.list.get(i + 1).x;
                        model.list.get(i + 1).earlierY = model.list.get(i + 1).y;
                        model.list.get(i + 1).earlierDirection = model.list.get(i + 1).direction;
                        model.list.get(i + 1).direction = model.list.get(i).earlierDirection;
                        model.list.get(i + 1).x = model.list.get(i).earlierX;
                        model.list.get(i + 1).y = model.list.get(i).earlierY;

                    }
                    for (int i = 0; i < model.list.size(); i++) {
                        graphicsContext.fillRoundRect(model.list.get(i).x, model.list.get(i).y, 25, 25, 10, 10);
                        graphicsContext.clearRect(model.list.get(i).earlierX, model.list.get(i).earlierY, 25, 25);
                    }
                    break;

                case Up:

                    model.list.get(0).earlierDirection = model.list.get(0).direction;
                    model.list.get(0).direction = Direction.Up;
                    model.list.get(0).earlierX = model.list.get(0).x;
                    model.list.get(0).earlierY = model.list.get(0).y;
                    model.list.get(0).y -= 25;
                    for (int i = 0; i < model.list.size() - 1; i++) {
                        model.list.get(i + 1).earlierX = model.list.get(i + 1).x;
                        model.list.get(i + 1).earlierY = model.list.get(i + 1).y;
                        model.list.get(i + 1).earlierDirection = model.list.get(i + 1).direction;
                        model.list.get(i + 1).direction = model.list.get(i).earlierDirection;
                        model.list.get(i + 1).x = model.list.get(i).earlierX;
                        model.list.get(i + 1).y = model.list.get(i).earlierY;

                    }
                    for (int i = 0; i < model.list.size(); i++) {
                        graphicsContext.fillRoundRect(model.list.get(i).x, model.list.get(i).y, 25, 25, 10, 10);
                        graphicsContext.clearRect(model.list.get(i).earlierX, model.list.get(i).earlierY, 25, 25);
                    }
                    break;

                case Left:

                    model.list.get(0).earlierDirection = model.list.get(0).direction;
                    model.list.get(0).direction = Direction.Left;
                    model.list.get(0).earlierX = model.list.get(0).x;
                    model.list.get(0).earlierY = model.list.get(0).y;
                    model.list.get(0).x -= 25;
                    for (int i = 0; i < model.list.size() - 1; i++) {
                        model.list.get(i + 1).earlierX = model.list.get(i + 1).x;
                        model.list.get(i + 1).earlierY = model.list.get(i + 1).y;
                        model.list.get(i + 1).earlierDirection = model.list.get(i + 1).direction;
                        model.list.get(i + 1).direction = model.list.get(i).earlierDirection;
                        model.list.get(i + 1).x = model.list.get(i).earlierX;
                        model.list.get(i + 1).y = model.list.get(i).earlierY;

                    }
                    for (int i = 0; i < model.list.size(); i++) {
                        graphicsContext.fillRoundRect(model.list.get(i).x, model.list.get(i).y, 25, 25, 10, 10);
                        graphicsContext.clearRect(model.list.get(i).earlierX, model.list.get(i).earlierY, 25, 25);
                    }
                    break;


            }if(!resultInc){
                resultInc=true;
                model.circleInc = newFoodInc(model);
                model.circleInc.setFill(Color.ROYALBLUE);

                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2),model.circleInc);
                fadeTransition.setAutoReverse(true);
                fadeTransition.setFromValue(1.0);
                fadeTransition.setToValue(0.65);
                fadeTransition.setCycleCount(Animation.INDEFINITE);
                fadeTransition.play();
                pane.getChildren().add(model.circleInc);

            }if(model.list.get(0).x==model.foodIncX && model.list.get(0).y==model.foodIncY){
                System.out.println(model.counterInc);
                model.increment();
                labelPoints.setText(""+model.Score);
                pane.getChildren().remove(model.circleInc);
                resultInc=false;
                if(!resultSho) {
                    model.counterInc--;
                }

            }
            if(!resultSho && model.counterInc==0){
                resultSho=true;

                    model.circleSho = newFoodSho(model);
                    model.circleSho.setFill(Color.CHOCOLATE);

                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2),model.circleSho);
                fadeTransition.setAutoReverse(true);
                fadeTransition.setFromValue(1.0);
                fadeTransition.setToValue(0.65);
                fadeTransition.setCycleCount(Animation.INDEFINITE);
                fadeTransition.play();
                pane.getChildren().add(model.circleSho);

            }if(model.list.get(0).x==model.foodShoX && model.list.get(0).y==model.foodShoY){
                if(model.list.size()>1) {
                    graphicsContext.clearRect(model.list.get(model.list.size() - 1).x, model.list.get(model.list.size() - 1).y, 25, 25); }
                model.decrement();
                model.counterInc=(int)(Math.random()*10);
                labelPoints.setText(""+model.Score);
                pane.getChildren().remove(model.circleSho);
                resultSho=false;

            }if(model.gameOver){this.timeline.stop();
                gameOver(pane);
                if(model.tmp){
                    AddToRanking addToRanking = new AddToRanking(new GridPane(),model);
                    Stage st = (Stage) this.getWindow();
                    st.close();
                    Stage stage = new Stage();
                    stage.setScene(addToRanking);
                    stage.show();
                }

            }


        }


        ), new KeyFrame(Duration.millis(500)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        this.timeline=timeline;

    }



    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public  static Circle newFoodInc(Model model){
        int index=0;
        index = (int)(Math.random()*model.points.size());

        Circle circle = new Circle(model.points.get(index).x+12.5,model.points.get(index).y+12.5,10);
        model.foodIncX=model.points.get(index).x;
        model.foodIncY=model.points.get(index).y;
        return circle;
    }

    public  static Circle newFoodSho(Model model){
        int index=0;
        index = (int)(Math.random()*model.points.size());

        Circle circle = new Circle(model.points.get(index).x+12.5,model.points.get(index).y+12.5,10);
        model.foodShoX=model.points.get(index).x;
        model.foodShoY=model.points.get(index).y;
        return circle;
    }

    public static Label gameOver(Pane pane){
        Label label = new Label("Game Over !");
        label.setTextFill(Color.GOLDENROD);
        label.setStyle("-fx-font-size: 40;-fx-font: italic; -fx-background-color: darkgreen;  -fx-stroke: black; -fx-stroke-width: 5;");
        label.setLayoutX(pane.getWidth()/4);
        label.setLayoutY(pane.getHeight()/4);
        pane.getChildren().add(label);
        return label;
    }










}
