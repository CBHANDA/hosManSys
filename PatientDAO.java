package dao;
import db.DBConnection;
import model.Patient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    public void add(Patient p) throws SQLException {
        String sql = "INSERT INTO patient(name, age, gender, address, phone) VALUES(?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, p.getName());
            pst.setInt(2, p.getAge());
            pst.setString(3, p.getGender());
            pst.setString(4, p.getAddress());
            pst.setString(5, p.getPhone());
            pst.executeUpdate();
        }
    }
    public void update(Patient p) throws SQLException {
        String sql = "UPDATE patient SET name=?, age=?, gender=?, address=?, phone=? WHERE id=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, p.getName());
            pst.setInt(2, p.getAge());
            pst.setString(3, p.getGender());
            pst.setString(4, p.getAddress());
            pst.setString(5, p.getPhone());
            pst.setInt(6, p.getId());
            pst.executeUpdate();
        }
    }
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM patient WHERE id=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
    public Patient get(int id) throws SQLException {
        String sql = "SELECT * FROM patient WHERE id=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return map(rs);
            }
            return null;
        }
    }
    public List<Patient> getAll() throws SQLException {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM patient ORDER BY id DESC";
        try (Connection con = DBConnection.getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }
    private Patient map(ResultSet rs) throws SQLException {
        return new Patient(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("age"),
            rs.getString("gender"),
            rs.getString("address"),
            rs.getString("phone")
        );
    }
}
