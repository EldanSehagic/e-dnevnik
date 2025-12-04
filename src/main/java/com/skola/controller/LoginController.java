package com.skola.controller;

import com.skola.model.User;
import com.skola.repository.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private UserRepository userRepo = new UserRepository();

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Dohvati korisnika iz baze
        User user = userRepo.getUserByUsernameAndPassword(username, password);

        if (user == null) {
            showAlert("Neispravno korisničko ime ili lozinka", "Pokušajte ponovo.");
        } else {
            // Otvori odgovarajući dashboard
            try {
                Stage stage = new Stage();
                FXMLLoader loader;
                switch (user.getRole()) {
                    case "ucenik":
                        loader = new FXMLLoader(getClass().getResource("/view/StudentDashboard.fxml"));
                        stage.setScene(new Scene(loader.load()));
                        stage.setTitle("e-Dnevnik - Student Dashboard");
                        StudentDashboardController studentController = loader.getController();
                        studentController.setStudentName(username);
                        break;
                    case "nastavnik":
                        loader = new FXMLLoader(getClass().getResource("/view/TeacherDashboard.fxml"));
                        stage.setScene(new Scene(loader.load()));
                        stage.setTitle("e-Dnevnik - Teacher Dashboard");
                        TeacherDashboardController teacherController = loader.getController();
                        teacherController.setTeacherName(username);
                        break;
                    case "direktor":
                        loader = new FXMLLoader(getClass().getResource("/view/DirectorDashboard.fxml"));
                        stage.setScene(new Scene(loader.load()));
                        stage.setTitle("e-Dnevnik - Director Dashboard");
                        DirectorDashboardController directorController = loader.getController();
                        directorController.setDirectorName(username);
                        break;
                    default:
                        showAlert("Greška", "Nepoznata uloga korisnika.");
                        return;
                }

                stage.show();

                // Zatvori login prozor
                Stage loginStage = (Stage) usernameField.getScene().getWindow();
                loginStage.close();

            } catch (IOException e) {
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
