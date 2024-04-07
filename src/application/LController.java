package application;

import java.util.logging.Logger;
import java.util.Optional;
import java.util.logging.Level;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LController {

	 @FXML
	    private Button btnok;

	    @FXML
	    private PasswordField pass;

	    @FXML
	    private TextField name;
	    
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        
        @FXML
	    private Button btnUpdate;  // Correctly named button for the update operation
	
	    @FXML
	    private TextField newName;
	
	    @FXML
	    private PasswordField newPass;

	 	
	 	@FXML
	    private Button btnModify;

	    

	    @FXML
	    void logIn(ActionEvent event) throws IOException {
	     
	    	String uname = name.getText(); 
	    	String password = pass.getText(); 
	    	
	    	if (uname.equals("") || password.equals("")) {
	    		
	    			JOptionPane.showMessageDialog(null,"Enter the Username Or Password !!");
	    			
			}else {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con= DriverManager.getConnection("jdbc:mysql://localhost/kasabagym", "root","");
					pst=con.prepareStatement("select * from admin where username=? and password=? ");
					pst.setString(1, uname);
					pst.setString(2, password);
					rs =pst.executeQuery();
					if (rs.next()) {
						
						JOptionPane.showMessageDialog(null,"Login Succes");
						
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
						

					}else {
						JOptionPane.showMessageDialog(null,"Login Failed");
						name.setText("");
						pass.setText("");
						name.requestFocus();
						
					}
				}catch (ClassNotFoundException | SQLException ex) {
				    ex.printStackTrace();  
				    Logger.getLogger(LController.class.getName()).log(Level.SEVERE, "An exception occurred", ex);
				}

			}
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


        
        @FXML
        void updateUser(ActionEvent event) {
            try {
                String newUsername = newName.getText();
                String newPassword = newPass.getText();

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/kasabagym", "root", "");
                String updateQuery = "UPDATE admin SET username = ?, password = ? WHERE id = ?";
                PreparedStatement pst = connection.prepareStatement(updateQuery);

                // Set the new values for username and password
                pst.setString(1, newUsername);
                pst.setString(2, newPassword);

                // Assuming you have a method getId() to get the user ID, replace it with the actual method you have
                pst.setInt(3, getId());

                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("User updated successfully");
                } else {
                    System.out.println("Failed to update user");
                }

                // Close the current modification window
                Stage modifyStage = (Stage) btnUpdate.getScene().getWindow();
                modifyStage.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Replace this method with the actual method you have to get the user ID
        private int getId() {
            // Replace with the actual method to get the user ID
            return 1;
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

}
