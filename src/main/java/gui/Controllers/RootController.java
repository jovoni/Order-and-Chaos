package gui.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import orderandchaos.Entities.Piece;
import orderandchaos.Game;
import orderandchaos.Utils.Display;

import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    @FXML private GridController gridController;
    @FXML private Label turn;
    @FXML public Button buttonX;
    @FXML public Button buttonO;

    private Game game;
    private String symbol;
    private Display display;
    public Piece piece;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gridController.injectMainController(this);
        this.game = new Game();
        this.display = new Display(this.game.getBoard());
        this.turn.setText("First turn: Order starts");
        this.turn.setAlignment(Pos.CENTER);
    }

    @FXML
    public void onMouseClickedCircle() {
        Color black = Color.rgb(0, 0, 0);
        putImage("/imgs/O.png");
        this.buttonO.setBorder(new Border(new BorderStroke(black, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
    }

    @FXML
    public void onMouseClickedCross() {
        Color black = Color.rgb(0, 0, 0);
        putImage("/imgs/X.png");
        this.buttonX.setBorder(new Border(new BorderStroke(black, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
    }

    private void putImage(String path){
        if(!this.gridController.started){
            this.gridController.grid.getChildren().forEach(c->c.setDisable(false));
        }
        this.gridController.started = true;
        Color darkGray = Color.rgb(147, 149, 152);
        this.symbol = path;
        if(path.contains("X")){
            this.piece = Piece.X;
        } else {
            this.piece = Piece.O;
        }
        this.buttonX.setBorder(new Border(new BorderStroke(darkGray, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
        this.buttonO.setBorder(new Border(new BorderStroke(darkGray, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
    }

    @FXML
    public void updateTurn() {
        if (this.turn.getText().contains("Order")){
            turn.setText("Chaos turn");
        } else {
            turn.setText("Order turn");
        }
    }

    public String getSymbol() {return this.symbol;}

    public Game getGame() {return this.game;}

    public Piece getPiece() {return this.piece;}

    public Display getDisplay() {return this.display;}
}
