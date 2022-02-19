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

    public void onMouseClickedPlay(MouseEvent event) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Root.fxml"));
        Parent root;
        try {
            root = loader.load();
            Node node = (Node) event.getSource();
            ((Stage) node.getScene().getWindow()).close();

            Scene scene = new Scene(root, node.getScene().getWidth(), node.getScene().getHeight());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/style.css")).toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setMinWidth(500);
            stage.setMinHeight(550);
            stage.setTitle("Order and Chaos");
//            Image applicationIcon = new Image(getClass().getResourceAsStream("/O.png"));
//            stage.getIcons().add(applicationIcon);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onMouseClickedRules(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Rules.fxml"));
        Parent root;
        try {
            root = loader.load();
            Scene scene = new Scene(root, ((Node)event.getSource()).getScene().getWidth(), ((Node)event.getSource()).getScene().getHeight());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/style.css")).toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setMinWidth(600);
            stage.setMinHeight(400);
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