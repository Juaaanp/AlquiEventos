module alquieventos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens alquieventos to javafx.fxml;
    exports alquieventos;
}
