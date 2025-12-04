package com.skola.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(username.equals("ucenik") && password.equals("123")) {
            // Otvori Student Dashboard
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/StudentDashboard.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));

                // prosledi ime korisnika
                StudentDashboardController controller = loader.getController();
                controller.setStudentName(username);

                stage.setTitle("e-Dnevnik - Dashboard");
                stage.show();

                // zatvori login prozor
                ((Stage) usernameField.getScene().getWindow()).close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
