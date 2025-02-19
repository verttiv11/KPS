package application.controller;

import application.Main;
import javafx.fxml.FXML;
import java.io.IOException;

public class StartController {
    @FXML
    private void startGame() throws IOException {
        Main.showSettingsView();
    }
}