package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AController {
	
	@FXML
    private Button add;
    
    @FXML
    private Button home;

    @FXML
    private Button members;

    @FXML
    private Button menu;

    @FXML
    private Button nothifications;
    
    @FXML
    private Button settings;
    
    @FXML
    private TextField txtprenom;

    @FXML
    private TextField txtnom;

    @FXML
    private TextField txtnumberphone;

    @FXML
    private TextField txtgender;

    @FXML
    private TextField txtage;

    @FXML
    private TextField txtduration;

    @FXML
    private TextField txtamount;

    
    @FXML
    void switchTosettings(ActionEvent event) throws IOException {
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
    void initialize() {
        
    }
    
    @FXML
    void ajouter(ActionEvent event) {
        try {
            // Validate nom and prenom
            String nom = txtnom.getText();
            String prenom = txtprenom.getText();
            if (!isValidName(nom) || !isValidName(prenom)) {
                showAlert(AlertType.ERROR, "Error", "Invalid First Name Or Laste Name. Please use alphabets only and limit to 20 characters.");
                return;
            }

            // Validate age
            int age = Integer.valueOf(txtage.getText());
            if (age < 10) {
                showAlert(AlertType.ERROR, "Error", "Invalid Age. Age should be 10 or greater.");
                return;
            }

            // Validate gender
            String gender = txtgender.getText();
            if (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")) {
                showAlert(AlertType.ERROR, "Error", "Invalid Gender. Use 'M' for male or 'F' for female.");
                return;
            }

            // Validate phone number
            String phoneNumber = txtnumberphone.getText();
            if (!isValidPhoneNumber(phoneNumber)) {
                showAlert(AlertType.ERROR, "Error", "Invalid Phone Number. Please use a valid format.");
                return;
            }

            // Validate amount
            String amountText = txtamount.getText().replaceAll("[^0-9]", "");
            int amount = Integer.valueOf(amountText);
            if (amount <= 0) {
                showAlert(AlertType.ERROR, "Error", "Invalid Amount. Amount should be greater than 0.");
                return;
            }

            // Other input validations...

            // If all validations pass, proceed with member creation
            Members m = new Members(nom, prenom, gender, Integer.valueOf(phoneNumber), amount, Integer.valueOf(txtduration.getText()), age);

            // Clear input fields
            txtnom.setText("");
            txtprenom.setText("");
            txtage.setText("");
            txtgender.setText("");
            txtnumberphone.setText("");
            txtduration.setText("");
            txtamount.setText("");

            // Insert member and generate PDF
            insert(m);
            GeneratPDF.generatePDF(m);
            showAlert(AlertType.INFORMATION, "Success", "Member added successfully.");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Please enter valid numeric values for Age, Duration, and Amount.");
        }
    }

    // Helper method to validate name (alphabetical and length <= 20)
    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z]+") && name.length() <= 20;
    }

    // Helper method to validate phone number format (06XXXXXXXX, +2126XXXXXXXX, 07XXXXXXXX)
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^(06|\\+2126|07)\\d{8}$");
    }

	
	

   

    private void showAlert(AlertType alertType, String title, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static void insert(Members m) {
        try {
            Connection connection = MysqlConnection.getDBConnection();
            String sql = "INSERT INTO `members` (`id`, `nom`, `prenom`, `gender`, `numPhone`, `amount`, `duree`, `age`, `status`, `endDate`, `startDate`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, '', NULL, current_timestamp());";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, m.nom);
            ps.setString(2, m.prenom);
            ps.setString(3, m.gender);
            ps.setInt(4, m.numPhone);
            ps.setInt(5, m.amount);
            ps.setInt(6, m.duree);
            ps.setInt(7, m.age);
          
            ps.execute();
            
            DatabaseHandler.updateEndDateForAllMembers();
            DatabaseHandler.updateMemberStatus();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
