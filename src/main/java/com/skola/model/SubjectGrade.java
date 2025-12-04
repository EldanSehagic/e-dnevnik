package com.skola.model;

public class SubjectGrade {
    private String subjectName;
    private int grade;

    public SubjectGrade(String subjectName, int grade) {
        this.subjectName = subjectName;
        this.grade = grade;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getGrade() {
        return grade;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
