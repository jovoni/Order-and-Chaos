package gui.project;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import orderandchaos.Utils.Display;
import orderandchaos.Game;
import orderandchaos.Entities.Piece;
import orderandchaos.Entities.Position;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GridController implements Initializable {
    @FXML
    public GridPane grid;
    RootController rootController;

    public void injectMainController(RootController rootController) {
        this.rootController = rootController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane.setRightAnchor(grid, 80.0);
        AnchorPane.setTopAnchor(grid, 110.0);
        AnchorPane.setLeftAnchor(grid, 130.0);
        AnchorPane.setBottomAnchor(grid, 130.0);
        grid.setAlignment(Pos.CENTER);

        NumberBinding rectsAreaSize = Bindings.min(grid.heightProperty(), grid.widthProperty());
        grid.vgapProperty().bind(rectsAreaSize.divide(60));
        grid.hgapProperty().bind(rectsAreaSize.divide(60));

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                Rectangle r = new Rectangle();

                r.xProperty().bind(rectsAreaSize.multiply(row).divide(6));
                r.yProperty().bind(rectsAreaSize.multiply(col).divide(6));
                r.heightProperty().bind(rectsAreaSize.divide(6).subtract(10));
                r.widthProperty().bind(rectsAreaSize.divide(6).subtract(10));

                r.setFill(Color.WHITE);
                r.setStroke(Color.rgb(216, 216, 216));
                r.strokeWidthProperty().bind(rectsAreaSize.divide(100));
                r.setOnMouseClicked(this::clickGrid);
                grid.addRow(row, r);
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
        Integer colIndex = grid.getColumnIndex(source);
        Integer rowIndex = grid.getRowIndex(source);
        ImageView im = new ImageView(getSymbol());
        im.setFitHeight(50+3);
        im.setFitWidth(50+3);
        im.setPreserveRatio(true);
        im.setSmooth(true);
        im.setCache(true);
        grid.add(im, colIndex, rowIndex);

        Position position = new Position(rowIndex + 1, colIndex + 1);
        getGame().getBoard().getCellAt(position).placePiece(getPiece());

        getGame().getBC().update(position);
        getDisplay().printBoard();

        checkEndGame(position, source);
        rootController.updateTurn();
        source.setDisable(true);
    }

    public void checkEndGame(Position position, Node source){
        getGame().checkBoard(position);

        if(getGame().getChaosWon()){
            FXMLLoader endLoader = new FXMLLoader();
            endLoader.setLocation(getClass().getResource("/ChaosWon.fxml"));
            Parent endGame = null;
            try {
                endGame = endLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene endScene = new Scene(endGame);

            Stage endStage = (Stage)source.getScene().getWindow();
            endStage.setScene(endScene);
            endStage.setResizable(false);
            endStage.show();
        }

        if(getGame().getOrderWon()){
            FXMLLoader endLoader = new FXMLLoader();
            endLoader.setLocation(getClass().getResource("/OrderWon.fxml"));
            Parent endGame = null;
            try {
                endGame = endLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene endScene = new Scene(endGame);

            Stage endStage = (Stage)source.getScene().getWindow();
            endStage.setScene(endScene);
            endStage.setResizable(false);
            endStage.show();
        }
    }
}

