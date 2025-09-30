package ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import dao.ServiceDAO;
import model.Service;
import java.awt.event.*;
import java.util.List;

public class ServiceFrame extends JFrame {
    private JTextField id, patientId, description, cost;
    private JTable table;
    private ServiceDAO dao = new ServiceDAO();

    public ServiceFrame(){
        setTitle("Services / Billing");
        setSize(800,500);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lid = new JLabel("ID"); lid.setBounds(20,20,80,25); add(lid);
        id = new JTextField(); id.setBounds(110,20,80,25); add(id);
        JLabel lpid = new JLabel("Patient ID"); lpid.setBounds(20,55,80,25); add(lpid);
        patientId = new JTextField(); patientId.setBounds(110,55,100,25); add(patientId);
        JLabel ldesc = new JLabel("Description"); ldesc.setBounds(20,90,80,25); add(ldesc);
        description = new JTextField(); description.setBounds(110,90,200,25); add(description);
        JLabel lcost = new JLabel("Cost"); lcost.setBounds(20,125,80,25); add(lcost);
        cost = new JTextField(); cost.setBounds(110,125,100,25); add(cost);

        JButton addBtn = new JButton("Add"); addBtn.setBounds(20,170,90,30); add(addBtn);
        JButton updBtn = new JButton("Update"); updBtn.setBounds(120,170,90,30); add(updBtn);
        JButton delBtn = new JButton("Delete"); delBtn.setBounds(220,170,90,30); add(delBtn);
        JButton totalBtn = new JButton("Total for Patient"); totalBtn.setBounds(20,210,190,30); add(totalBtn);

        table = new JTable(); JScrollPane sp = new JScrollPane(table); sp.setBounds(340,20,430,420); add(sp);

        addBtn.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){ addS(); load(); }});
        updBtn.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){ updS(); load(); }});
        delBtn.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){ delS(); load(); }});
        totalBtn.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){ showTotal(); }});

        load();
    }
    private void load(){
        try{
            int pid = 0;
            try{ pid = Integer.parseInt(patientId.getText()); } catch(Exception ignore){}
            List<Service> list = pid>0 ? dao.getByPatient(pid) : dao.getByPatient(0);
            DefaultTableModel m = new DefaultTableModel(new Object[]{"ID","Patient ID","Description","Cost"},0);
            for(Service s: list) m.addRow(new Object[]{s.getId(), s.getPatientId(), s.getDescription(), s.getCost()});
            table.setModel(m);
        } catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); }
    }
    private void addS(){ try{ dao.add(new Service(Integer.parseInt(patientId.getText()), description.getText(), Double.parseDouble(cost.getText()))); JOptionPane.showMessageDialog(this,"Added"); } catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); } }
    private void updS(){ try{ dao.update(new Service(Integer.parseInt(id.getText()), Integer.parseInt(patientId.getText()), description.getText(), Double.parseDouble(cost.getText()))); JOptionPane.showMessageDialog(this,"Updated"); } catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); } }
    private void delS(){ try{ dao.delete(Integer.parseInt(id.getText())); JOptionPane.showMessageDialog(this,"Deleted"); } catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); } }
    private void showTotal(){ try{ double t = dao.totalForPatient(Integer.parseInt(patientId.getText())); JOptionPane.showMessageDialog(this, "Total: " + t); } catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); } }
}
