package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import com.example.Model.HPCharacter;
import com.example.Util.HarryApiManager;
import com.example.Controller.HPCharacterController;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        // Load the graphical user interface
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("HPView2.fxml"));
        Parent root = fxmlLoader.load();
        scene = new Scene(root, 640, 480);
        
        // Load the CSS file to style the scene
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);

        // Set the window title
        stage.setTitle("Harry Potter Characters");

        // Set the application icon
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));

        // Show the window
        stage.show();

        // Load characters into the graphical user interface
        HarryApiManager manager = new HarryApiManager();
        List<HPCharacter> characters = manager.getCharacters();
        HPCharacterController controller = fxmlLoader.getController();
        controller.setCharacters(characters);
    }

    // Method to change the root FXML content dynamically
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    // Method to load FXML files
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    // private void displayCharacters() {
    //     HarryApiManager manager = new HarryApiManager();
    //     List<HPCharacter> characters = manager.getCharacters();
    //     for (HPCharacter character : characters) {
    //         System.out.println("Character: " + character.getName());
    //     }
    // }
}
