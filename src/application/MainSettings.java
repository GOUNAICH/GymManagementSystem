package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainSettings extends Application {
	@Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SettingsPage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            String initialCss = this.getClass().getResource("dark-mode.css").toExternalForm();
            scene.getStylesheets().add(initialCss);
            String initialCss2 = this.getClass().getResource("light-mode.css").toExternalForm();
            scene.getStylesheets().add(initialCss2);

            primaryStage.setScene(scene);

            // Call initialize() on the controller
            SettingsController controller = loader.getController();
            controller.initialize();

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
