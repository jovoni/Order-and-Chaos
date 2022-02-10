package gui.project;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {


    @FXML private GridController gridController;


    public String symbol;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gridController.injectMainController(this);
    }

    public void onMouseClickedCircle() {
        this.symbol = "/zero.jpg";
    }

    public void onMouseClickedCross() {
        this.symbol = "/cross.png" ;
    }

    public String getSymbol(){
        return symbol;
    }



}
