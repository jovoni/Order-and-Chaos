package gui.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import orderandchaos.Entities.Piece;
import orderandchaos.Entities.Position;
import orderandchaos.Game;
import orderandchaos.Utils.Display;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GridController implements Initializable {
    @FXML
    public GridPane grid;
    RootController rootController;
    boolean started;

    public void injectMainController(RootController rootController) {
        this.rootController = rootController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        started = false;
        grid.setVgap(8);
        grid.setHgap(8);
        grid.setAlignment(Pos.CENTER);
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                Rectangle r = new Rectangle(col * 50, row * 50, 50, 50);
                r.setFill(Color.WHITE);
                r.setStroke(Color.rgb(216, 216, 216));
                r.setStrokeWidth(3);
                grid.addRow(row, r);
                r.setOnMouseClicked(this::clickGrid);
                r.setDisable(true);

            }
        }
    }

    String getSymbol() {
        return this.rootController.getSymbol();
    }

    Game getGame() {
        return this.rootController.getGame();
    }

    Piece getPiece() {
        return this.rootController.getPiece();
    }

    Display getDisplay() {
        return this.rootController.getDisplay();
    }


    @FXML
    private void clickGrid(MouseEvent event) {
        Node source = (Node) event.getSource();
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        ImageView im = new ImageView(String.valueOf(getClass().getResource(getSymbol())));
        im.setFitHeight(50+3);
        im.setFitWidth(50+3);
        im.setPreserveRatio(true);
        im.setSmooth(true);
        im.setCache(true);
        grid.add(im, colIndex, rowIndex);

        Position position = new Position(rowIndex + 1, colIndex + 1);
        getGame().getBoard().getCellAt(position).placePiece(getPiece());
        getGame().getBC().update(position);
        checkEndGame(position, source);
        rootController.updateTurn();
        source.setDisable(true);
        this.grid.getChildren().forEach(c->c.setDisable(false));
    }

    public void checkEndGame(Position position, Node source) {
        getGame().checkBoard(position);

        if (getGame().getChaosWon()) {
            loadingFXML(source, "/fxml/ChaosWon.fxml", 400, 400);
        }

        if (getGame().getOrderWon()) {
            loadingFXML(source, "/fxml/OrderWon.fxml", 400, 400);
        }
    }

    private void loadingFXML(Node source, String path, int minHeight, int minWidth){
        FXMLLoader endLoader = new FXMLLoader();
        endLoader.setLocation(getClass().getResource(path));
        Parent endGame;
        try {
            endGame = endLoader.load();
            Scene endScene = new Scene(endGame,  source.getScene().getWidth(), source.getScene().getHeight());
            Stage endStage = (Stage)source.getScene().getWindow();
            endStage.setScene(endScene);
            endStage.setResizable(true);
            endStage.setMinWidth(minWidth);
            endStage.setMinHeight(minHeight);
            endStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

