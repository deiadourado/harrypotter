module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;

    opens com.example to javafx.fxml;
    opens com.example.Model to com.google.gson; // Open the package containing HPCharacter to Gson
    opens com.example.Controller to javafx.fxml; // Open the package containing HPCharacterController to FXMLLoader

    exports com.example;
    exports com.example.Model; // Export the package containing HPCharacter

}