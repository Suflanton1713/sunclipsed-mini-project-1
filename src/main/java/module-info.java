module com.example.sesion5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sesion5 to javafx.fxml;
    opens com.example.sesion5.controller to javafx.fxml;
    exports com.example.sesion5;
}