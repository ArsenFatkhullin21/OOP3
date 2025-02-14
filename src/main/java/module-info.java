module ru.arsen.oop3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens ru.arsen.oop3 to javafx.fxml;
    exports ru.arsen.oop3;
}