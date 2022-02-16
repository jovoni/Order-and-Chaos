package gui.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StartController  implements Initializable {

    public void onMouseClickedPlay(MouseEvent mouseEvent) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Root.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node node = (Node) mouseEvent.getSource();
        ((Stage) node.getScene().getWindow()).close();

        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("Order and Chaos");
        stage.show();
    }

    public void onMouseClickedRules(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Rules.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMinHeight(500);
        stage.setMinWidth(600);
        stage.setTitle("Order and Chaos");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
