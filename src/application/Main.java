package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        showStartView();
    }

    public static void showStartView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/application/view/StartView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Main.class.getResource("/application/styles/StartStyles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Kivi-Paperi-Sakset");
        primaryStage.show();
    }

    public static void showSettingsView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/application/view/SettingsView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Main.class.getResource("/application/styles/SettingStyles.css").toExternalForm());
        primaryStage.setScene(scene);
    }

    public static void showGameView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/application/view/GameView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Main.class.getResource("/application/styles/GameStyles.css").toExternalForm());
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}