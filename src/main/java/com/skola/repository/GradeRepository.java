package com.skola.repository;

import com.skola.model.Grade;
import com.skola.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeRepository {

    public List<Grade> getAllGrades() {
        List<Grade> grades = new ArrayList<>();
        String sql = "SELECT * FROM Grade";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while(rs.next()) {
                grades.add(new Grade(
                        rs.getInt("id"),
                        rs.getInt("student_id"),
                        rs.getInt("subject_id"),
                        rs.getInt("ocjena")
                ));
            }

        } catch(SQLException e) {
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

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGrade(int gradeId) {
        String sql = "DELETE FROM Grade WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, gradeId);
            stmt.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
