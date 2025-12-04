package com.skola.model;

public class Grade {
    private int id;
    private int studentId;
    private int subjectId;
    private int ocjena;

    public Grade(int id, int studentId, int subjectId, int ocjena) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.ocjena = ocjena;
    }

    public int getId() { return id; }
    public int getStudentId() { return studentId; }
    public int getSubjectId() { return subjectId; }
    public int getOcjena() { return ocjena; }

    public void setId(int id) { this.id = id; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public void setSubjectId(int subjectId) { this.subjectId = subjectId; }
    public void setOcjena(int ocjena) { this.ocjena = ocjena; }
}
