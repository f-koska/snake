package sample;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class AddToRanking extends Scene {
    TextField textField;

    public AddToRanking(GridPane gridPane,Model model){
        super(gridPane,200,100);
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        Label labelName = new Label("Put your name");
        TextField textField = new TextField();
        textField.setPrefSize(80,25);
        this.textField=textField;

        Label labelScore = new Label("Your score: " + model.Score);
        Button buttonAddToRanking = new Button("Confirm");

        gridPane.add(labelName,0,0);
        gridPane.add(textField,1,0);
        gridPane.add(labelScore,0,1);
        buttonAddToRanking.setOnMouseClicked(mouseEvent -> {
            Player player = new Player(textField.getText(),model.points());
            try {
                FileOutputStream fileOutputStream = new FileOutputStream("Ranking.txt", true);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(player);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage)this.getWindow();
            stage.setScene(new Scene(parent,600,400));
            stage.show();

        });
        gridPane.add(buttonAddToRanking,0,2);


    }


}
