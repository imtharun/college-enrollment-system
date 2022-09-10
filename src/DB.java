import javax.swing.*;
import java.awt.event.*;

public class DB extends JFrame implements ActionListener {
    JButton studTable, staffTable;
    JLabel emp;
    public DB(){
        studTable = new JButton("Student DB");
        staffTable = new JButton("Staff DB");
        emp = new JLabel();

        add(staffTable);
        add(studTable);
        add(emp);

        staffTable.addActionListener(this);
        studTable.addActionListener(this);

        studTable.setBounds(80, 30, 100, 30);
        staffTable.setBounds(80, 80, 100, 30);

        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Database");
        setSize(300, 200);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == studTable){
            new TableStud();
        }
        if(ae.getSource() == staffTable){
            new TableStaff();
        }

    }
}
