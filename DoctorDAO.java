package dao;
import db.DBConnection;
import model.Doctor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    public void add(Doctor d) throws SQLException {
        String sql = "INSERT INTO doctor(name, specialization, phone) VALUES(?,?,?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, d.getName());
            pst.setString(2, d.getSpecialization());
            pst.setString(3, d.getPhone());
            pst.executeUpdate();
        }
    }
    public void update(Doctor d) throws SQLException {
        String sql = "UPDATE doctor SET name=?, specialization=?, phone=? WHERE id=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, d.getName());
            pst.setString(2, d.getSpecialization());
            pst.setString(3, d.getPhone());
            pst.setInt(4, d.getId());
            pst.executeUpdate();
        }
    }
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM doctor WHERE id=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
    public List<Doctor> getAll() throws SQLException {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT * FROM doctor ORDER BY id DESC";
        try (Connection con = DBConnection.getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new Doctor(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("specialization"),
                    rs.getString("phone")
                ));
            }
        }
        return list;
    }
}
