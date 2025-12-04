package com.skola.controller;

import com.skola.model.Grade;
import com.skola.model.Student;
import com.skola.model.SubjectGrade;
import com.skola.repository.GradeRepository;
import com.skola.repository.StudentRepository;
import com.skola.repository.SubjectRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label averageLabel;

    @FXML
    private TableView<SubjectGrade> gradesTable;

    @FXML
    private TableColumn<SubjectGrade, String> subjectColumn;

    @FXML
    private TableColumn<SubjectGrade, Integer> gradeColumn;

    private ObservableList<SubjectGrade> grades = FXCollections.observableArrayList();

    private StudentRepository studentRepo = new StudentRepository();
    private GradeRepository gradeRepo = new GradeRepository();
    private SubjectRepository subjectRepo = new SubjectRepository();

    private int studentId;
    private String studentName;

    public void setStudentName(String name) {
        this.studentName = name;
        welcomeLabel.setText("Dobrodošli, " + name + "!");

        // Dohvati studenta iz baze prema username
        Student student = studentRepo.getStudentByUsername(name);
        if (student != null) {
            this.studentId = student.getId();
            loadGrades();
        }
    }

    private void loadGrades() {
        grades.clear();

        // Dohvati sve ocjene ovog učenika
        List<Grade> studentGrades = gradeRepo.getGradesForStudent(studentId);

        // Grupiraj ocjene po predmetu
        Map<String, Double> subjectAvgMap = new HashMap<>();
        Map<String, Integer> subjectCountMap = new HashMap<>();

        for (Grade g : studentGrades) {
            String subjectName = g.getSubjectName();
            subjectAvgMap.put(subjectName, subjectAvgMap.getOrDefault(subjectName, 0.0) + g.getOcjena());
            subjectCountMap.put(subjectName, subjectCountMap.getOrDefault(subjectName, 0) + 1);
        }

        // Kreiraj SubjectGrade sa zakljućnom ocjenom po predmetu
        for (String subject : subjectAvgMap.keySet()) {
            double avg = subjectAvgMap.get(subject) / subjectCountMap.get(subject);
            int finalGrade = convertToFinalGrade(avg);
            grades.add(new SubjectGrade(subject, finalGrade));
        }

        // Postavi ocjene u tabelu
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        gradesTable.setItems(grades);

        // Izračunaj ukupni prosjek
        int total = 0;
        for (SubjectGrade sg : grades) {
            total += sg.getGrade();
        }
        double overallAvg = grades.isEmpty() ? 0 : (double) total / grades.size();
        averageLabel.setText("Ukupni prosjek: " + String.format("%.2f", overallAvg));
    }

    private int convertToFinalGrade(double avg) {
        if (avg >= 4.5) return 5;
        if (avg >= 3.5) return 4;
        if (avg >= 2.5) return 3;
        return 2;
    }
}
