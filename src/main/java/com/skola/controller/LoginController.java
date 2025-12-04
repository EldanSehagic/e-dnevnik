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

        try {
            Stage stage = new Stage();

            if(username.equals("ucenik") && password.equals("123")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/StudentDashboard.fxml"));
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("e-Dnevnik - Student Dashboard");
                StudentDashboardController controller = loader.getController();
                controller.setStudentName(username);
            } else if(username.equals("nastavnik") && password.equals("123")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TeacherDashboard.fxml"));
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("e-Dnevnik - Teacher Dashboard");
                TeacherDashboardController controller = loader.getController();
                controller.setTeacherName(username);
            } else if(username.equals("direktor") && password.equals("123")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DirectorDashboard.fxml"));
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("e-Dnevnik - Director Dashboard");
                DirectorDashboardController controller = loader.getController();
                controller.setDirectorName(username);
            } else {
                showAlert("Neispravno korisničko ime ili lozinka", "Pokušajte ponovo.");
                return;
            }

            stage.show();
            ((Stage) usernameField.getScene().getWindow()).close();

        } catch (Exception e) {
            e.printStackTrace();
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
