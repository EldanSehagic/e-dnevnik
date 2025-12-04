package com.skola.controller;

import com.skola.model.DirectorStudent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DirectorDashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private TableView<DirectorStudent> studentsTable;

    @FXML
    private TableColumn<DirectorStudent, String> studentColumn;

    @FXML
    private TableColumn<DirectorStudent, String> subjectColumn;

    @FXML
    private TableColumn<DirectorStudent, Integer> gradeColumn;

    private ObservableList<DirectorStudent> students = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Dummy podaci
        students.add(new DirectorStudent("Eldan Šehagić", "Matematika", 4));
        students.add(new DirectorStudent("Mirza H.", "Fizika", 5));
        students.add(new DirectorStudent("Amina B.", "Biologija", 3));

        studentColumn.setCellValueFactory(cellData -> cellData.getValue().studentNameProperty());
        subjectColumn.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        gradeColumn.setCellValueFactory(cellData -> cellData.getValue().gradeProperty().asObject());

        studentsTable.setItems(students);
    }

    @FXML
    private void handleAddStudent() {
        students.add(new DirectorStudent("Novi učenik", "Novi predmet", 4));
    }

    @FXML
    private void handleDeleteStudent() {
        DirectorStudent selected = studentsTable.getSelectionModel().getSelectedItem();
        if(selected != null) {
            students.remove(selected);
        }
    }

    public void setDirectorName(String name) {
        welcomeLabel.setText("Dobrodošli, " + name + "!");
    }
}
