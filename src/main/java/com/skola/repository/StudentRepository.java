package com.skola.repository;

import com.skola.model.Student;
import com.skola.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRepository {

    // Dohvati jednog studenta po imenu (ili username ako postoji)
    public Student getStudentByUsername(String username) {
        Student student = null;
        String sql = "SELECT * FROM Student WHERE ime = ?"; // koristi "ime" iz baze

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                student = new Student(
                        rs.getInt("id"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("smjer")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    // Dodaj studenta
    public void addStudent(Student student) {
        String sql = "INSERT INTO Student (ime, prezime, smjer) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getIme());
            stmt.setString(2, student.getPrezime());
            stmt.setString(3, student.getSmjer());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obriši studenta po ID
    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM Student WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
