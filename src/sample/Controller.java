package sample;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Controller{
    public Stage stage;

    public Model model;
@FXML
    private TextField Columns;
@FXML
    private TextField Rows;
@FXML
    private Label errorLabel;
@FXML
    private ListView<Player> playerList;

int counter;
String input="";


public Controller(){
    this.model= new Model();
    this.stage= new Stage();
    counter=0;


}

@FXML
    public void newGame(ActionEvent event){
    Parent parent = null;
    try {
        parent = FXMLLoader.load(getClass().getResource("Game.fxml"));
    } catch (IOException e) {
        e.printStackTrace();
    }
    Scene scene = new Scene(parent);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(scene);
    window.show();



}

@FXML
    public void highScores(ActionEvent event){
    List<Player> players = new ArrayList<>();

    FileInputStream fileInputStream = null;
    ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("Ranking.txt");
            objectInputStream = new ObjectInputStream(fileInputStream);
            while (fileInputStream.available() != 0) {
                Player player = (Player) objectInputStream.readObject();
                players.add(player);
            }
            fileInputStream.close();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        players.sort(Comparator.comparing(Player::getPoints));


    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HighScores.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Controller controller = fxmlLoader.getController();
        ObservableList<Player> playerObservableList = FXCollections.observableList(players);
        controller.playerList.setItems(playerObservableList);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    } catch (IOException e) {
        e.printStackTrace();
    }


}

@FXML
    public void exit(){
    Platform.exit();
    System.exit(0);


}

@FXML
    public void confirm(ActionEvent event){
    if(Integer.parseInt(Rows.getText()) < 4 || Integer.parseInt(Columns.getText()) < 4 ){
        errorLabel.setText("The number of rows and columns must be bigger than 4.");
    }
    else {
        System.out.println("snake");
        model.rows = Integer.parseInt(Rows.getText());
        model.columns = Integer.parseInt(Columns.getText());
        Snake snake = new Snake(new BorderPane(),this,model);

        GameOver gameOver = new GameOver(model,snake);
        Thread threadGameOver = new Thread(gameOver);
        threadGameOver.start();

        Time time = new Time(snake,model);
        Thread threadTime = new Thread(time);
        threadTime.start();

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(snake);
        window.show();


        snake.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String string = String.valueOf(KeyCode.CONTROL) + KeyCode.SHIFT + KeyCode.A;

                if(keyEvent.getCode()== KeyCode.DOWN && model.list.get(0).direction!=Direction.Up){
                    model.list.get(0).direction=Direction.Down;
                }
                if(keyEvent.getCode()== KeyCode.RIGHT && model.list.get(0).direction!=Direction.Left){
                    model.list.get(0).direction=Direction.Right;
                }
                if(keyEvent.getCode()== KeyCode.UP && model.list.get(0).direction!=Direction.Down){
                    model.list.get(0).direction=Direction.Up;
                }
                if(keyEvent.getCode()== KeyCode.LEFT && model.list.get(0).direction!=Direction.Right){
                    model.list.get(0).direction=Direction.Left;
                }
                switch (counter) {

                    case 0:
                        if (keyEvent.getCode() == KeyCode.CONTROL) {
                            input += KeyCode.CONTROL;
                            counter++;
                            System.out.println(input);
                        } else {
                            input = "";
                        }
                        break;

                    case 1:
                        if (keyEvent.getCode() == KeyCode.SHIFT) {
                            input += KeyCode.SHIFT;
                            counter++;
                            System.out.println(input);
                        } else {
                            input = "";
                            counter = 0;
                        }
                        break;

                    case 2:
                        if (keyEvent.getCode() == KeyCode.A) {
                            input += KeyCode.A;
                            System.out.println(input);
                        } else {
                            input = "";
                        }
                        counter = 0;
                        break;
                }

                        if (string.equals(input)) {
                            snake.timeline.stop();
                        Parent parent = null;
                        try {
                            parent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Stage st = (Stage) snake.getWindow();
                        st.close();
                        Scene scene = new Scene(parent);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();

                    }


            }
        });

    }

}



}
