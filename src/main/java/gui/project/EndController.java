package gui.project;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EndController {


    public void onMouseClickedPlay(MouseEvent mouseEvent) {
        Gui app = new Gui();
        try {
            app.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Node node = (Node) mouseEvent.getSource();
        ((Stage) node.getScene().getWindow()).close();
    }
}
