package com.skola.model;

import javafx.beans.property.*;

public class DirectorStudent {
    private final StringProperty studentName;
    private final StringProperty subject;
    private final IntegerProperty grade;

    public DirectorStudent(String studentName, String subject, int grade) {
        this.studentName = new SimpleStringProperty(studentName);
        this.subject = new SimpleStringProperty(subject);
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

    public String getSubject() {
        return subject.get();
    }

    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    public StringProperty subjectProperty() {
        return subject;
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
