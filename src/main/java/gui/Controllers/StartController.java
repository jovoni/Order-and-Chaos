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
        Parent root;
        try {
            root = loader.load();
            Node node = (Node) mouseEvent.getSource();
            ((Stage) node.getScene().getWindow()).close();

            Scene scene = new Scene(root, 600, 600);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Order and Chaos");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onMouseClickedRules() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Rules.fxml"));
        Parent root;
        try {
            root = loader.load();
            Scene scene = new Scene(root, 600, 600);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Order and Chaos");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
