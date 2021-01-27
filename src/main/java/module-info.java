module com.Controladores {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.Vistas to javafx.fxml;
    exports com.Controladores;
}
