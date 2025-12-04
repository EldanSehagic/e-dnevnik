package com.skola.model;

public class Subject {
    private int id;
    private String naziv;
    private String smjer;
    private int teacherId;

    public Subject(int id, String naziv, String smjer, int teacherId) {
        this.id = id;
        this.naziv = naziv;
        this.smjer = smjer;
        this.teacherId = teacherId;
    }

    public int getId() { return id; }
    public String getNaziv() { return naziv; }
    public String getSmjer() { return smjer; }
    public int getTeacherId() { return teacherId; }

    public void setId(int id) { this.id = id; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    public void setSmjer(String smjer) { this.smjer = smjer; }
    public void setTeacherId(int teacherId) { this.teacherId = teacherId; }
}
