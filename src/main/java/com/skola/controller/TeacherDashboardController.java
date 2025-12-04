package com.skola.controller;

import com.skola.model.Student;
import com.skola.model.Grade;
import com.skola.model.StudentGrade;
import com.skola.repository.StudentRepository;
import com.skola.repository.GradeRepository;
import com.skola.repository.SubjectRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

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

    private StudentRepository studentRepo = new StudentRepository();
    private GradeRepository gradeRepo = new GradeRepository();
    private SubjectRepository subjectRepo = new SubjectRepository();

    // Za filtriranje ocjena po predmetu koji predaje nastavnik
    private int subjectId;

    @FXML
    public void initialize() {
        studentColumn.setCellValueFactory(cellData -> cellData.getValue().studentNameProperty());
        gradeColumn.setCellValueFactory(cellData -> cellData.getValue().gradeProperty().asObject());

        loadGrades();
    }

    public void setTeacherName(String name) {
        welcomeLabel.setText("Dobrodošli, " + name + "!");
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
        loadGrades();
    }

    private void loadGrades() {
        studentGrades.clear();

        // Dohvati sve učenike
        List<Student> students = studentRepo.getAllStudents();

        for (Student s : students) {
            // Dohvati sve ocjene učenika za ovaj predmet
            List<Grade> grades = gradeRepo.getGradesForStudent(s.getId());

            for (Grade g : grades) {
                // Ako filtriramo po predmetu, preskoči ostale
                if (subjectId != 0 && g.getSubjectId() != subjectId) continue;

                studentGrades.add(new StudentGrade(
                        s.getIme() + " " + s.getPrezime(),
                        g.getOcjena()
                ));
            }
        }

        studentsTable.setItems(studentGrades);
    }

    @FXML
    private void handleAddGrade() {
        // TODO: Dodati dijalog za unos učenika i ocjene
        // Nakon unosa pozvati:
        // gradeRepo.addGrade(new Grade(...));
        System.out.println("Dodavanje ocjene u bazu još nije implementirano");
    }

    @FXML
    private void handleDeleteGrade() {
        StudentGrade selected = studentsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // TODO: Dohvatiti Grade.id iz baze i pozvati gradeRepo.deleteGrade(id);
            studentGrades.remove(selected);
            System.out.println("Brisanje ocjene iz baze još nije implementirano");
        }
    }
}
