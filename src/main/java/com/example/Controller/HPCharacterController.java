package com.example.Controller;

import com.example.Model.HPCharacter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;

public class HPCharacterController {

    @FXML
    private ComboBox<HPCharacter> characterComboBox;

    @FXML
    private Label nameLabel;

    @FXML
    private Label speciesLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label houseLabel;

    @FXML
    private Label dobLabel;

    @FXML
    private Label actorLabel;

    @FXML
    private Label patronusLabel;

    @FXML
    private ImageView characterImage; // ImageView to display character image

    @FXML
    private TextField searchTextField;

    @FXML
    private List<HPCharacter> characters;

    /**
     * Initializes the controller.
     * This method is automatically called after the FXML file has been loaded.
     * It populates the ComboBox with characters and sets up event listeners.
     */
    public void initialize() {
        // Populates the ComboBox with characters
        if (characters != null && !characters.isEmpty()) {
            ObservableList<HPCharacter> characterList = FXCollections.observableArrayList(characters);
            characterComboBox.setItems(characterList);
            
            // Customizes the appearance of each item in the ComboBox
            characterComboBox.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(HPCharacter item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getName());
                    }
                }
            });
            
            // Adds a listener to handle character selection events
            characterComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    setHPCharacter(newValue);
                }
            });
        }
    }

    @FXML
    private void handleCharacterSelection() {
        // Retrieves the selected character from the ComboBox and displays its data
        HPCharacter selectedCharacter = characterComboBox.getValue();
        if (selectedCharacter != null) {
            setHPCharacter(selectedCharacter);
        }
    }

    /**
     * Sets the character's data in the corresponding labels.
     *
     * @param hpCharacter The character to be displayed.
     */
    public void setHPCharacter(HPCharacter hpCharacter) {
        nameLabel.setText(hpCharacter.getName());
        speciesLabel.setText(hpCharacter.getSpecies());
        genderLabel.setText(hpCharacter.getGender());
        houseLabel.setText(hpCharacter.getHouse());
        dobLabel.setText(hpCharacter.getDateOfBirth());
        nameLabel.setText(hpCharacter.getName());
        actorLabel.setText(hpCharacter.getActor());
        patronusLabel.setText(hpCharacter.getPatronus());

    }

    /**
     * Handles the search action.
     * Filters characters whose names match the search term.
     */
    @FXML
    private void handleSearchAction() {
        String searchTerm = searchTextField.getText().toLowerCase().trim();
        if (searchTerm.isEmpty()) {
            clearLabels();
            return;
        }

        List<HPCharacter> matchingCharacters = new ArrayList<>();
        for (HPCharacter character : characters) {
            if (character.getName().toLowerCase().contains(searchTerm)) {
                matchingCharacters.add(character);
            }
        }

        if (!matchingCharacters.isEmpty()) {
            setHPCharacter(matchingCharacters.get(0));
        } else {
            clearLabels();
        }
    }

    /**
     * Clears the labels.
     */
    private void clearLabels() {
        nameLabel.setText("");
        speciesLabel.setText("");
        genderLabel.setText("");
        houseLabel.setText("");
        dobLabel.setText("");
        actorLabel.setText("");
        patronusLabel.setText("");
    }

    /**
     * Sets the list of characters.
     * Calls the initialize method to populate the ComboBox and display the first character when the list is set.
     *
     * @param characters The list of characters.
     */
    public void setCharacters(List<HPCharacter> characters) {
        this.characters = characters;
        initialize();
    }
}
