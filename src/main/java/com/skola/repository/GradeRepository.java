package com.skola.repository;

import com.skola.model.Grade;
import com.skola.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeRepository {

    // Dohvati sve ocjene za jednog učenika sa imenom predmeta
    public List<Grade> getGradesForStudent(int studentId) {
        List<Grade> grades = new ArrayList<>();
        String sql = "SELECT g.id, g.student_id, g.subject_id, g.ocjena, s.name AS subject_name " +
                "FROM Grade g " +
                "JOIN Subject s ON g.subject_id = s.id " +
                "WHERE g.student_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Grade g = new Grade();
                g.setId(rs.getInt("id"));
                g.setStudentId(rs.getInt("student_id"));
                g.setSubjectId(rs.getInt("subject_id"));
                g.setOcjena(rs.getInt("ocjena"));
                g.setSubjectName(rs.getString("subject_name"));
                grades.add(g);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grades;
    }

    // Dohvati sve ocjene svih učenika (za nastavnika)
    public List<Grade> getAllGrades() {
        List<Grade> grades = new ArrayList<>();
        String sql = "SELECT g.id, g.student_id, g.subject_id, g.ocjena, s.name AS subject_name " +
                "FROM Grade g " +
                "JOIN Subject s ON g.subject_id = s.id";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Grade g = new Grade();
                g.setId(rs.getInt("id"));
                g.setStudentId(rs.getInt("student_id"));
                g.setSubjectId(rs.getInt("subject_id"));
                g.setOcjena(rs.getInt("ocjena"));
                g.setSubjectName(rs.getString("subject_name"));
                grades.add(g);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grades;
    }

    public void addGrade(Grade grade) {
        String sql = "INSERT INTO Grade (student_id, subject_id, ocjena) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, grade.getStudentId());
            stmt.setInt(2, grade.getSubjectId());
            stmt.setInt(3, grade.getOcjena());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGrade(int gradeId) {
        String sql = "DELETE FROM Grade WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, gradeId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
