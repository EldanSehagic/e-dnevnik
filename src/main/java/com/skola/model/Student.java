package com.skola.model;

public class Student {
    private int id;
    private String ime;
    private String prezime;
    private String smjer;

    public Student(int id, String ime, String prezime, String smjer) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.smjer = smjer;
    }

    public int getId() { return id; }
    public String getIme() { return ime; }
    public String getPrezime() { return prezime; }
    public String getSmjer() { return smjer; }

    public void setId(int id) { this.id = id; }
    public void setIme(String ime) { this.ime = ime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }
    public void setSmjer(String smjer) { this.smjer = smjer; }
}
