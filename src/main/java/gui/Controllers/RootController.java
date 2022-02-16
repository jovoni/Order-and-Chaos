package gui.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import orderandchaos.*;
import orderandchaos.Entities.Piece;
import orderandchaos.Utils.Display;

import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    @FXML private GridController gridController;
    @FXML private Label turn;
    @FXML private Button buttonX;
    @FXML private Button buttonO;

    private Game game;
    private String symbol;
    private Display display;
    public Piece piece;

    private final Color darkGray = Color.rgb(147, 149, 152);

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
        this.gridController.started = true;
        this.symbol = "O.png";
        this.piece = Piece.O;
        this.buttonO.setBorder(new Border(new BorderStroke(darkGray, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
        this.buttonX.setBorder(new Border(new BorderStroke(darkGray, BorderStrokeStyle.NONE, new CornerRadii(5), BorderWidths.DEFAULT)));
    }

    @FXML
    public void onMouseClickedCross() {
        this.gridController.started = true;
        this.gridController.grid.getChildren().forEach(c->c.setDisable(false));
        this.symbol = "X.png";
        this.piece = Piece.X;
        this.buttonX.setBorder(new Border(new BorderStroke(darkGray, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
        this.buttonO.setBorder(new Border(new BorderStroke(darkGray, BorderStrokeStyle.NONE, new CornerRadii(5), BorderWidths.DEFAULT)));
    }

    @FXML
    public void updateTurn() {
        if (this.turn.getText().contains("Order")){
            turn.setText("Chaos turn");
        } else {
            turn.setText("Order turn");
        }
    }

    public String getSymbol() {return symbol;}

    public Game getGame() {return game;}

    public Piece getPiece() {return piece;}

    public Display getDisplay() {return display;}
}
