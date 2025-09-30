package ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import dao.RoomDAO;
import model.Room;
import java.awt.event.*;
import java.util.List;

public class RoomFrame extends JFrame {
    private JTextField id, roomNo, type, status;
    private JTable table;
    private RoomDAO dao = new RoomDAO();

    public RoomFrame(){
        setTitle("Rooms");
        setSize(700,450);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lid = new JLabel("ID"); lid.setBounds(20,20,80,25); add(lid);
        id = new JTextField(); id.setBounds(110,20,80,25); add(id);
        JLabel lno = new JLabel("Room No"); lno.setBounds(20,55,80,25); add(lno);
        roomNo = new JTextField(); roomNo.setBounds(110,55,200,25); add(roomNo);
        JLabel ltype = new JLabel("Type"); ltype.setBounds(20,90,80,25); add(ltype);
        type = new JTextField(); type.setBounds(110,90,200,25); add(type);
        JLabel lstatus = new JLabel("Status"); lstatus.setBounds(20,125,80,25); add(lstatus);
        status = new JTextField(); status.setBounds(110,125,200,25); add(status);

        JButton addBtn = new JButton("Add"); addBtn.setBounds(20,170,90,30); add(addBtn);
        JButton updBtn = new JButton("Update"); updBtn.setBounds(120,170,90,30); add(updBtn);
        JButton delBtn = new JButton("Delete"); delBtn.setBounds(220,170,90,30); add(delBtn);

        table = new JTable(); JScrollPane sp = new JScrollPane(table); sp.setBounds(340,20,330,360); add(sp);

        addBtn.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){ addR(); load(); }});
        updBtn.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){ updR(); load(); }});
        delBtn.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){ delR(); load(); }});

        table.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                int row = table.getSelectedRow();
                if(row>=0){
                    id.setText(table.getValueAt(row,0).toString());
                    roomNo.setText(table.getValueAt(row,1).toString());
                    type.setText(table.getValueAt(row,2).toString());
                    status.setText(table.getValueAt(row,3).toString());
                }
            }
        });
        load();
    }
    private void load(){
        try{
            List<Room> list = dao.getAll();
            DefaultTableModel m = new DefaultTableModel(new Object[]{"ID","Room No","Type","Status"},0);
            for(Room r: list) m.addRow(new Object[]{r.getId(), r.getRoomNo(), r.getType(), r.getStatus()});
            table.setModel(m);
        } catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); }
    }
    private void addR(){ try{ dao.add(new Room(roomNo.getText(), type.getText(), status.getText())); JOptionPane.showMessageDialog(this,"Added"); } catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); } }
    private void updR(){ try{ dao.update(new Room(Integer.parseInt(id.getText()), roomNo.getText(), type.getText(), status.getText())); JOptionPane.showMessageDialog(this,"Updated"); } catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); } }
    private void delR(){ try{ dao.delete(Integer.parseInt(id.getText())); JOptionPane.showMessageDialog(this,"Deleted"); } catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); } }
}
