package com.skola.repository;

import com.skola.model.Teacher;
import com.skola.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository {

    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM Teacher";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while(rs.next()) {
                teachers.add(new Teacher(
                        rs.getInt("id"),
                        rs.getString("ime"),
                        rs.getString("prezime")
                ));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public void addTeacher(Teacher teacher) {
        String sql = "INSERT INTO Teacher (ime, prezime) VALUES (?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, teacher.getIme());
            stmt.setString(2, teacher.getPrezime());
            stmt.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTeacher(int teacherId) {
        String sql = "DELETE FROM Teacher WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, teacherId);
            stmt.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
