package com.skola.controller;

import com.skola.model.DirectorStudent;
import com.skola.model.Grade;
import com.skola.model.Student;
import com.skola.repository.GradeRepository;
import com.skola.repository.StudentRepository;
import com.skola.repository.SubjectRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.List;

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

    private StudentRepository studentRepo = new StudentRepository();
    private GradeRepository gradeRepo = new GradeRepository();
    private SubjectRepository subjectRepo = new SubjectRepository();

    @FXML
    public void initialize() {
        // Poveži table sa ObservableList
        studentColumn.setCellValueFactory(cellData -> cellData.getValue().studentNameProperty());
        subjectColumn.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        gradeColumn.setCellValueFactory(cellData -> cellData.getValue().gradeProperty().asObject());

        loadAllStudents();
        studentsTable.setItems(students);
    }

    private void loadAllStudents() {
        students.clear();

        List<Student> allStudents = studentRepo.getAllStudents(); // dohvat svih studenata
        for (Student student : allStudents) {
            List<Grade> studentGrades = gradeRepo.getGradesForStudent(student.getId());
            for (Grade g : studentGrades) {
                String subjectName = g.getSubjectName();
                int gradeValue = g.getOcjena();
                String studentFullName = student.getIme() + " " + student.getPrezime();

                students.add(new DirectorStudent(studentFullName, subjectName, gradeValue));
            }
        }
    }

    @FXML
    private void handleAddStudent() {
        // Za sada dummy unos; kasnije može da otvori formu za dodavanje studenta/predmeta
        students.add(new DirectorStudent("Novi učenik", "Novi predmet", 4));
    }

    @FXML
    private void handleDeleteStudent() {
        DirectorStudent selected = studentsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // Kasnije može da briše iz baze
            students.remove(selected);
        }
    }

    public void setDirectorName(String name) {
        welcomeLabel.setText("Dobrodošli, " + name + "!");
    }
}
