module com.example.projectwithgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projectwithgui to javafx.fxml;
    exports com.example.projectwithgui;
}