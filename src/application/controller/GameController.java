package application.controller;

import application.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.util.Random;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class GameController {
    @FXML
    private Label computerLabel, resultLabel, scoreLabel, roundsLabel;
    @FXML
    private RadioButton rockButton, paperButton, scissorsButton;
    @FXML
    private Button newGameButton, exitButton, playRoundButton;
    @FXML
    private ToggleGroup choiceGroup;

    private int playerScore = 0;
    private int computerScore = 0;
    private int roundCount = 0;
    private final String[] choices = {"Kivi", "Paperi", "Sakset"};
    private final Random random = new Random();

    public void initialize() {
        updateScore();
        updateRounds();
        choiceGroup = new ToggleGroup();
        rockButton.setToggleGroup(choiceGroup);
        paperButton.setToggleGroup(choiceGroup);
        scissorsButton.setToggleGroup(choiceGroup);
        
        addImagesToRadioButtons();
    }
    
    private void addImagesToRadioButtons() {
        Image rockImage = new Image(getClass().getResourceAsStream("/application/styles/kivi.png"));
        ImageView rockImageView = new ImageView(rockImage);
        rockImageView.setFitWidth(40);
        rockImageView.setFitHeight(40);
        rockButton.setGraphic(rockImageView);

        Image paperImage = new Image(getClass().getResourceAsStream("/application/styles/paperi.png"));
        ImageView paperImageView = new ImageView(paperImage);
        paperImageView.setFitWidth(40);
        paperImageView.setFitHeight(40);
        paperButton.setGraphic(paperImageView);

        Image scissorsImage = new Image(getClass().getResourceAsStream("/application/styles/sakset.png"));
        ImageView scissorsImageView = new ImageView(scissorsImage);
        scissorsImageView.setFitWidth(40);
        scissorsImageView.setFitHeight(40);
        scissorsButton.setGraphic(scissorsImageView);
    }
    
    @FXML
    private void playRound() {
    	roundCount++;
        RadioButton selected = (RadioButton) choiceGroup.getSelectedToggle();
        if (selected == null) {
            resultLabel.setText("Valitse ensin!");
            return;
        }

        String playerChoice = selected.getText();
        String computerChoice = choices[random.nextInt(3)];
        
        updateComputerChoice(computerChoice);

        determineWinner(playerChoice, computerChoice);
    }
    
    private void updateComputerChoice(String computerChoice) {
        ImageView imageView = new ImageView();
        if (computerChoice.equals("Kivi")) {
            imageView.setImage(new Image(getClass().getResourceAsStream("/application/styles/kivi.png")));
        } else if (computerChoice.equals("Paperi")) {
            imageView.setImage(new Image(getClass().getResourceAsStream("/application/styles/paperi.png")));
        } else if (computerChoice.equals("Sakset")) {
            imageView.setImage(new Image(getClass().getResourceAsStream("/application/styles/sakset.png")));
        }
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        
        Label computerChoiceLabel = new Label("Tietokone valitsi: " + computerChoice);
        computerChoiceLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        
        HBox computerChoiceBox = new HBox();
        computerChoiceBox.setSpacing(10);
        computerChoiceBox.getChildren().addAll(computerChoiceLabel, imageView);
        computerLabel.setGraphic(computerChoiceBox);
    }

    private void determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            resultLabel.setText("Tasapeli! Valitkaa uudelleen.");
            return;
        }

        boolean playerWins = (playerChoice.equals("Kivi") && computerChoice.equals("Sakset")) ||
                             (playerChoice.equals("Paperi") && computerChoice.equals("Kivi")) ||
                             (playerChoice.equals("Sakset") && computerChoice.equals("Paperi"));

        if (playerWins) {
            playerScore++;
            resultLabel.setText("Voitit kierroksen!");
        } else {
            computerScore++;
            resultLabel.setText("Tietokone voitti kierroksen.");
        }

        updateScore();
        updateRounds();
        try {
            checkForWinner();
        } catch (IOException e) {
            System.err.println("Virhe siirryttäessä aloitusnäkymään: " + e.getMessage());
        }

    }

    private void updateScore() {
    	String playerName = SettingsController.getPlayerName();
        scoreLabel.setText(playerName + ": " + playerScore + " - Tietokone: " + computerScore);
    }
    private void updateRounds() {
        roundsLabel.setText("Kierrokset: " + roundCount);
    }

    private void checkForWinner() throws IOException {
        if (playerScore >= SettingsController.getRequiredWins()) {
            resultLabel.setText("Voitit pelin!");
            showEndGameOptions("Voitit pelin!");
        } else if (computerScore >= SettingsController.getRequiredWins()) {
            resultLabel.setText("Tietokone voitti pelin!");
            showEndGameOptions("Tietokone voitti pelin!");
        }
    }

    private void showEndGameOptions(String winnerMessage) {
        resultLabel.setText(winnerMessage);
        playRoundButton.setVisible(false);
        newGameButton.setVisible(true);
        exitButton.setVisible(true);
    }

    @FXML
    private void resetGame() throws IOException {
        Main.showStartView();
        playerScore = 0;
        computerScore = 0;
        roundCount = 0;
        updateScore();
        resultLabel.setText("Tulos: ");
    }

    @FXML
    private void exitGame() {
        System.exit(0);
    }
}