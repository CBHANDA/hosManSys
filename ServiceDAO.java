package dao;
import db.DBConnection;
import model.Service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {
    public void add(Service s) throws SQLException {
        String sql = "INSERT INTO service(patient_id, description, cost) VALUES(?,?,?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, s.getPatientId());
            pst.setString(2, s.getDescription());
            pst.setDouble(3, s.getCost());
            pst.executeUpdate();
        }
    }
    public void update(Service s) throws SQLException {
        String sql = "UPDATE service SET patient_id=?, description=?, cost=? WHERE id=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, s.getPatientId());
            pst.setString(2, s.getDescription());
            pst.setDouble(3, s.getCost());
            pst.setInt(4, s.getId());
            pst.executeUpdate();
        }
    }
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM service WHERE id=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
    public List<Service> getByPatient(int patientId) throws SQLException {
        List<Service> list = new ArrayList<>();
        String sql = "SELECT * FROM service WHERE patient_id=? ORDER BY id DESC";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, patientId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Service(
                    rs.getInt("id"),
                    rs.getInt("patient_id"),
                    rs.getString("description"),
                    rs.getDouble("cost")
                ));
            }
        }
        return list;
    }
    public double totalForPatient(int patientId) throws SQLException {
        String sql = "SELECT COALESCE(SUM(cost),0) as total FROM service WHERE patient_id=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, patientId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) return rs.getDouble("total");
        }
        return 0.0;
    }
}
