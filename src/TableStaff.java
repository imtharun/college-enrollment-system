import java.sql.*;
import javax.swing.*;

public class TableStaff extends JFrame{
    JTable jt;
    JScrollPane jst;
    String[] columns = {"Name", "DOB", "Gender", "Email", "Phone", "Education", "Address", "Username", "Pass"};
    String[][] row = new String[100][9];

    TableStaff(){
        jt = new JTable();
        try{
            Connection con = DriverManager.getConnection("jdbc:derby:staffdb;create=true");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM STAFF");
            int index = 0;
            while(rs.next()){
                row[index][0] = rs.getString(1);
                row[index][1] = rs.getString(2);
                row[index][2] = rs.getString(3);
                row[index][3] = rs.getString(4);
                row[index][4] = rs.getString(5);
                row[index][5] = rs.getString(6);
                row[index][6] = rs.getString(7);
                row[index][7] = rs.getString(8);
                row[index][8] = rs.getString(9);
                index++;
            }
        }catch(Exception exp){
            exp.printStackTrace();
        }
        jt = new JTable(row, columns);
        jst = new JScrollPane(jt);
        jst.setBounds(100, 60, 500, 500);

        add(jst);
        setVisible(true);
        setSize(600, 600);
        setTitle("Staff Database");
    }
    
    
}
