package dao;
import db.DBConnection;
import model.Room;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    public void add(Room r) throws SQLException {
        String sql = "INSERT INTO room(room_no, type, status) VALUES(?,?,?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, r.getRoomNo());
            pst.setString(2, r.getType());
            pst.setString(3, r.getStatus());
            pst.executeUpdate();
        }
    }
    public void update(Room r) throws SQLException {
        String sql = "UPDATE room SET room_no=?, type=?, status=? WHERE id=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, r.getRoomNo());
            pst.setString(2, r.getType());
            pst.setString(3, r.getStatus());
            pst.setInt(4, r.getId());
            pst.executeUpdate();
        }
    }
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM room WHERE id=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
    public List<Room> getAll() throws SQLException {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT * FROM room ORDER BY id DESC";
        try (Connection con = DBConnection.getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new Room(
                    rs.getInt("id"),
                    rs.getString("room_no"),
                    rs.getString("type"),
                    rs.getString("status")
                ));
            }
        }
        return list;
    }
}
