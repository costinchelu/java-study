module ui {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires db;
    requires common;

    opens ui to javafx.fxml;

    exports ui to javafx.graphics;
}