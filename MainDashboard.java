package ui;
import javax.swing.*;
import java.awt.event.*;

public class MainDashboard extends JFrame {
    public MainDashboard(){
        setTitle("Hospital Management - Dashboard");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton patients = new JButton("Patients");
        patients.setBounds(40,40,180,40); add(patients);
        JButton doctors = new JButton("Doctors");
        doctors.setBounds(260,40,180,40); add(doctors);
        JButton rooms = new JButton("Rooms");
        rooms.setBounds(40,120,180,40); add(rooms);
        JButton services = new JButton("Services/Billing");
        services.setBounds(260,120,180,40); add(services);

        patients.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){ new PatientFrame().setVisible(true);} });
        doctors.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){ new DoctorFrame().setVisible(true);} });
        rooms.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){ new RoomFrame().setVisible(true);} });
        services.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){ new ServiceFrame().setVisible(true);} });
    }
}
