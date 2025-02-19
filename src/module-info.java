module KPS {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens application to javafx.graphics, javafx.fxml;
    opens application.controller to javafx.fxml;
    
    exports application;
    exports application.controller;
}