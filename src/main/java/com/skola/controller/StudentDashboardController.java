package com.skola.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.skola.model.SubjectGrade;

public class StudentDashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private TableView<SubjectGrade> gradesTable;

    @FXML
    private TableColumn<SubjectGrade, String> subjectColumn;

    @FXML
    private TableColumn<SubjectGrade, Integer> gradeColumn;

    private ObservableList<SubjectGrade> grades = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Dummy podaci za test
        grades.add(new SubjectGrade("Matematika", 4));
        grades.add(new SubjectGrade("Fizika", 5));
        grades.add(new SubjectGrade("Biologija", 3));

        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        gradesTable.setItems(grades);
    }

    public void setStudentName(String name) {
        welcomeLabel.setText("Dobrodošli, " + name + "!");
    }
}
