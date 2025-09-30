package ui;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import db.DBConnection;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    public LoginFrame(){
        setTitle("Admin Login");
        setSize(360,220);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel u = new JLabel("Username:");
        u.setBounds(30,30,90,25); add(u);
        usernameField = new JTextField(); usernameField.setBounds(130,30,180,25); add(usernameField);

        JLabel p = new JLabel("Password:");
        p.setBounds(30,70,90,25); add(p);
        passwordField = new JPasswordField(); passwordField.setBounds(130,70,180,25); add(passwordField);

        JButton login = new JButton("Login");
        login.setBounds(130,120,100,30); add(login);

        login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ login(); }
        });
    }
    private void login(){
        String user = usernameField.getText();
        String pass = String.valueOf(passwordField.getPassword());
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM admin WHERE username=? AND password=?");
            pst.setString(1, user);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(this, "Welcome " + user + "!");
                new MainDashboard().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
            }
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
