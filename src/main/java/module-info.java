module ru.arsen.oop3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.arsen.oop3 to javafx.fxml;
    exports ru.arsen.oop3;
}