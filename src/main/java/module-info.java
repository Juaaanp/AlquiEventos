module alquieventos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens alquieventos to javafx.fxml;
    exports alquieventos;
}
