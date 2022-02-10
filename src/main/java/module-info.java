module gui.project {
    requires javafx.controls;
    requires javafx.fxml;

    opens gui.project to javafx.fxml;
    exports gui.project;
}