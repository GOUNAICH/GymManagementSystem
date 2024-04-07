package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SettingsController {

    @FXML
    private Button menu1221;

    @FXML
    private Button menu122;

    @FXML
    private Button AccountSettings;

    @FXML
    private Button SystemInformation;

    @FXML
    private Button DisplayBrightness;

    @FXML
    private Button langage;

    @FXML
    private Button MemberManagement;

    @FXML
    private Button menu;
    
    @FXML
    private Button menu12211;

    @FXML
    private Button home;

    @FXML
    private Button members;

    @FXML
    private Button nothifications;

    @FXML
    private Button settings;
    @FXML
    private AnchorPane anchorPane;
	@FXML
    private Button btnUpdate;  // Correctly named button for the update operation

    @FXML
    private TextField newName;

    @FXML
    private PasswordField newPass;

 	@FXML
    private Button btnok;
 	@FXML
    private Button btnModify;
    

    @FXML
    private Button toggleModeButton;

    private boolean isDarkMode = true;
    @FXML
    private Button settingspage;

    @FXML
	public void ActionClose(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
    	Scene scene = new Scene(root);

        // Set the dimensions for the scene
        Stage stage = new Stage();
        stage.setScene(scene);

        // Maximize the stage to the full screen
        stage.setMaximized(true);

        // Show the stage
        stage.show();

        // Close the current login stage
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
	}
    
    @FXML
    void returntosettings(ActionEvent event) throws IOException {
    	 Parent root = FXMLLoader.load(getClass().getResource("SettingsPage.fxml"));
    	 Scene scene = new Scene(root);

         // Set the dimensions for the scene
         Stage stage = new Stage();
         stage.setScene(scene);

         // Maximize the stage to the full screen
         stage.setMaximized(true);

         // Show the stage
         stage.show();

         // Close the current login stage
         Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         currentStage.close();
    }

    @FXML
	 public void MemberManagement(ActionEvent event) throws IOException {
		 Parent root = FXMLLoader.load(getClass().getResource("ManageM.fxml"));
		 Scene scene = new Scene(root);

         // Set the dimensions for the scene
         Stage stage = new Stage();
         stage.setScene(scene);

         // Maximize the stage to the full screen
         stage.setMaximized(true);

         // Show the stage
         stage.show();

         // Close the current login stage
         Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         currentStage.close();
	 }
    @FXML
    void switchToAdd(ActionEvent event) throws IOException {
		 Parent root = FXMLLoader.load(getClass().getResource("AddMember.fxml"));
		 Scene scene = new Scene(root);

         // Set the dimensions for the scene
         Stage stage = new Stage();
         stage.setScene(scene);

         // Maximize the stage to the full screen
         stage.setMaximized(true);

         // Show the stage
         stage.show();

         // Close the current login stage
         Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         currentStage.close();
    }
	
    @FXML
  	 public void switchToMembers(ActionEvent event) throws IOException {
  		 Parent root = FXMLLoader.load(getClass().getResource("MembersDesign.fxml"));
  		Scene scene = new Scene(root);

        // Set the dimensions for the scene
        Stage stage = new Stage();
        stage.setScene(scene);

        // Maximize the stage to the full screen
        stage.setMaximized(true);

        // Show the stage
        stage.show();

        // Close the current login stage
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
  	 }
      @FXML
      void switchToHome(ActionEvent event) throws IOException {
  		 Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
  		Scene scene = new Scene(root);

        // Set the dimensions for the scene
        Stage stage = new Stage();
        stage.setScene(scene);

        // Maximize the stage to the full screen
        stage.setMaximized(true);

        // Show the stage
        stage.show();

        // Close the current login stage
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
  	}

      @FXML
      void switchToNotif(ActionEvent event) throws IOException {
  		 Parent root = FXMLLoader.load(getClass().getResource("notifications.fxml"));
  		Scene scene = new Scene(root);

        // Set the dimensions for the scene
        Stage stage = new Stage();
        stage.setScene(scene);

        // Maximize the stage to the full screen
        stage.setMaximized(true);

        // Show the stage
        stage.show();

        // Close the current login stage
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
  	}
	
	
    

	@FXML
	public void logout(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
	    Parent root = loader.load();

	    // Create a new stage for the login page
	    Stage loginStage = new Stage();
	    Scene scene = new Scene(root);
	    loginStage.setScene(scene);
	    loginStage.show();

	    // Close the current stage
	    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    currentStage.close();
	   
	}
	@FXML
    void ToSystemInformationaction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SystemInformation.fxml"));
        Scene scene = new Scene(root);

        // Set the dimensions for the scene
        Stage stage = new Stage();
        stage.setScene(scene);

        // Maximize the stage to the full screen
        stage.setMaximized(true);

        // Show the stage
        stage.show();

        // Close the current login stage
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }
	@FXML
    void modifyUser(ActionEvent event) {
        if (performSecurityCheck()) {
            try {
                // Your existing code for loading and showing the modification page
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Display a message or take action if the security check fails
            System.out.println("Security check failed. Access denied.");
        }
    }


    
    
    
    private TextField usernameField;

    private boolean performSecurityCheck() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Security Check");
        dialog.setHeaderText("Enter your credentials:");

        // Add fields for username and password
        dialog.getDialogPane().setContentText("Username:");
        usernameField = new TextField();  // Use the class-level variable
        PasswordField passwordField = new PasswordField();
        GridPane grid = new GridPane();
        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(passwordField, 1, 1);
        dialog.getDialogPane().setContent(grid);

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            String enteredUsername = usernameField.getText();
            String enteredPassword = passwordField.getText();

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/kasabagym", "root", "");
                 PreparedStatement pst = connection.prepareStatement("SELECT * FROM admin WHERE username = ? AND password = ?")) {

                pst.setString(1, enteredUsername);
                pst.setString(2, enteredPassword);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("User found in the database");
                        openUpdatePage(); // Open the update page
                        return true;
                    } else {
                        System.out.println("User not found in the database");
                        showAlert("Authentication Failed", "Invalid username or password.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Error", "An error occurred during authentication. Please try again.");
            }
        }

        return false;
    }


    private void openUpdatePage() {
        try {
            // Load the FXML file for the update page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyPage.fxml"));
            Parent root = loader.load();

            // Create a new stage for the update page
            Stage updateStage = new Stage();
            updateStage.setTitle("Update Page");
            updateStage.setScene(new Scene(root));

            // Show the update page
            updateStage.show();

            // Close the current login page (optional)
            Scene scene = usernameField.getScene();
            if (scene != null) {
                Stage loginStage = (Stage) scene.getWindow();
                loginStage.close();
            } else {
                System.out.println("Warning: Scene is null for usernameField");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to open the update page.");
        }
    }



    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
	
	
    @FXML
    public void initialize() {
        // Call setAppStyle() during initialization to apply the initial mode
        setAppStyle();
    }
    @FXML
    void toggleMode(ActionEvent event) {
        isDarkMode = !isDarkMode;
        setAppStyle(); // Apply the style after the mode change
    }

    private void setAppStyle() {
        if (anchorPane != null) { // Check if anchorPane is not null before accessing its properties
            System.out.println("Setting app style to " + (isDarkMode ? "dark" : "light"));
            String styleSheet = isDarkMode ? "dark-mode.css" : "light-mode.css";
            anchorPane.getStylesheets().clear();
            anchorPane.getStylesheets().add(getClass().getResource(styleSheet).toExternalForm());
        } else {
            System.out.println("AnchorPane is null. Check initialization.");
        }
    }


}
