package ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import dao.DoctorDAO;
import model.Doctor;
import java.awt.event.*;
import java.util.List;

public class DoctorFrame extends JFrame {
    private JTextField id, name, specialization, phone;
    private JTable table;
    private DoctorDAO dao = new DoctorDAO();

    public DoctorFrame(){
        setTitle("Doctors");
        setSize(700,450);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lid = new JLabel("ID"); lid.setBounds(20,20,80,25); add(lid);
        id = new JTextField(); id.setBounds(110,20,80,25); add(id);
        JLabel lname = new JLabel("Name"); lname.setBounds(20,55,80,25); add(lname);
        name = new JTextField(); name.setBounds(110,55,200,25); add(name);
        JLabel lspec = new JLabel("Specialization"); lspec.setBounds(20,90,100,25); add(lspec);
        specialization = new JTextField(); specialization.setBounds(110,90,200,25); add(specialization);
        JLabel lphone = new JLabel("Phone"); lphone.setBounds(20,125,80,25); add(lphone);
        phone = new JTextField(); phone.setBounds(110,125,200,25); add(phone);

        JButton addBtn = new JButton("Add"); addBtn.setBounds(20,170,90,30); add(addBtn);
        JButton updBtn = new JButton("Update"); updBtn.setBounds(120,170,90,30); add(updBtn);
        JButton delBtn = new JButton("Delete"); delBtn.setBounds(220,170,90,30); add(delBtn);

        table = new JTable(); JScrollPane sp = new JScrollPane(table); sp.setBounds(340,20,330,360); add(sp);

        addBtn.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){ addD(); load(); }});
        updBtn.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){ updD(); load(); }});
        delBtn.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){ delD(); load(); }});

        table.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                int row = table.getSelectedRow();
                if(row>=0){
                    id.setText(table.getValueAt(row,0).toString());
                    name.setText(table.getValueAt(row,1).toString());
                    specialization.setText(table.getValueAt(row,2).toString());
                    phone.setText(table.getValueAt(row,3).toString());
                }
            }
        });
        load();
    }
    private void load(){
        try{
            List<Doctor> list = dao.getAll();
            DefaultTableModel m = new DefaultTableModel(new Object[]{"ID","Name","Specialization","Phone"},0);
            for(Doctor d: list) m.addRow(new Object[]{d.getId(), d.getName(), d.getSpecialization(), d.getPhone()});
            table.setModel(m);
        } catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); }
    }
    private void addD(){ try{ dao.add(new Doctor(name.getText(), specialization.getText(), phone.getText())); JOptionPane.showMessageDialog(this,"Added"); } catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); } }
    private void updD(){ try{ dao.update(new Doctor(Integer.parseInt(id.getText()), name.getText(), specialization.getText(), phone.getText())); JOptionPane.showMessageDialog(this,"Updated"); } catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); } }
    private void delD(){ try{ dao.delete(Integer.parseInt(id.getText())); JOptionPane.showMessageDialog(this,"Deleted"); } catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); } }
}
