package com.skola.repository;

import com.skola.model.Subject;
import com.skola.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepository {

    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM Subject";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while(rs.next()) {
                subjects.add(new Subject(
                        rs.getInt("id"),
                        rs.getString("naziv"),
                        rs.getString("smjer"),
                        rs.getInt("teacher_id")
                ));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    public void addSubject(Subject subject) {
        String sql = "INSERT INTO Subject (naziv, smjer, teacher_id) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, subject.getNaziv());
            stmt.setString(2, subject.getSmjer());
            stmt.setInt(3, subject.getTeacherId());
            stmt.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSubject(int subjectId) {
        String sql = "DELETE FROM Subject WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, subjectId);
            stmt.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
