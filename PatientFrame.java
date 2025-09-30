package ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import dao.PatientDAO;
import model.Patient;
import java.awt.event.*;
import java.util.List;

public class PatientFrame extends JFrame {
    private JTextField name, age, gender, address, phone, id;
    private JTable table;
    private PatientDAO dao = new PatientDAO();

    public PatientFrame(){
        setTitle("Patients");
        setSize(800,500);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lid = new JLabel("ID"); lid.setBounds(20,20,80,25); add(lid);
        id = new JTextField(); id.setBounds(110,20,80,25); add(id);
        JLabel lname = new JLabel("Name"); lname.setBounds(20,55,80,25); add(lname);
        name = new JTextField(); name.setBounds(110,55,200,25); add(name);
        JLabel lage = new JLabel("Age"); lage.setBounds(20,90,80,25); add(lage);
        age = new JTextField(); age.setBounds(110,90,80,25); add(age);
        JLabel lgender = new JLabel("Gender"); lgender.setBounds(20,125,80,25); add(lgender);
        gender = new JTextField(); gender.setBounds(110,125,100,25); add(gender);
        JLabel laddr = new JLabel("Address"); laddr.setBounds(20,160,80,25); add(laddr);
        address = new JTextField(); address.setBounds(110,160,200,25); add(address);
        JLabel lphone = new JLabel("Phone"); lphone.setBounds(20,195,80,25); add(lphone);
        phone = new JTextField(); phone.setBounds(110,195,200,25); add(phone);

        JButton addBtn = new JButton("Add"); addBtn.setBounds(20,240,90,30); add(addBtn);
        JButton updBtn = new JButton("Update"); updBtn.setBounds(120,240,90,30); add(updBtn);
        JButton delBtn = new JButton("Delete"); delBtn.setBounds(220,240,90,30); add(delBtn);
        JButton clrBtn = new JButton("Clear"); clrBtn.setBounds(320,240,90,30); add(clrBtn);

        table = new JTable(); JScrollPane sp = new JScrollPane(table); sp.setBounds(340,20,430,420); add(sp);

        addBtn.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){ addPatient(); load(); }});
        updBtn.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){ updatePatient(); load(); }});
        delBtn.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){ deletePatient(); load(); }});
        clrBtn.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){ clearFields(); }});

        table.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                int row = table.getSelectedRow();
                if(row>=0){
                    id.setText(table.getValueAt(row,0).toString());
                    name.setText(table.getValueAt(row,1).toString());
                    age.setText(table.getValueAt(row,2).toString());
                    gender.setText(table.getValueAt(row,3).toString());
                    address.setText(table.getValueAt(row,4).toString());
                    phone.setText(table.getValueAt(row,5).toString());
                }
            }
        });

        load();
    }
    private void load(){
        try{
            List<Patient> list = dao.getAll();
            DefaultTableModel m = new DefaultTableModel(new Object[]{"ID","Name","Age","Gender","Address","Phone"},0);
            for(Patient p: list){
                m.addRow(new Object[]{p.getId(), p.getName(), p.getAge(), p.getGender(), p.getAddress(), p.getPhone()});
            }
            table.setModel(m);
        } catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); }
    }
    private void addPatient(){
        try{
            Patient p = new Patient(name.getText(), Integer.parseInt(age.getText()), gender.getText(), address.getText(), phone.getText());
            dao.add(p);
            clearFields();
            JOptionPane.showMessageDialog(this, "Added!");
        } catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); }
    }
    private void updatePatient(){
        try{
            Patient p = new Patient(Integer.parseInt(id.getText()), name.getText(), Integer.parseInt(age.getText()), gender.getText(), address.getText(), phone.getText());
            dao.update(p);
            JOptionPane.showMessageDialog(this, "Updated!");
        } catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); }
    }
    private void deletePatient(){
        try{
            dao.delete(Integer.parseInt(id.getText()));
            clearFields();
            JOptionPane.showMessageDialog(this, "Deleted!");
        } catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); }
    }
    private void clearFields(){ id.setText(""); name.setText(""); age.setText(""); gender.setText(""); address.setText(""); phone.setText("");}
}
