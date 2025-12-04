package com.skola.controller;

import com.skola.model.StudentGrade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TeacherDashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private TableView<StudentGrade> studentsTable;

    @FXML
    private TableColumn<StudentGrade, String> studentColumn;

    @FXML
    private TableColumn<StudentGrade, Integer> gradeColumn;

    private ObservableList<StudentGrade> studentGrades = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Dummy podaci
        studentGrades.add(new StudentGrade("Eldan Šehagić", 4));
        studentGrades.add(new StudentGrade("Mirza H.", 5));
        studentGrades.add(new StudentGrade("Amina B.", 3));

        studentColumn.setCellValueFactory(cellData -> cellData.getValue().studentNameProperty());
        gradeColumn.setCellValueFactory(cellData -> cellData.getValue().gradeProperty().asObject());

        studentsTable.setItems(studentGrades);
    }

    @FXML
    private void handleAddGrade() {
        studentGrades.add(new StudentGrade("Novi učenik", 4));
    }

    @FXML
    private void handleDeleteGrade() {
        StudentGrade selected = studentsTable.getSelectionModel().getSelectedItem();
        if(selected != null) {
            studentGrades.remove(selected);
        }
    }

    public void setTeacherName(String name) {
        welcomeLabel.setText("Dobrodošli, " + name + "!");
    }
}
