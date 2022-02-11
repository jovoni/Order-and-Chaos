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
    public Piece piece;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gridController.injectMainController(this);
    }

    public void onMouseClickedCircle() {

        this.symbol = "/zero.jpg";
        this.piece = piece.X;
    }

    public void onMouseClickedCross() {

        this.symbol = "/cross.png" ;
        this.piece = piece.X;
    }

    public String getSymbol(){
        return symbol;
    }

    public Game getGame(){ return game; }



}
