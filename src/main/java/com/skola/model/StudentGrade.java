package com.skola.model;

import javafx.beans.property.*;

public class StudentGrade {
    private final StringProperty studentName;
    private final IntegerProperty grade;

    public StudentGrade(String studentName, int grade) {
        this.studentName = new SimpleStringProperty(studentName);
        this.grade = new SimpleIntegerProperty(grade);
    }

    public String getStudentName() {
        return studentName.get();
    }

    public void setStudentName(String studentName) {
        this.studentName.set(studentName);
    }

    public StringProperty studentNameProperty() {
        return studentName;
    }

    public int getGrade() {
        return grade.get();
    }

    public void setGrade(int grade) {
        this.grade.set(grade);
    }

    public IntegerProperty gradeProperty() {
        return grade;
    }
}
