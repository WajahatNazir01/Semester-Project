module com.example.projectwithgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;


    opens com.example.projectwithgui to javafx.fxml;
    exports com.example.projectwithgui;
}