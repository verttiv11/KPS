package application.controller;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;

public class SettingsController {
    @FXML
    private TextField playerNameField;
    @FXML
    private ComboBox<Integer> winCountComboBox;
    @FXML
    private Label errorLabel;

    private static String playerName;
    private static int requiredWins;

    @FXML
    public void initialize() {
        winCountComboBox.getItems().addAll(1, 3, 5);
        errorLabel.setText("");
    }

    @FXML
    private void startGame() throws IOException {
        playerName = playerNameField.getText();
        Integer selectedWins = winCountComboBox.getValue();
        
        if (playerName.isEmpty()) {
            errorLabel.setText("Syötä pelaajan nimi!");
            return;
        }
        if (selectedWins == null) {
            errorLabel.setText("Valitse voittomäärä!");
            return;
        }
        requiredWins = selectedWins;
        Main.showGameView();
    }

    public static String getPlayerName() {
        return playerName;
    }

    public static int getRequiredWins() {
        return requiredWins;
    }
}
