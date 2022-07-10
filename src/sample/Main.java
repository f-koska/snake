package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Controller controller = new Controller();
         this.primaryStage=primaryStage;
        controller.stage=primaryStage;

        Parent parent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        primaryStage.setScene(new Scene(parent,600,400));
        primaryStage.show();

    }


    public static void main(String[] args) {

        launch(args);

    }
}
