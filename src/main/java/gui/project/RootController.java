package gui.project;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import orderandchaos.*;

import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    @FXML private GridController gridController;

    private Game game;
    private String symbol;
    private Display display;
    public Piece piece;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        gridController.injectMainController(this);
        this.game = new Game();
        this.display = new Display(this.game.getBoard());

    }

    public void onMouseClickedCircle() {

        this.symbol = "/zero.jpg";
        this.piece = Piece.O;
    }

    public void onMouseClickedCross() {

        this.symbol = "/cross.png" ;
        this.piece = Piece.X;
    }

    public String getSymbol(){
        return symbol;
    }

    public Game getGame(){ return game; }

    public Piece getPiece(){ return piece; }

    public Display getDisplay(){ return display; }



}
