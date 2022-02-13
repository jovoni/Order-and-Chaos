package gui.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.*;

public class Gui extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        System.out.println(javafx.scene.text.Font.getFamilies());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Start.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Order and Chaos");
        stage.show();
    }
}