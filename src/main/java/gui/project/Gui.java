package gui.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Gui extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(Gui.class.getResource("/Root.fxml"));
        FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(getClass().getResource("Grid.fxml"));
        loader.setLocation(getClass().getResource("/Root.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Order and Chaos");
        stage.show();
    }


}