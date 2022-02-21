package gui.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class StartController {

    public void onMouseClickedPlay(MouseEvent event) {
        loadingFXML(event, "/fxml/Root.fxml",550, 500 );
    }

    public void onMouseClickedRules(MouseEvent event) {
       loadingFXML(event, "/fxml/Rules.fxml", 400, 600);
    }

    private void loadingFXML(MouseEvent event, String path, int minHeight, int minWidth){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root;
        try {
            root = loader.load();
            Node node = (Node) event.getSource();

            if(path.contains("Root")){
                ((Stage) node.getScene().getWindow()).close();
            }

            Scene scene = new Scene(root, node.getScene().getWidth(), node.getScene().getHeight());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/style.css")).toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setMinWidth(minWidth);
            stage.setMinHeight(minHeight);
            stage.setTitle("Order and Chaos");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}